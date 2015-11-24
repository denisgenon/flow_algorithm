package models;

import object.Vertex;

public abstract class SimpleGraph {
	public Vertex [] vertices;
	public int V;
	public int E;
	public int maxCapa;
	
	public int getV() {
		return V;
	}

	public int getE() {
		return E;
	}
	
	public int getMaxCapacity() {
		return maxCapa;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public Vertex getVertex(int id) {
		return vertices[id];
	}

}
