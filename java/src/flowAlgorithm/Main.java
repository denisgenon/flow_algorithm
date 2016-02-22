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
					File directoryToScan = new File("instancesPrct"); 
					for(File f : directoryToScan.listFiles()){
						//if(f.getName().contains("5")){
							System.out.println(f.getName()+" : ");
							Graph g = new SplitArrayGraph(f.getPath(),true);
							Solver s = new EdmondsKarp(g);
							s.getResults();
						//}
					}
				}
				else {
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
