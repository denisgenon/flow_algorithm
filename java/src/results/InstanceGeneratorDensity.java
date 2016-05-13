package results;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import object.Edge;

public class InstanceGeneratorDensity {

	public static ArrayList<Edge> edges;
	public static ArrayList<Edge> allEdges;
	public static int V = 1000;
	public static int capaMax = 10000;

	public static int getRandomElem(ArrayList<Integer> array){
		int index = (int) (Math.random()*array.size());
		return array.get(index);
	}

	public static Edge getRandomEdge(ArrayList<Edge> array){
		int index = (int) (Math.random()*array.size());
		return array.get(index);
	}

	public static void generateUniqueInstance(String foldername) {

		edges = new ArrayList<Edge>();
		allEdges = new ArrayList<Edge>();


		ArrayList<Integer> connected = new ArrayList<Integer>();
		ArrayList<Integer> doubleLinked = new ArrayList<Integer>();

		// On fait une liste de toutes les arêtes possibles
		for(int u=0; u<V; u++){
			for(int v=0; v<V;v++){
				if(u!=v){
					allEdges.add(new Edge(u,v,capaMax));
				}
			}
		}

		// D'abord, avoir un graphe connecté minimum
		connected.add(0);
		for(int i=1; i<V; i++){
			int a = getRandomElem(connected);
			Edge e = new Edge(a,i,capaMax);
			Edge reverseE = new Edge(i,a,capaMax);
			edges.add(e);
			allEdges.remove(e);
			allEdges.remove(reverseE);
			if(a!=0) {
				if(!doubleLinked.contains(a)) doubleLinked.add(a);
			}
			connected.add(i);
		}

		// Rajouter des arêtes pour enlever les culs de sacs
		for(int i=0; i<V; i++){
			if(!doubleLinked.contains(i)){
				int a = getRandomElem(connected);
				Edge e = new Edge(i,a,capaMax);
				Edge reverseE = new Edge(a,i,capaMax);
				if(edges.contains(e) || edges.contains(reverseE)){
					i--;
				}
				else {
					edges.add(e);
					allEdges.remove(e);
					allEdges.remove(reverseE);
					doubleLinked.add(i);
				}
			}
		}
		System.out.println("Doublelinked : "+doubleLinked.size());

		// Rajouter des arêtes pour avoir le pourcentage voulu
		for(int prct=5;prct<=100; prct+=5){
			while((double)(edges.size()-(V-1))/(((V*(V-1))/2)-(V-1))*100 < prct){

				Edge newEdge = getRandomEdge(allEdges);
				edges.add(newEdge);
				allEdges.remove(newEdge);
				allEdges.remove(new Edge(newEdge.v,newEdge.u,capaMax));
			}

			System.out.println("Edges : "+edges.size());
			System.out.println("Remaining edges : "+allEdges.size());
			String pr;
			if (prct==5)pr="05";
			else pr=Integer.toString(prct);
			System.out.println("Création de : "+"instanceuniqueprct"+pr);
			createGraphFile(foldername, "instanceuniqueprct"+pr);
		}
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

	public static void main (String [] args) {	
		/*generateInstance(65);
		createGraphFile("instanceprct65");*/
		for(int i=8; i<=10; i++){
			generateUniqueInstance("instancesUniquePrct"+i);
		}

	}
}
