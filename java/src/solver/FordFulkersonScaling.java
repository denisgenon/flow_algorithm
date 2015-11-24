package solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import interfaces.Graph;

public class FordFulkersonScaling extends AugmentingPathScaling{

	public int [] parents; // For getPath
	/**
	 * Just call the augmenting path algorithm
	 * @param g, the representation of the instance
	 */
	public FordFulkersonScaling(Graph g) {
		super(g);
	}

	/**
	 * We found a path with DFS in the residual graph
	 */
	public int [] getPath(int delta) {
		parents = new int [g.getV()];

		// Initialize values
		for(int i=0; i<g.getV(); i++) {
			parents[i]=-1;
		}

		visitDFS(0, delta);

		ArrayList<Integer> mypath = new ArrayList<Integer>();
		int indexpath = g.getV() - 1;
		mypath.add(indexpath); 
		while(parents[indexpath]!=-1 && indexpath!=0){
			mypath.add(parents[indexpath]); 
			indexpath=parents[indexpath];
		}

		if(indexpath==0) {
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

	public void visitDFS(int index, int delta) {
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
					if(g.getCapacity(current, v, 1)>= delta) { //We browse only the edge if the capacity is greater than the delta value
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
}