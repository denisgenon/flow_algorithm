package flowAlgorithm;
import object.Node;
import object.Vertex;

public class FlowAlgorithmInstance {
	
	public Node[] capaMatrix;
	public Node[] bestflot;
	public Vertex [] vertices;
	public int V;
	public int E;
	
	public FlowAlgorithmInstance(Node [] capaMatrix, Vertex [] vertices, int V, int E) {
		this.capaMatrix = capaMatrix;
		this.vertices = vertices;
		this.V = V;
		this.E = E;
		bestflot = new Node[V];
	}
}
