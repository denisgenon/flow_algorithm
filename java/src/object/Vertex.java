package object;
import java.util.ArrayList;


public class Vertex {
	public int id;
	public ArrayList<Vertex> adjaDijkstra;
	public int h;
	public int e;
	
	public Vertex(int id) {
		this.id = id;
		adjaDijkstra = new ArrayList<Vertex>();
		h=0;
		e=0;
	}
	
	@Override
	public boolean equals(Object o) {
		Vertex v = (Vertex)o;
		return this.id==v.id;
	}
	
	@Override
	public String toString() {
		return Integer.toString(id);
	}
}
