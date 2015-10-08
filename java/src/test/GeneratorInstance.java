package test;

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

			// les arêtes qui sortent
			for(int i=0; i<V; i++) {
				int rdm = (int) (Math.random()*(V));
				while(rdm==i) {
					rdm = (int) (Math.random()*(V));
				}
				int rdmcapa = (int) (Math.random()*(capaMax))+1;

				if(!couples.contains(new Couple(i,rdm))) {
					fw.write(i+" "+rdm+" "+rdmcapa+"\n");
					couples.add(new Couple(i,rdm));
				}
				else {
					i--;
				}
			}
			E=E-V;

			// les arêtes qui rentrent
			for(int i=0; i<V; i++) {
				int rdm = (int) (Math.random()*(V));
				while(rdm==i) {
					rdm = (int) (Math.random()*(V));
				}
				int rdmcapa = (int) (Math.random()*(capaMax))+1;

				if(!couples.contains(new Couple(rdm,i))) {
					fw.write(rdm+" "+i+" "+rdmcapa+"\n");
					couples.add(new Couple(rdm,i));
				}
				else {
					i--;
				}
			}
			E=E-V;

			for(int i=0; i<E; i++){
				int rdm1 = (int) (Math.random()*(V));
				int rdm2 = (int) (Math.random()*(V));
				while(rdm2==rdm1) {
					rdm2 = (int) (Math.random()*(V));
				}
				int rdmcapa = (int) (Math.random()*(capaMax))+1;

				if(!couples.contains(new Couple(rdm1,rdm2))) {
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
		generateInstance(10,100000,1000,"instance7");
	}
}
