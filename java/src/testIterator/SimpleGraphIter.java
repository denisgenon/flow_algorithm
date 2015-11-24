package testIterator;

import java.util.HashMap;

import object.Vertex;

public abstract class SimpleGraphIter {
	public Vertex [] vertices;
	public HashMap<Arc, Integer> capacities;
	public HashMap<Arc, Integer> capacitiesBestFlow;
	public int V;
	public int E;
	
	public int getV() {
		return V;
	}

	public int getE() {
		return E;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public Vertex getVertex(int id) {
		return vertices[id];
	}
	
	public int getCapacity(int u, int v, int type) {
		if (type==1) return capacities.get(new Arc(u, v));
		if (type==2) return capacitiesBestFlow.get(new Arc(u, v));
		return -1;
	}
	
	public void setCapacity(int u, int v, int capa, int type) {	
		if (type==1) capacities.put(new Arc(u, v), capa);
		if (type==2) capacitiesBestFlow.put(new Arc(u, v), capa);
	}

}
