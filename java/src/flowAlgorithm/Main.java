package flowAlgorithm;
import java.io.File;

import interfaces.Graph;
import models.HashMapGraph;
import models.LinkedListGraph;
import models.SparseMapGraph;
import models.SplitArrayGraph;
import models.TreeMapGraph;
import solver.EdmondsKarp;
import solver.EdmondsKarpScaling;
import solver.FIFOPushRelabel;
import solver.HighestLabelPushRelabel;
import solver.PushRelabel;
import solver.Solver;

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
					System.out.println("GENERIC");
					System.out.println("HM");
					Graph g = new HashMapGraph(args[0], true);
					Solver s = new PushRelabel(g);
					s.getResults();
					System.out.println("SM");
					Graph g1 = new SparseMapGraph(args[0], true);
					Solver s1 = new PushRelabel(g1);
					s1.getResults();
					System.out.println("LL");
					Graph g2 = new LinkedListGraph(args[0], true);
					Solver s2 = new PushRelabel(g2);
					s2.getResults();
					System.out.println("SA");
					Graph g3 = new SplitArrayGraph(args[0], true);
					Solver s3 = new PushRelabel(g3);
					s3.getResults();
					System.out.println("TM");
					Graph g4 = new TreeMapGraph(args[0], true);
					Solver s4 = new PushRelabel(g4);
					s4.getResults();
					
					System.out.println("FIFO");
					System.out.println("HM");
					Graph gg = new HashMapGraph(args[0], true);
					Solver ss = new FIFOPushRelabel(gg);
					ss.getResults();
					System.out.println("SM");
					Graph gg1 = new SparseMapGraph(args[0], true);
					Solver ss1 = new FIFOPushRelabel(gg1);
					ss1.getResults();
					System.out.println("LL");
					Graph gg2 = new LinkedListGraph(args[0], true);
					Solver ss2 = new FIFOPushRelabel(gg2);
					ss2.getResults();
					System.out.println("SA");
					Graph gg3 = new SplitArrayGraph(args[0], true);
					Solver ss3 = new FIFOPushRelabel(gg3);
					ss3.getResults();
					System.out.println("TM");
					Graph gg4 = new TreeMapGraph(args[0], true);
					Solver ss4 = new FIFOPushRelabel(gg4);
					ss4.getResults();
					
					System.out.println("HIGHEST LABEL");
					System.out.println("HM");
					Graph ggg = new HashMapGraph(args[0], true);
					Solver sss = new HighestLabelPushRelabel(ggg);
					sss.getResults();
					System.out.println("SM");
					Graph ggg1 = new SparseMapGraph(args[0], true);
					Solver sss1 = new HighestLabelPushRelabel(ggg1);
					sss1.getResults();
					System.out.println("LL");
					Graph ggg2 = new LinkedListGraph(args[0], true);
					Solver sss2 = new HighestLabelPushRelabel(ggg2);
					sss2.getResults();
					System.out.println("SA");
					Graph ggg3 = new SplitArrayGraph(args[0], true);
					Solver sss3 = new HighestLabelPushRelabel(ggg3);
					sss3.getResults();
					System.out.println("TM");
					Graph ggg4 = new TreeMapGraph(args[0], true);
					Solver sss4 = new HighestLabelPushRelabel(ggg4);
					sss4.getResults();
					
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
