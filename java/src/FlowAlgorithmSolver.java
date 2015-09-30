public class FlowAlgorithmSolver {

	static FlowAlgorithmInstance instance;
	
	public static void printMatrix(int [][] matrix) {
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("no instance file");
		} 
		else {
			try {
				instance = FlowAlgorithmParser.parse("input.txt");
				printMatrix(instance.distMatrix);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
