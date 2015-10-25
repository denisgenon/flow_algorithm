package solver;

import interfaces.Graph;

import java.util.ArrayList;
import java.util.LinkedList;

import object.Vertex;

public class EdmondsKarp extends FordFulkerson {
	private static final int INFINITY = Integer.MAX_VALUE;

	public EdmondsKarp(Graph g) {
		super(g);
	}
	
	public Vertex [] getPath() {
		int[] distances = new int[super.g.getV()];
		Vertex[] parent = new Vertex[super.g.getV()];
		ArrayList<Vertex> path = new ArrayList<>();
		
		for (int i = 0; i < super.g.getV(); i++) {
			distances[i] = INFINITY;
			parent[i] = null;
		}
		
		LinkedList<Vertex> queue = new LinkedList<>();
		
		// Sommet ?
		distances[0] = 0;
		queue.add(super.g.getVertex(0));
		while (!queue.isEmpty()) {
			Vertex u = queue.removeFirst();
			for (Vertex v : g.getAdjacents(u)) {
				if (distances[v.id] == INFINITY) {
					distances[v.id] = distances[u.id] + 1;
					parent[v.id] = u;
					queue.add(v);
				}
			}
		}
		int index = super.g.getV() - 1;
		path.add(super.g.getVertex(index));
		while (parent[index] != null && index != 0) {
			path.add(super.g.getVertex(parent[index].id));
			index = parent[index].id;
		}
		
		return index == 0 ? path.toArray(new Vertex[path.size()]) : null;
	}
}
