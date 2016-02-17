package tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import object.Edge;

public class GeneratorInstance {

	// TODO à corriger, voir enlever
	public static void generateInstance(int V, int E, int capaMax, String filename) {
		File f = new File ("instances/"+filename+".txt");
		try
		{
			ArrayList<Edge> edges = new ArrayList<Edge>(E);
			FileWriter fw = new FileWriter (f);
			fw.write(V+" "+E+"\n");
			
			// 0->1, 1->2, ..., (V-2)->(V-1)
			// Permet d'avoir un graphe connecté
			for(int i=0; i<V-1; i++) {
				int rdmcapa = (int) (Math.random()*(capaMax))+1;
				if(!edges.contains(new Edge(i,i+1,capaMax)) && !edges.contains(new Edge(i+1,i,capaMax))) {
					fw.write(i+" "+(i+1)+" "+rdmcapa+"\n");
					E--;
					edges.add(new Edge(i,i+1,capaMax));
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
				if(!edges.contains(new Edge(0,rdm1,capaMax)) && !edges.contains(new Edge(rdm1,0,capaMax))) {
					fw.write(0+" "+rdm1+" "+rdmcapa+"\n");
					E--;
					edges.add(new Edge(0,rdm1,capaMax));
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
				if(!edges.contains(new Edge(rdm1,(V-1),capaMax)) && !edges.contains(new Edge((V-1),rdm1,capaMax))) {
					fw.write(rdm1+" "+(V-1)+" "+rdmcapa+"\n");
					E--;
					edges.add(new Edge(rdm1,(V-1),capaMax));
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

				if(!edges.contains(new Edge(rdm1,rdm2,capaMax)) && !edges.contains(new Edge(rdm2,rdm1,capaMax))) {
					fw.write(rdm1+" "+rdm2+" "+rdmcapa+"\n");
					edges.add(new Edge(rdm1,rdm2,capaMax));
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
