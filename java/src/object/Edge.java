package object;

public class Edge {
	public int u;
	public int v;
	
	public Edge(int u, int v) {
		this.u=u;
		this.v=v;
	}
	@Override
	public boolean equals(Object o) {
		Edge c = (Edge)o;
		if (this.u==c.u && this.v==c.v) return true;
		return false;
	}
}
