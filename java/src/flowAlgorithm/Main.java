package flowAlgorithm;
import java.io.File;

import interfaces.Graph;
import models.*;
import solver.*;

public class Main {

	public static void bigTest(){
		try {
			File directoryToScan = new File("instances"); 

			for(File f : directoryToScan.listFiles()){
				System.out.println("----> "+f.getName()+" <----");

				System.out.println("SplitArray");
				System.out.println("    Push Relabel :");
				Graph g = new SplitArrayGraph(f.getPath());
				Solver s = new PushRelabel(g);
				s.getResults();

				System.out.println("    Ford Fulkerson (scaling) :");
				g = new SplitArrayGraph(f.getPath());
				s = new FordFulkersonScaling(g);
				s.getResults();

				System.out.println("    Edmonds Karp :");
				g = new SplitArrayGraph(f.getPath());
				s = new EdmondsKarp(g);
				s.getResults();

				System.out.println("-------------");
				System.out.println("HashMap");
				System.out.println("    Push Relabel :");
				g = new HashMapGraph(f.getPath());
				s = new PushRelabel(g);
				s.getResults();

				System.out.println("    Ford Fulkerson (scaling) :");
				g = new HashMapGraph(f.getPath());
				s = new FordFulkersonScaling(g);
				s.getResults();

				System.out.println("    Edmonds Karp :");
				g = new HashMapGraph(f.getPath());
				s = new EdmondsKarp(g);
				s.getResults();

				System.out.println("-------------");
				System.out.println("AdjacencyList");
				System.out.println("    Push Relabel :");
				g = new LinkedListGraph(f.getPath());
				s = new PushRelabel(g);
				s.getResults();

				System.out.println("    Ford Fulkerson (scaling) :");
				g = new LinkedListGraph(f.getPath());
				s = new FordFulkersonScaling(g);
				s.getResults();

				System.out.println("    Edmonds Karp :");
				g = new LinkedListGraph(f.getPath());
				s = new EdmondsKarp(g);
				s.getResults();

				System.out.println("-------------");
				System.out.println("TreeMap");
				System.out.println("    Push Relabel :");
				g = new TreeMapGraph(f.getPath());
				s = new PushRelabel(g);
				s.getResults();

				System.out.println("    Ford Fulkerson (scaling) :");
				g = new TreeMapGraph(f.getPath());
				s = new FordFulkersonScaling(g);
				s.getResults();

				System.out.println("    Edmonds Karp :");
				g = new TreeMapGraph(f.getPath());
				s = new EdmondsKarp(g);
				s.getResults();

				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
						//if(f.getName().contains("5")){
							System.out.println(f.getName()+" : ");
							Graph g = new LinkedListGraph(f.getPath());
							Solver s = new FordFulkersonScaling(g);
							s.getResults();
						//}
					}
				}
				else if(args[0].equals("bigTest")){
					bigTest();
				}
				else {
					Graph g1 = new LinkedListGraph(args[0]);
					//Graph g2 = new HashMapGraph("file_path");
					
					Solver s1 = new FordFulkersonScaling(g1);
					//Solver s2 = new PushRelabel(g2);
					
					s1.getResults();
					//s2.getResults();
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
