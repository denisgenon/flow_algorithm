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

			//au moins 1% des arêtes partent de la source
			for(int i=0; i<Math.max(2, E/100); i++){
				int rdm = (int) (Math.random()*(V));
				while(rdm==0) {
					rdm = (int) (Math.random()*(V));
				}
				int rdmcapa = (int) (Math.random()*(capaMax))+1;
				if(!couples.contains(new Couple(0,rdm))) {
					fw.write(0+" "+rdm+" "+rdmcapa+"\n");
					couples.add(new Couple(0,rdm));
				}
				else {
					i--;
				}
			}

			//au moins 1% des arêtes arrivent à la destination
			for(int i=0; i<Math.max(2, E/100); i++){
				int rdm = (int) (Math.random()*(V));
				while(rdm==V-1) {
					rdm = (int) (Math.random()*(V));
				}
				int rdmcapa = (int) (Math.random()*(capaMax))+1;
				if(!couples.contains(new Couple(rdm,(V-1)))) {
					fw.write(rdm+" "+(V-1)+" "+rdmcapa+"\n");
					couples.add(new Couple(rdm,(V-1)));
				}
				else {
					i--;
				}
			}


			for(int i=0; i<E-2*(Math.max(2, E/100)); i++){
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
		generateInstance(10000,10000,100,"instance6");
	}
}
