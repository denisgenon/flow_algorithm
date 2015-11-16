package solver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import interfaces.Graph;

public class EdmondsKarp extends AugmentingPath {
	private static final int INFINITY = Integer.MAX_VALUE;

	public EdmondsKarp(Graph g) {
		super(g);
	}
	
	public int [] getPath() {
		int[] distances = new int[g.getV()];
		int[] parent = new int[g.getV()];
		ArrayList<Integer> path = new ArrayList<>();
		
		for (int i = 0; i < super.g.getV(); i++) {
			distances[i] = INFINITY;
			parent[i] = -1;
		}
		
		LinkedList<Integer> queue = new LinkedList<>();
		
		// Sommet ?
		distances[0] = 0;
		queue.add(0);
		while (!queue.isEmpty()) {
			int u = queue.removeFirst();
			Iterator<Integer> iterator = g.getAdjacents(u);
			while(iterator.hasNext()) {
				int v = iterator.next();
				if (distances[v] == INFINITY) {
					distances[v] = distances[u] + 1;
					parent[v] = u;
					queue.add(v);
				}
			}
		}
		int index = g.getV() - 1;
		path.add(index);
		while (parent[index] != -1 && index != 0) {
			path.add(parent[index]);
			index = parent[index];
		}
		
		if(index==0) {
			int [] res = new int [path.size()];
			int ind=0;
			for(int i : path){
				res[ind]=i;
				ind++;
			}
			return res;
		}
		return null;
	}
}
