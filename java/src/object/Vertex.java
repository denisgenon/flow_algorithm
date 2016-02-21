package object;


public class Vertex implements Comparable<Vertex> {
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

	@Override
	public int compareTo(Vertex u) {
		if (this.e > u.e) return 1;
		else if (this.e == u.e) return 0;
		else return -1;
	}
}
