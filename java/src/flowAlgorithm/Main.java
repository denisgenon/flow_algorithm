package flowAlgorithm;
import java.io.File;

import interfaces.Graph;
import models.AdjacencyListGraph;
import models.SparseSetGraph;
import object.Node;
import object.Vertex;
import solver.*;


public class Main {

	public static void printPath(Vertex [] mypath) {
		for(Vertex v : mypath){
			System.out.print(v + " ");
		}
		System.out.println();
	}
	
	public static void printNodes(Node [] capaMatrix) {
		for(int i=0; i<capaMatrix.length; i++) {
			Node current = capaMatrix[i];
			System.out.print(i+" : ");
			while(current!=null){
				System.out.print(current+ " ");
				current=current.next;
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("no instance file");
		} 
		else {
			try {
				if(args[0].equals("all")) {
					File directoryToScan = new File("instances"); 
					for(File f : directoryToScan.listFiles()){
						System.out.println(f.getName()+" : ");
						/*Graph g = new AdjacencyListGraph(f.getPath());
						PushRelabel pr = new PushRelabel();
						pr.process(g);
						pr.getResult();*/
						Graph g = new AdjacencyListGraph(f.getPath());
						//EdmondsKarp ek = new EdmondsKarp(g);
						//ek.getResult();
						FordFulkerson ff = new FordFulkerson(g);
						ff.getResult();
					}
				}

				else {
					Graph g = new SparseSetGraph(args[0]);
					PushRelabel pr = new PushRelabel();
					pr.process(g);
					pr.getResult();
					//FordFulkerson ff = new FordFulkerson(g);
					//ff.getResult();
					//EdmondsKarp ek = new EdmondsKarp(g);
					//ek.getResult();
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
