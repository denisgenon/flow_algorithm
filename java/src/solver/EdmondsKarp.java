package solver;

import java.util.ArrayList;
import java.util.LinkedList;

import interfaces.Graph;

public class EdmondsKarp extends AugmentingPath {
	private static final int INFINITY = Integer.MAX_VALUE;
	/**
	 * Just call the augmenting path algorithm
	 * @param g, the representation of the instance
	 */
	public EdmondsKarp(Graph g) {
		super(g,0,g.getV()-1);
	}
	
	/**
	 * Just call the augmenting path algorithm
	 * @param g, the representation of the instance
	 * @param source, the source node
	 * @param sink, the sink node
	 */
	public EdmondsKarp(Graph g, int source, int sink) {
		super(g, source, sink);
	}
	
	/**
	 * We found a path with BFS in the residual graph
	 */
	public int [] getPath() {
		int[] distances = new int[g.getV()];
		int[] parent = new int[g.getV()];
		ArrayList<Integer> path = new ArrayList<>();
		
		// Initialize values
		for (int i = 0; i < super.g.getV(); i++) {
			distances[i] = INFINITY;
			parent[i] = -1;
		}
		
		LinkedList<Integer> queue = new LinkedList<>();
		
		// BFS
		distances[source] = 0;
		queue.add(source);
		while (!queue.isEmpty()) {
			int u = queue.removeFirst();
			for(int i : g.getAdjacents(u)) {
				int v = i;
				if (distances[v] == INFINITY) {
					distances[v] = distances[u] + 1;
					parent[v] = u;
					queue.add(v);
				}
			}
		}
		
		int index = sink;
		path.add(index);
		while (parent[index] != -1 && index != source) {
			path.add(parent[index]);
			index = parent[index];
		}
		
		if(index == source) {
			int [] res = new int [path.size()];
			for (int i = 0; i < res.length; i++) {
				res[i] = path.get(i);
			}
			return res;
		}
		return null;
	}
}
