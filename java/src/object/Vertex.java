package object;


public class Vertex {
	public int id;
	public int h;
	public int e;
	
	public Vertex(int id) {
		this.id = id;
		h = 0;
		e = 0;
	}
	
	@Override
	public boolean equals(Object o) {
		Vertex v = (Vertex) o;
		return this.id == v.id;
	}
	
	@Override
	public String toString() {
		return Integer.toString(id);
	}
}
