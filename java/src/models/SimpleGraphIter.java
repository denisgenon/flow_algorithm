package models;

import java.util.HashMap;

import object.Arc;
import object.Vertex;

public abstract class SimpleGraphIter {
	public Vertex [] vertices;
	public HashMap<Arc, Integer> capacities;
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
	
	public int getCapacity(int u, int v) {
		return capacities.get(new Arc(u, v));
	}
	public void setCapacity(int u, int v, int capa) {
		capacities.put(new Arc(u, v), capa);
	}

}
