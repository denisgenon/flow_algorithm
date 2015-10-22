package flowAlgorithm;
import java.io.File;

import interfaces.AugmentingPathGraph;
import interfaces.Graph;
import interfaces.PushRelabelGraph;
import models.PushRelabel.AdjacencyListGraph;
import object.Node;
import object.Vertex;
import solver.EdmondsKarp;
import solver.FordFulkerson;
import solver.PushRelabel;

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
						PushRelabelGraph g = new AdjacencyListGraph(f.getPath());
						PushRelabel pr = new PushRelabel();
						pr.process(g);
						pr.getResult();
						//EdmondsKarp ek = new EdmondsKarp(g);
						//ek.getResult();
					}
				}

				else {
					//PushRelabelGraph g = new AdjacencyListGraph(args[0]);
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
