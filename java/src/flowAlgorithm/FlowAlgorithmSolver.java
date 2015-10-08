package flowAlgorithm;
import object.Node;
import object.Vertex;
import solver.EdmondsKarp;
import solver.FordFulkerson;

@SuppressWarnings("unused")
public class FlowAlgorithmSolver {

	public static void printPath(Vertex [] mypath) {
		for(Vertex v : mypath){
			System.out.print(v + " ");
		}
		System.out.println();
	}
	
	public static void printNodes(Node [] capaMatrix) {
		for(int i=0; i<capaMatrix.length; i++) {
			Node current = capaMatrix[i];
			System.out.print(i+" : ");
			while(current!=null){
				System.out.print(current+ " ");
				current=current.next;
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("no instance file");
		} 
		else {
			try {
				/*FordFulkerson ff = new FordFulkerson(FlowAlgorithmParser.parse(args[0]));
				ff.getResult();*/
				EdmondsKarp ek = new EdmondsKarp(FlowAlgorithmParser.parse(args[0]));
				ek.getResult();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
