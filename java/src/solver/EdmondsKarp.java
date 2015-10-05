package solver;

import object.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;

import flowAlgorithm.FlowAlgorithmInstance;

public class EdmondsKarp extends FordFulkerson {
	private static final int INFINITY = Integer.MAX_VALUE;

	public EdmondsKarp(FlowAlgorithmInstance instance) {
		super(instance);
	}
	
	public Vertex [] getPath() {
		int[] distances = new int[super.instance.V];
		Vertex[] parent = new Vertex[super.instance.V];
		ArrayList<Vertex> path = new ArrayList<>();
		
		for (int i = 0; i < super.instance.V; i++) {
			distances[i] = INFINITY;
			parent[i] = null;
		}
		
		LinkedList<Vertex> queue = new LinkedList<>();
		
		// Sommet ?
		distances[0] = 0;
		queue.add(super.instance.vertices[0]);
		while (!queue.isEmpty()) {
			Vertex u = queue.removeFirst();
			for (Vertex v : u.adjacents) {
				if (distances[v.id] == INFINITY) {
					distances[v.id] = distances[u.id] + 1;
					parent[v.id] = u;
					queue.add(v);
				}
			}
		}
		int index = super.instance.V-1;
		path.add(super.instance.vertices[index]);
		while (parent[index] != null && index != 0) {
			path.add(super.instance.vertices[parent[index].id]);
			index = parent[index].id;
		}
		
		return index == 0 ? path.toArray(new Vertex[path.size()]) : null;
	}
}
