package solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import interfaces.GraphIter;

public class FordFulkersonIter extends AugmentingPathIter{

	public int [] parents; // For getPath

	public FordFulkersonIter(GraphIter g) {
		super(g);
	}

	public int [] getPath() {

		// DFS

		parents = new int [g.getV()];

		for(int i=0; i<g.getV(); i++) {
			parents[i]=-1;
		}

		visitDFS(0);

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