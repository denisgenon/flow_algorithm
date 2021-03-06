package flowAlgorithm;

import interfaces.Graph;

import models.*;
import solver.*;

public class BatchMain {
	/**
	 * 
	 * @param 	args[0] pour l'algo
	 * 				FF : Ford Fulkerson Scaling
	 * 				EK : Edmonds Karp
	 * 				PR : Push Relabel
	 * 				FPR : FIFO Push Relabel
	 * 				HLPR : Highest Label Push Relabel
	 * 			args[1] pour la structure de données
	 * 				LL : LinkedList
	 * 				HM : HashMap
	 * 				SA : SplitArray
	 * 				TM : TreeMap
	 * 				SM : SparseMap
	 * 			args[2] pour l'instance
	 * 			args[3] pour ordonné
	 */
	public static void main(String[] args) {

		Graph g;
		Solver s;

		if(args[1].equals("LL")) g = new LinkedListGraph(args[2],Boolean.parseBoolean(args[3]));
		else if(args[1].equals("HM")) g = new HashMapGraph(args[2],Boolean.parseBoolean(args[3]));
		else if(args[1].equals("SA")) g = new SplitArrayGraph(args[2],Boolean.parseBoolean(args[3]));
		else if(args[1].equals("TM")) g = new TreeMapGraph(args[2],Boolean.parseBoolean(args[3]));
		else if(args[1].equals("SM")) g = new SparseMapGraph(args[2],Boolean.parseBoolean(args[3]));
		else g = null;

		if(args[0].equals("FF")) s = new FordFulkersonScaling(g);
		else if(args[0].equals("EK")) s = new EdmondsKarp(g);
		else if(args[0].equals("PR")) s = new PushRelabel(g);
		else if(args[0].equals("FPR")) s = new FIFOPushRelabel(g);
		else if(args[0].equals("HLPR")) s = new HighestLabelPushRelabel(g);
		else if(args[0].equals("PRI")) s = new PushRelabelInit(g);
		else if(args[0].equals("FPRI")) s = new FIFOPushRelabelInit(g);
		else if(args[0].equals("HLPRI")) s = new HighestLabelPushRelabelInit(g);
		else s = null;

		if(args.length==5 && args[4].equals("r")){
			s.getResults();
		}
		else {
			s.getTime();
		}
	}
}
