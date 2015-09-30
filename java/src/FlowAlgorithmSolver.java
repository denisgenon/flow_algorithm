import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class FlowAlgorithmSolver {

	static FlowAlgorithmInstance instance;
	
	public void printMatrix(int [][] matrix) {
		// TODO
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("no instance file");
		} 
		else {
			try {
				
				instance = FlowAlgorithmParser.parse(args[0]);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
