import java.util.ArrayList;


public class Vertex {
	int id;
	ArrayList<Vertex> adjacents;
	
	public Vertex(int id) {
		this.id = id;
		adjacents = new ArrayList<Vertex>();
	}
	
	@Override
	public String toString() {
		return Integer.toString(id);
	}
}
