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
	 * 			args[1] pour la structure de données
	 * 				LL : LinkedList
	 * 				HM : HashMap
	 * 				SA : SplitArray
	 * 				TM : TreeMap
	 * 			args[2] pour l'instance
	 */
	public static void main(String[] args) {

		Graph g;
		Solver s;

		if(args[1].equals("LL")) g = new LinkedListGraph(args[2],true);
		else if(args[1].equals("HM")) g = new HashMapGraph(args[2],true);
		else if(args[1].equals("SA")) g = new SplitArrayGraph(args[2],true);
		else if(args[1].equals("TM")) g = new TreeMapGraph(args[2],true);
		else g = new LinkedListGraph(args[2],true);

		if(args[0].equals("FF")) s = new FordFulkersonScaling(g);
		else if(args[0].equals("EK")) s = new EdmondsKarp(g);
		else if(args[0].equals("PR")) s = new PushRelabel(g);
		else s = new EdmondsKarp(g);

		s.getTime();
	}
}