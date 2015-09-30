import java.io.BufferedReader;
import java.io.FileReader;

public class FlowAlgorithmParser {
	public static FlowAlgorithmInstance parse(String filepath) throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		
		String line = br.readLine();

		// Number of items
		String[] data = line.split(" ");
		int V = Integer.parseInt(data[0]);
		int E = Integer.parseInt(data[1]);
		int [][] distMatrix = new int [V][V];
		// Parse the items
		for (int i = 0; i < E; i++) {
			line = br.readLine();
			data = line.split(" ");
			distMatrix[Integer.parseInt(data[0])][Integer.parseInt(data[1])]=Integer.parseInt(data[2]);
		}
		
		br.close();
		
		FlowAlgorithmInstance instance = new  FlowAlgorithmInstance(distMatrix);
		return instance;
	}
}
