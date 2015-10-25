package solver;

import interfaces.AugmentingPathGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import object.Vertex;

public class FordFulkerson {
	public AugmentingPathGraph g;
	public int [] parents; // For getPath
	public long timeStart;
	
	public FordFulkerson(AugmentingPathGraph g) {
		this.g = g;
		timeStart=System.currentTimeMillis();
		Vertex [] myPath = getPath();
		while(myPath!=null) {
			g.applyPath(g.getMinFlow(myPath),myPath);
			myPath = getPath();
		}
	}
	
	public void getResult() {
		System.out.println("|V| : "+g.getV());
		System.out.println("|E| : "+g.getE());
		System.out.println("Max flot : " + g.getFlowValue());
		System.out.println("Temps d'éxecution : "+(System.currentTimeMillis()-timeStart)+" ms"+"\n");
	}
	
	public Vertex [] getPath() {
		
		parents = new int [g.getV()];
		
		for(int i=0; i<g.getV(); i++) {
			parents[i]=-1;
		}
		
		visitDFS(0);

		ArrayList<Vertex> mypath = new ArrayList<Vertex>();
		int indexpath = g.getV() - 1;
		mypath.add(g.getVertex(indexpath)); // TODO Cast Vertex ?
		while(parents[indexpath]!=-1 && indexpath!=0){
			mypath.add(g.getVertex(parents[indexpath])); // TODO Cast Vertex ?
			indexpath=parents[indexpath];
		}
		if(indexpath==0) return mypath.toArray(new Vertex [mypath.size()]);
		return null;
	}
	
	public void visitDFS(int index) {
		Set<Integer> set = new HashSet<Integer>();
	    Stack<Integer> stack = new Stack<Integer>();
	    stack.push(index);
	    while(!stack.isEmpty()){
	    	int current = stack.pop();
	    	set.add(current);
	    	for(Vertex v : g.getVertex(current).adjacents) {
				if(!set.contains(v.id)) {
					parents[v.id]=current;
					if (!set.contains(v.id)) {
		                stack.push(v.id);
		            }
				}
			}
	    }
	}
}
