package results;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import object.Edge;

public class InstanceGeneratorMatching {

	public static ArrayList<Edge> edges;
	public static ArrayList<Edge> allEdges;
	public static int V = 1002;

	public static int getRandomIndex(ArrayList<Edge> array){
		return (int) (Math.random()*array.size());
	}

	public static void createGraphFile(String foldername, String filename) {
		File f = new File ("instances/"+foldername+"/"+filename+".txt");
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write(V+" "+edges.size()+"\n");

			for(Edge c : edges){
				fw.write(c.u+" "+c.v+" "+c.capacity+"\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void generateInstance(int nbrinstance){

		edges=new ArrayList<Edge>();
		allEdges=new ArrayList<Edge>();

		// Relier 0 à 1->500
		for(int i=1; i<=500; i++){
			edges.add(new Edge(0,i,1));
		}

		// Relier 501->1001 à 1002
		for(int i=501; i<=1000; i++){
			edges.add(new Edge(i,1001,1));
		}

		// On genere toutes les arêtes possibles
		for(int i=1; i<=500; i++){
			for(int j=501; j<=1000; j++){
				allEdges.add(new Edge(i,j,1));
			}
		}

		// 250 000 arêtes max
		int prct=0;
		while(!allEdges.isEmpty()){
			for(int i=0; i<12500; i++){
				edges.add(allEdges.remove(getRandomIndex(allEdges)));
			}
			prct+=5;
			String pr="";
			if (prct==5)pr="05";
			else pr=Integer.toString(prct);
			createGraphFile("instancesMatching"+nbrinstance,"instancematching"+pr);
			System.out.println("Création de instancematching"+pr);
		}

	}



	public static void main (String [] args) {	
		for(int i=1; i<=5; i++){
			generateInstance(i);
		}
	}
}
