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
		Vertex [] vertices = new Vertex[V];
		
		// Parse the items
		for (int i = 0; i < E; i++) {
			line = br.readLine();
			data = line.split(" ");
			int idVertex1 = Integer.parseInt(data[0]);
			int idVertex2 = Integer.parseInt(data[1]);
			int capa = Integer.parseInt(data[2]);
			
			// On ajoute les nouveaux vertices
			if(vertices[idVertex1] == null) {
				vertices[idVertex1] = new Vertex(idVertex1);
			}
			if(vertices[idVertex2] == null) {
				vertices[idVertex2] = new Vertex(idVertex2);
			}
			
			// On ajoute les voisins dans les vertices
			vertices[idVertex1].adjacents.add(vertices[idVertex2]);
			
			// On ajoute la distance dans la matrice des distances
			distMatrix[idVertex1][idVertex2] = capa;
			
		}
		
		br.close();
		
		return new FlowAlgorithmInstance(distMatrix, vertices, V, E);
	}
}
