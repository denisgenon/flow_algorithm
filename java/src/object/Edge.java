package object;

public class Edge {
	public int u;
	public int v;
	public int capacity;
	
	public Edge(int u, int v, int capacitymax) {
		this.u=u;
		this.v=v;
		capacity = (int) (Math.random()*(capacitymax))+1;
	}
	@Override
	public boolean equals(Object o) {
		Edge c = (Edge)o;
		if (this.u==c.u && this.v==c.v) return true;
		return false;
	}
}
