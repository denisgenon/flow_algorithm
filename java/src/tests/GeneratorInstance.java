package tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import object.Couple;

public class GeneratorInstance {

	public static void generateInstance(int V, int E, int capaMax, String filename) {
		File f = new File ("instances/"+filename+".txt");
		try
		{
			ArrayList<Couple> couples = new ArrayList<Couple>(E);
			FileWriter fw = new FileWriter (f);
			fw.write(V+" "+E+"\n");
			
			// 0->1, 1->2, ..., (V-2)->(V-1)
			// Permet d'avoir un graphe connecté
			for(int i=0; i<V-1; i++) {
				int rdmcapa = (int) (Math.random()*(capaMax))+1;
				if(!couples.contains(new Couple(i,i+1)) && !couples.contains(new Couple(i+1,i))) {
					fw.write(i+" "+(i+1)+" "+rdmcapa+"\n");
					E--;
					couples.add(new Couple(i,i+1));
				}
				else {
					i--;
				}
			}
			
			// Au moins quelques arêtes sortent de la source
			int nbrMin = (E/100)+1;
			for(int i=0; i<nbrMin && E>0; i++){
				int rdmcapa = (int) (Math.random()*(capaMax))+1;
				int rdm1 = (int) (Math.random()*(V));
				while(rdm1==0) {
					rdm1 = (int) (Math.random()*(V));
				}
				if(!couples.contains(new Couple(0,rdm1)) && !couples.contains(new Couple(rdm1,0))) {
					fw.write(0+" "+rdm1+" "+rdmcapa+"\n");
					E--;
					couples.add(new Couple(0,rdm1));
				}
				else {
					i--;
				}
			}
			// Au moins quelques arêtes arrivent au puit
			for(int i=0; i<nbrMin && E>0; i++){
				int rdmcapa = (int) (Math.random()*(capaMax))+1;
				int rdm1 = (int) (Math.random()*(V));
				while(rdm1==0) {
					rdm1 = (int) (Math.random()*(V));
				}
				if(!couples.contains(new Couple(rdm1,(V-1))) && !couples.contains(new Couple((V-1),rdm1))) {
					fw.write(rdm1+" "+(V-1)+" "+rdmcapa+"\n");
					E--;
					couples.add(new Couple(rdm1,(V-1)));
				}
				else {
					i--;
				}
			}
			
			// On rajoute le reste au hazard
			for(int i=0; i<E; i++){
				int rdm1 = (int) (Math.random()*(V));
				int rdm2 = (int) (Math.random()*(V));
				while(rdm2==rdm1) {
					rdm2 = (int) (Math.random()*(V));
				}
				int rdmcapa = (int) (Math.random()*(capaMax))+1;

				if(!couples.contains(new Couple(rdm1,rdm2)) && !couples.contains(new Couple(rdm2,rdm1))) {
					fw.write(rdm1+" "+rdm2+" "+rdmcapa+"\n");
					couples.add(new Couple(rdm1,rdm2));
				}
				else {
					i--;
				}
			}
			
			fw.close();
		}
		catch (IOException exception)
		{
			System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
	}

	public static void main (String [] args) {
		generateInstance(10000,20000,10000,"instance54");
	}
}
