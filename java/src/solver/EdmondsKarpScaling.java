package solver;

import java.util.ArrayList;
import java.util.LinkedList;

import interfaces.Graph;

public class EdmondsKarpScaling extends AugmentingPathScaling {

	public EdmondsKarpScaling(Graph g, int source, int sink) {
		super(g, source, sink);
	}
	
	public EdmondsKarpScaling(Graph g) {
		super(g,0,g.getV()-1);
	}

	@Override
	public int getPath(int delta) {
		int[] parents = new int[g.getV()];
		int[] parentsCapacity = new int[g.getV()];
		parentsCapacity[source] = Integer.MAX_VALUE;

		// Initialize values
		for (int i = 0; i < g.getV(); i++) {
			parents[i] = -1;
		}

		// BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.push(source);
		while(!queue.isEmpty() && parents[sink] == -1){
			int u = queue.pop();
			for(int v : g.getAdjacents(u)) {
				if(g.getCapacity(u, v) >= delta) {
					if(parents[v] == -1) {
						parents[v] = u;
						parentsCapacity[v] = Math.min(parentsCapacity[u], g.getCapacity(u, v));
						queue.push(v);
					}
				}
			}
		}

		ArrayList<Integer> path = new ArrayList<Integer>();
		int index = sink;
		path.add(index); 
		while(parents[index] != -1 && index != source){
			path.add(parents[index]); 
			index = parents[index];
		}

		if(index == source) {
			applyPath(parentsCapacity[sink], path);
		}
		return parentsCapacity[sink];
	}

}
