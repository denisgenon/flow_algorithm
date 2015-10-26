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
						Graph g = new SplitArrayGraph(f.getPath());
						PushRelabel pr = new PushRelabel();
						pr.process(g);
						pr.getResult();
						//Graph g = new AdjacencyListGraph(f.getPath());
						//EdmondsKarp ek = new EdmondsKarp(g);
						//ek.getResult();
						//FordFulkerson ff = new FordFulkerson(g);
						//ff.getResult();
					}
				}

				else {
					Graph g = new SplitArrayGraph(args[0]);
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
