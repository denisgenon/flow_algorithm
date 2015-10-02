package object;
import java.util.ArrayList;


public class Vertex {
	public int id;
	public ArrayList<Vertex> adjacents;
	
	public Vertex(int id) {
		this.id = id;
		adjacents = new ArrayList<Vertex>();
	}
	
	@Override
	public String toString() {
		return Integer.toString(id);
	}
}
