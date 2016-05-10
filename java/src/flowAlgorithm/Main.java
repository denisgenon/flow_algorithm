package flowAlgorithm;
import java.io.File;

import interfaces.Graph;

import models.*;
import solver.*;

public class Main {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("no instance file");
		} 
		else {
			try {
				if (args[0].equals("all")) {
					File directoryToScan = new File("instancesUniquePrct"); 
					for(File f : directoryToScan.listFiles()){
						//if(f.getName().contains("5")){
						Graph g = new LinkedListGraph(f.getPath(), true);
						Solver s = new EdmondsKarp(g);
						s.getResults();
						
						g = new LinkedListGraph(f.getPath(), true);
						s = new EdmondsKarpScaling(g);
						s.getResults();
						//}
					}
				}
				else {
					Graph g = new SparseMapGraph(args[0], true);
					System.out.println("Graphe crée");
					
					
					//Solver s = new PushRelabel(g);
					Solver s = new PushRelabel(g);
					
					s.getResults();
					/*
					g = new LinkedListGraph(args[0], true);
					s = new EdmondsKarp(g);
					
					Thread.sleep(10000);
					Graph g = new LinkedListGraph(args[0], true);
					Solver s = new PushRelabel(g);
					s.getResults();
					
					/*
					g = new SplitArrayGraph(args[0],true);
					s = new FordFulkersonScaling(g);
					s.getResults();
					*/
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
