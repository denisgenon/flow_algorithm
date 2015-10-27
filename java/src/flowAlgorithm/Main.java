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
				if(args[0].equals("all")) {
					File directoryToScan = new File("instances"); 
					for(File f : directoryToScan.listFiles()){
						System.out.println(f.getName()+" : ");
						Graph g = new HashMapGraph(f.getPath());
						PushRelabel pr = new PushRelabel();
						pr.process(g);
						pr.getResult();
						//Graph g = new SplitArrayGraph(f.getPath());
						//EdmondsKarp ek = new EdmondsKarp(g);
						//ek.getResult();
						//FordFulkerson ff = new FordFulkerson(g);
						//ff.getResult();
					}
				}

				else {
					
					System.out.println("Push Relabel :");
					Graph g = new HashMapGraph(args[0]);
					PushRelabel pr = new PushRelabel();
					pr.process(g);
					pr.getResult();
					
					System.out.println("Ford Fulkerson :");
					g = new HashMapGraph(args[0]);
					FordFulkerson ff = new FordFulkerson(g);
					ff.getResult();
					
					System.out.println("Edmonds Karp :");
					g = new HashMapGraph(args[0]);
					EdmondsKarp ek = new EdmondsKarp(g);
					ek.getResult();
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
