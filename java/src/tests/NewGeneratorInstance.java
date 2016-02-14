package tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import object.Edge;

public class NewGeneratorInstance {

	public static ArrayList<Edge> edges = new ArrayList<Edge>();
	public static int V = 1000;
	public static int capaMax = 10000;

	public static int getRandomElem(ArrayList<Integer> array){
		int index = (int) (Math.random()*array.size());
		return array.get(index);
	}

	public static void generateInstance(int prct) {
		ArrayList<Integer> connected = new ArrayList<Integer>();
		ArrayList<Integer> doubleLinked = new ArrayList<Integer>();


		// D'abord, avoir un graphe connecté minimum
		connected.add(0);
		for(int i=1; i<V; i++){
			int a = getRandomElem(connected);
			edges.add(new Edge(a,i));
			if(a!=0) doubleLinked.add(a);
			connected.add(i);
		}

		// Enlever les culs de sacs
		for(int i=0; i<V; i++){
			if(!doubleLinked.contains(i)){
				int a = getRandomElem(connected);
				edges.add(new Edge(i,a));
				doubleLinked.add(i);
			}
		}

		// Rajouter des arêtes pour avoir le pourcentage voulu
		int currentprct = (int)((double)(edges.size()-(V-1))/(((V*(V-1))/2)-(V-1))*100);
		int oldprct = (int)((double)(edges.size()-(V-1))/(((V*(V-1))/2)-(V-1))*100);
		while((double)(edges.size()-(V-1))/(((V*(V-1))/2)-(V-1))*100 < prct){
			int rdm1 = getRandomElem(connected);
			int rdm2 = getRandomElem(connected);
			while(rdm2==rdm1) {
				rdm2 = getRandomElem(connected);
			}

			if(!edges.contains(new Edge(rdm1,rdm2)) && !edges.contains(new Edge(rdm2,rdm1))) {
				edges.add(new Edge(rdm1,rdm2));
			}
			currentprct = (int)((double)(edges.size()-(V-1))/(((V*(V-1))/2)-(V-1))*100);
			if(currentprct!=oldprct) {
				System.out.println(currentprct);
				oldprct=currentprct;
			}
		}
	}

	public static void createGraphFile(String filename) {
		File f = new File ("instancesPrct/"+filename+".txt");
		FileWriter fw;
		try {
			fw = new FileWriter (f);
			fw.write(V+" "+edges.size()+"\n");
			
			for(Edge c : edges){
				int rdmcapa = (int) (Math.random()*(capaMax))+1;
				fw.write(c.u+" "+c.v+" "+rdmcapa+"\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main (String [] args) {	
		generateInstance(50);
		createGraphFile("instanceprct50");
	}
}
