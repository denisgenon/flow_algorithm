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
					System.out.println(f.getName()+" : ");
					System.out.println("SplitArray");
					System.out.println("    Push Relabel :");
					Graph g = new SplitArrayGraph(f.getPath());
					PushRelabel pr = new PushRelabel();
					pr.process(g);
					pr.getResult();
					
					/*System.out.println("    Ford Fulkerson :");
					g = new SplitArrayGraph(f.getPath());
					FordFulkerson ff = new FordFulkerson(g);
					ff.getResult();*/
					
					System.out.println("    Edmonds Karp :");
					g = new SplitArrayGraph(f.getPath());
					EdmondsKarp ek = new EdmondsKarp(g);
					ek.getResult();
					
					System.out.println("-------------");
					System.out.println("HashMap");
					System.out.println("    Push Relabel :");
					g = new HashMapGraph(f.getPath());
					pr = new PushRelabel();
					pr.process(g);
					pr.getResult();
					
					/*System.out.println("    Ford Fulkerson :");
					g = new HashMapGraph(f.getPath());
					ff = new FordFulkerson(g);
					ff.getResult();*/
					
					System.out.println("    Edmonds Karp :");
					g = new HashMapGraph(f.getPath());
					ek = new EdmondsKarp(g);
					ek.getResult();
					
					System.out.println("-------------");
					System.out.println("AdjacencyList");
					System.out.println("    Push Relabel :");
					g = new SplitArrayGraph(f.getPath());
					pr = new PushRelabel();
					pr.process(g);
					pr.getResult();
					
					/*System.out.println("    Ford Fulkerson :");
					g = new SplitArrayGraph(f.getPath());
					ff = new FordFulkerson(g);
					ff.getResult();*/
					
					System.out.println("    Edmonds Karp :");
					g = new SplitArrayGraph(f.getPath());
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
						Graph g = new SplitArrayGraph(f.getPath());
						/*PushRelabel pr = new PushRelabel();
						pr.process(g);
						pr.getResult();*/
						EdmondsKarp ek = new EdmondsKarp(g);
						ek.getResult();

						//FordFulkerson ff = new FordFulkerson(g);
						//ff.getResult();
					}
				}
				if(args[0].equals("bigTest")){
					bigTest();
				}
				else {
					
					System.out.println("Push Relabel :");
					Graph g = new SplitArrayGraph(args[0]);
					PushRelabel pr = new PushRelabel();
					pr.process(g);
					pr.getResult();
					
					System.out.println("Ford Fulkerson :");
					g = new SplitArrayGraph(args[0]);
					FordFulkerson ff = new FordFulkerson(g);
					ff.getResult();
					
					System.out.println("Edmonds Karp :");
					g = new SplitArrayGraph(args[0]);
					EdmondsKarp ek = new EdmondsKarp(g);
					ek.getResult();
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
