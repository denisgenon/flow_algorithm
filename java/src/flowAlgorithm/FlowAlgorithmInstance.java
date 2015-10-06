package flowAlgorithm;
import object.Tuple;
import object.Vertex;

public class FlowAlgorithmInstance {
	
	public Tuple[] capaMatrix;
	public Tuple[] bestflot;
	public Vertex [] vertices;
	public int V;
	public int E;
	
	public FlowAlgorithmInstance(Tuple [] capaMatrix, Vertex [] vertices, int V, int E) {
		this.capaMatrix = capaMatrix;
		this.vertices = vertices;
		this.V = V;
		this.E = E;
		bestflot = new Tuple[V];
	}
}
