package flowAlgorithm;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

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
				PushRelabel pr = new PushRelabel(g);
				pr.getResult();

				System.out.println("    Ford Fulkerson (scaling) :");
				g = new SplitArrayGraph(f.getPath());
				FordFulkersonScaling ff = new FordFulkersonScaling(g);
				ff.getResult();

				System.out.println("    Edmonds Karp :");
				g = new SplitArrayGraph(f.getPath());
				EdmondsKarp ek = new EdmondsKarp(g);
				ek.getResult();

				System.out.println("-------------");
				System.out.println("HashMap");
				System.out.println("    Push Relabel :");
				g = new HashMapGraph(f.getPath());
				pr = new PushRelabel(g);
				pr.getResult();

				System.out.println("    Ford Fulkerson (scaling) :");
				g = new HashMapGraph(f.getPath());
				ff = new FordFulkersonScaling(g);
				ff.getResult();

				System.out.println("    Edmonds Karp :");
				g = new HashMapGraph(f.getPath());
				ek = new EdmondsKarp(g);
				ek.getResult();

				System.out.println("-------------");
				System.out.println("AdjacencyList");
				System.out.println("    Push Relabel :");
				g = new LinkedListGraph(f.getPath());
				pr = new PushRelabel(g);
				pr.getResult();

				System.out.println("    Ford Fulkerson (scaling) :");
				g = new LinkedListGraph(f.getPath());
				ff = new FordFulkersonScaling(g);
				ff.getResult();

				System.out.println("    Edmonds Karp :");
				g = new LinkedListGraph(f.getPath());
				ek = new EdmondsKarp(g);
				ek.getResult();
				
				System.out.println("-------------");
				System.out.println("TreeMap");
				System.out.println("    Push Relabel :");
				g = new TreeMapGraph(f.getPath());
				pr = new PushRelabel(g);
				pr.getResult();

				System.out.println("    Ford Fulkerson (scaling) :");
				g = new TreeMapGraph(f.getPath());
				ff = new FordFulkersonScaling(g);
				ff.getResult();

				System.out.println("    Edmonds Karp :");
				g = new TreeMapGraph(f.getPath());
				ek = new EdmondsKarp(g);
				ek.getResult();

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
						System.out.println(f.getName()+" : ");
						//if(!f.getName().contains("5") && !f.getName().contains("7")){
							long moyenne=0;
							for(int i=0; i<10; i++){
								long timeStart=System.currentTimeMillis();
								Graph g = new TreeMapGraph(f.getPath());
								FordFulkersonScaling ek = new FordFulkersonScaling(g);
								moyenne+=System.currentTimeMillis()-timeStart;
							}
							System.out.println("Moyenne : "+moyenne/10);
												//}
					}
				}
				else if(args[0].equals("bigTest")){
					bigTest();
				}
				else {
					Graph g = new HashMapGraph(args[0]);
					FordFulkersonScaling ek = new FordFulkersonScaling(g);
					ek.getResult();
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
