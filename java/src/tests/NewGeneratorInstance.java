package tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import object.Edge;

public class NewGeneratorInstance {

	public static ArrayList<Edge> edges = new ArrayList<Edge>();
	public static ArrayList<Edge> allEdges = new ArrayList<Edge>();
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

	public static void generateInstance(int prct) {
		ArrayList<Integer> connected = new ArrayList<Integer>();
		ArrayList<Integer> doubleLinked = new ArrayList<Integer>();
		
		// On fait une liste de toutes les arêtes possibles
		for(int u=0; u<V; u++){
			for(int v=0; v<V;v++){
				if(u!=v){
					allEdges.add(new Edge(u,v));
				}
			}
		}

		// D'abord, avoir un graphe connecté minimum
		connected.add(0);
		for(int i=1; i<V; i++){
			int a = getRandomElem(connected);
			Edge e = new Edge(a,i);
			Edge reverseE = new Edge(i,a);
			edges.add(e);
			allEdges.remove(e);
			allEdges.remove(reverseE);
			if(a!=0) doubleLinked.add(a);
			connected.add(i);
		}

		// Enlever les culs de sacs
		for(int i=0; i<V; i++){
			if(!doubleLinked.contains(i)){
				int a = getRandomElem(connected);
				Edge e = new Edge(i,a);
				Edge reverseE = new Edge(a,i);
				edges.add(e);
				allEdges.remove(e);
				allEdges.remove(reverseE);
				doubleLinked.add(i);
			}
		}

		// Rajouter des arêtes pour avoir le pourcentage voulu
		int currentprct = (int)((double)(edges.size()-(V-1))/(((V*(V-1))/2)-(V-1))*100);
		int oldprct = (int)((double)(edges.size()-(V-1))/(((V*(V-1))/2)-(V-1))*100);
		while((double)(edges.size()-(V-1))/(((V*(V-1))/2)-(V-1))*100 < prct){
			
			Edge newEdge = getRandomEdge(allEdges);
			edges.add(newEdge);
			allEdges.remove(newEdge);
			allEdges.remove(new Edge(newEdge.v,newEdge.u));
			
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
		generateInstance(65);
		createGraphFile("instanceprct65");
		
		edges = new ArrayList<Edge>();
		allEdges = new ArrayList<Edge>();
		
		generateInstance(70);
		createGraphFile("instanceprct70");
		
		edges = new ArrayList<Edge>();
		allEdges = new ArrayList<Edge>();
		
		generateInstance(75);
		createGraphFile("instanceprct75");
		
		edges = new ArrayList<Edge>();
		allEdges = new ArrayList<Edge>();
		
		generateInstance(80);
		createGraphFile("instanceprct80");
		
		edges = new ArrayList<Edge>();
		allEdges = new ArrayList<Edge>();
		
		generateInstance(85);
		createGraphFile("instanceprct85");
		
		edges = new ArrayList<Edge>();
		allEdges = new ArrayList<Edge>();
		
		generateInstance(90);
		createGraphFile("instanceprct90");
		
		edges = new ArrayList<Edge>();
		allEdges = new ArrayList<Edge>();
		
		generateInstance(95);
		createGraphFile("instanceprct95");
		
		edges = new ArrayList<Edge>();
		allEdges = new ArrayList<Edge>();
		
		generateInstance(100);
		createGraphFile("instanceprct100");	
	}
}
