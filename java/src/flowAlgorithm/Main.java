package flowAlgorithm;
import java.io.File;

import interfaces.Graph;
import models.*;
import solver.*;
import testIterator.AdjacencyListGraphIterator;
import testIterator.EdmondsKarpIterator;
import testIterator.GraphIterator;

public class Main {

	public static void bigTest(){
		try {
			File directoryToScan = new File("instances"); 
			for(File f : directoryToScan.listFiles()){
				System.out.println(f.getName()+" : ");
				System.out.println("SplitArray");
				System.out.println("    Push Relabel :");
				Graph g = new SplitArrayGraph(f.getPath());
				PushRelabel pr = new PushRelabel(g);
				pr.getResult();

				System.out.println("    Ford Fulkerson :");
				g = new SplitArrayGraph(f.getPath());
				FordFulkerson ff = new FordFulkerson(g);
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

				System.out.println("    Ford Fulkerson :");
				g = new HashMapGraph(f.getPath());
				ff = new FordFulkerson(g);
				ff.getResult();

				System.out.println("    Edmonds Karp :");
				g = new HashMapGraph(f.getPath());
				ek = new EdmondsKarp(g);
				ek.getResult();

				System.out.println("-------------");
				System.out.println("AdjacencyList");
				System.out.println("    Push Relabel :");
				g = new SplitArrayGraph(f.getPath());
				pr = new PushRelabel(g);
				pr.getResult();

				System.out.println("    Ford Fulkerson :");
				g = new SplitArrayGraph(f.getPath());
				ff = new FordFulkerson(g);
				ff.getResult();

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
						//if(!f.getName().contains("5") && !f.getName().contains("7")){
							long moyenne=0;
							for(int i=0; i<10; i++){
								long timeStart=System.currentTimeMillis();
								GraphIterator g = new AdjacencyListGraphIterator(f.getPath());
								EdmondsKarpIterator ek = new EdmondsKarpIterator(g);
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
					GraphIterator g = new AdjacencyListGraphIterator(args[0]);
					EdmondsKarpIterator ek = new EdmondsKarpIterator(g);
					ek.getResult();
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
