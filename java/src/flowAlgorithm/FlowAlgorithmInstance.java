package flowAlgorithm;
import object.Vertex;

public class FlowAlgorithmInstance {
	
	public int [][] distMatrix;
	public int [][] bestflot;
	public Vertex [] vertices;
	public int V;
	public int E;
	
	public FlowAlgorithmInstance(int [][] distMatrix, Vertex [] vertices, int V, int E) {
		this.distMatrix = distMatrix;
		this.vertices = vertices;
		this.V = V;
		this.E = E;
		bestflot = new int [V][V];
	}
}
