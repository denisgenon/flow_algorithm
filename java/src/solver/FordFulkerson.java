package solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import interfaces.Graph;

public class FordFulkerson extends AugmentingPath{
	
	public int [] parents; // For getPath
	
	public FordFulkerson(Graph g) {
		super(g);
	}

	public int [] getPath() {

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
		while(!stack.isEmpty()){
			int current = stack.pop();
			set.add(current);
			Iterator<Integer> iterator = g.getAdjacents(current).iterator();
			while(iterator.hasNext()) {
				int v = iterator.next();
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