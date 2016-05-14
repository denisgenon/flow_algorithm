package results;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import object.Edge;

// On commence avec un graphe à 1000 sommets et une densité de 10%
// On garde ce même graphe, on y ajouter 500 sommets et les arêtes qu'il faut pour garder 10%
// Ainsi de suite jusque 10 000 sommets
public class InstanceGeneratorSize {

	public static ArrayList<Edge> edges;
	public static ArrayList<Integer> connected;
	public static int capaMax = 10000;
	public static int V;

	public static int getRandomElem(ArrayList<Integer> array){
		int index = (int) (Math.random()*array.size());
		return array.get(index);
	}

	public static void generateFirstGraph(){

		V = 1000;
		edges = new ArrayList<Edge>();
		connected = new ArrayList<Integer>();
		ArrayList<Integer> doubleLinked = new ArrayList<Integer>();

		// D'abord, avoir un graphe connecté minimum
		connected.add(0);
		for(int i=1; i<V; i++){
			int a = getRandomElem(connected);
			Edge e = new Edge(a,i,capaMax);
			edges.add(e);
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
					doubleLinked.add(i);
				}
			}
		}

		// Rajouter des arêtes pour avoir 10%
		while((double)(edges.size())<(double)(V-1)*V/20.0){
			int u = getRandomElem(connected);
			int v = getRandomElem(connected);
			while(v==u){
				v = getRandomElem(connected);
			}
			Edge e = new Edge(u,v,capaMax);
			Edge reverseE = new Edge(v,u,capaMax);
			while(edges.contains(e) || edges.contains(reverseE)){
				u = getRandomElem(connected);
				v = getRandomElem(connected);
				while(v==u){
					v = getRandomElem(connected);
				}
				e = new Edge(u,v,capaMax);
				reverseE = new Edge(v,u,capaMax);
			}
			edges.add(e);
		}
	}

	public static void add500Vertices(){
		V+=500;
		ArrayList<Integer> doubleLinked = new ArrayList<Integer>();

		// D'abord, avoir un graphe connecté minimum
		for(int i=V-500; i<V; i++){
			int a = getRandomElem(connected);
			Edge e = new Edge(a,i,capaMax);
			edges.add(e);
			if(a>=(V-500)) {
				if(!doubleLinked.contains(a)) doubleLinked.add(a);
			}
			connected.add(i);
		}

		// Rajouter des arêtes pour enlever les culs de sacs
		for(int i=V-500; i<V; i++){
			if(!doubleLinked.contains(i)){
				int a = getRandomElem(connected);
				Edge e = new Edge(i,a,capaMax);
				Edge reverseE = new Edge(a,i,capaMax);
				if(edges.contains(e) || edges.contains(reverseE)){
					i--;
				}
				else {
					edges.add(e);
					doubleLinked.add(i);
				}
			}
		}

		// Rajouter des arêtes pour avoir le pourcentage voulu
		while((double)(edges.size())<(double)(V-1)*V/20.0){
			int u = getRandomElem(connected);
			int v = getRandomElem(connected);
			while(v==u){
				v = getRandomElem(connected);
			}
			Edge e = new Edge(u,v,capaMax);
			Edge reverseE = new Edge(v,u,capaMax);
			while(edges.contains(e) || edges.contains(reverseE)){
				u = getRandomElem(connected);
				v = getRandomElem(connected);
				while(v==u){
					v = getRandomElem(connected);
				}
				e = new Edge(u,v,capaMax);
				reverseE = new Edge(v,u,capaMax);
			}
			edges.add(e);
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
		System.out.println("Création de "+filename);
	}

	public static void main(String[] args){
		
		String foldername = "instancesSize";
		for(int i=8; i<=10;i++){
			System.out.println("Instance Size "+i);
			generateFirstGraph();
			createGraphFile(foldername+i,"instancesize"+V);
			while(V<5000){
				add500Vertices();
				createGraphFile(foldername+i,"instancesize"+V);
			}
		}
	}

}
