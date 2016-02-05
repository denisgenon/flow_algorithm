package solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import interfaces.Graph;

public class FordFulkerson extends AugmentingPath{

	public int [] parents; // For getPath
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
	public int [] getPath() {
		parents = new int [g.getV()];

		// Initialize values
		for(int i=0; i<g.getV(); i++) {
			parents[i]=-1;
		}

		visitDFS(source);

		ArrayList<Integer> mypath = new ArrayList<Integer>();
		int indexpath = sink;
		mypath.add(indexpath); 
		while(parents[indexpath]!=-1 && indexpath != source){
			mypath.add(parents[indexpath]); 
			indexpath=parents[indexpath];
		}

		if(indexpath == source) {
			int [] res = new int [mypath.size()];
			int index=0;
			for(int i : mypath){
				res[index]=i;
				index++;
			}
			return res;
		}
		return null;
	}

	public void visitDFS(int index) {
		Set<Integer> set = new HashSet<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(index);
		boolean flag = true;
		while(!stack.isEmpty() && flag){
			int current = stack.pop();
			if(current==g.getV()-1){
				flag=false;
			}
			else {
				set.add(current);
				for(int i : g.getAdjacents(current)){
					int v = i;
					if(!set.contains(v)) {
						parents[v]=current;
						if (!set.contains(v)) {
							stack.push(v);
						}
					}
				}
			}
		}
	}
}