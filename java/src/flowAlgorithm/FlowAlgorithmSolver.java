package flowAlgorithm;
import object.Vertex;
import solver.EdmondsKarp;
import solver.FordFulkerson;

public class FlowAlgorithmSolver {
	
	public static void printMatrix(int [][] matrix) {
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void printPath(Vertex [] mypath) {
		for(Vertex v : mypath){
			System.out.print(v + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("no instance file");
		} 
		else {
			try {
				//FordFulkerson ff = new FordFulkerson(FlowAlgorithmParser.parse(args[0]));
				//ff.getResult();
				EdmondsKarp ek = new EdmondsKarp(FlowAlgorithmParser.parse(args[0]));
				ek.getResult();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
