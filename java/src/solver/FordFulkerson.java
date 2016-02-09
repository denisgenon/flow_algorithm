package solver;

import java.util.ArrayList;
import java.util.Stack;

import interfaces.Graph;

public class FordFulkerson extends AugmentingPath{
	/**
	 * Just call the augmenting path algorithm
	 * @param g, the representation of the instance
	 */
	public FordFulkerson(Graph g) {
		super(g,0,g.getV()-1);
	}
	
	/**
	 * Just call the augmenting path algorithm
	 * @param g, the representation of the instance
	 * @param source, the source node
	 * @param sink, the sink node
	 */
	public FordFulkerson(Graph g, int source, int sink) {
		super(g, source, sink);
	}
	
	/**
	 * We found a path with DFS in the residual graph
	 */
	public int getPath() {
		int[] parents = new int[g.getV()];
		int[] parentsCapacity = new int[g.getV()];
		parentsCapacity[source] = Integer.MAX_VALUE;

		// Initialize values
		for (int i = 0; i < g.getV(); i++) {
			parents[i] = -1;
		}

		// DFS
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(source);
		while(!stack.isEmpty() && parents[sink] == -1){
			int u = stack.pop();
			for(int v : g.getAdjacents(u)){
				if(parents[v] == -1) {
					parents[v] = u;
					parentsCapacity[v] = Math.min(parentsCapacity[u], g.getCapacity(u, v));
					stack.push(v);
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