package solver;

import java.util.ArrayList;
import java.util.LinkedList;

import interfaces.Graph;

public class EdmondsKarp extends AugmentingPath {
	/**
	 * Just call the augmenting path algorithm
	 * @param g, the representation of the instance
	 */
	public EdmondsKarp(Graph g) {
		super(g, 0, g.getV()-1);
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
	public int getPath() {
		int[] parents = new int[g.getV()];
		int[] parentsCapacity = new int[g.getV()];
		parentsCapacity[source] = Integer.MAX_VALUE;
		
		// Initialize values
		for (int i = 0; i < super.g.getV(); i++) {
			parents[i] = -1;
		}
		
		// BFS
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(source);
		while (!queue.isEmpty() && parents[sink] == -1) {
			int u = queue.removeFirst();
			for(int v : g.getAdjacents(u)) {
				if (parents[v] == -1) {
					parents[v] = u;
					parentsCapacity[v] = Math.min(parentsCapacity[u], g.getCapacity(u, v));
					queue.add(v);
				}
			}
		}
		
		ArrayList<Integer> path = new ArrayList<>();
		int index = sink;
		path.add(index);
		while (parents[index] != -1 && index != source) {
			path.add(parents[index]);
			index = parents[index];
		}
		
		if(index == source) {
			applyPath(parentsCapacity[sink], path);
		}
		return parentsCapacity[sink];
	}
}
