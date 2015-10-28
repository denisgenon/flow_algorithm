package solver;

import interfaces.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import object.Vertex;

public class FordFulkerson {
	public Graph g;
	public int [] parents; // For getPath
	public long timeStart;
	
	public FordFulkerson(Graph g) {
		this.g = g;
		timeStart=System.currentTimeMillis();
		Vertex [] myPath = getPath();
		while(myPath!=null) {
			applyPath(getMinFlow(myPath),myPath);
			myPath = getPath();
		}
	}
	
	public int getMinFlow(Vertex[] path) {
		int minFlow=Integer.MAX_VALUE;
		for(int i=0; i<path.length-1; i++) {
			minFlow=Math.min(minFlow,g.getCapacity(path[i+1], path[i],1));
		}
		return minFlow;
	}
	
	public void getResult() {
		System.out.println("|V| : "+g.getV());
		System.out.println("|E| : "+g.getE());
		System.out.println("Max flot : " + g.getFlowValue(1));
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
	
	public void applyPath(int capacity, Vertex[] path) {
		for(int i=0; i<path.length-1; i++) {
			int currentCapa = g.getCapacity(path[i+1],  path[i],1);
			if(currentCapa<=capacity) { // On enleve l'arete si la capa dispo est 0
				g.removeEdge(path[i+1], path[i]);
			}
			else { // on enleve la capa dans le bon sens sinon
				g.setCapacity(path[i+1], path[i], currentCapa-capacity,1);
			}

			currentCapa = g.getCapacity(path[i],path[i+1],1);
			if(currentCapa==-1) { // on crée l'arete si elle n'existe pas
				g.addEdge(path[i], path[i+1], capacity,1);
			}
			else { // on rajoute la capa dans le sens inverse sinon
				g.setCapacity(path[i], path[i+1], currentCapa+capacity,1);
			}
			
			currentCapa = g.getCapacity(path[i], path[i+1], 2); // on augmente le flot courant	
			if(currentCapa==-1) g.addEdge(path[i], path[i+1], capacity, 2); 
			else {
				g.setCapacity(path[i], path[i+1], currentCapa+capacity,2);
			}
			
		}
	}
	
	public void visitDFS(int index) {
		Set<Integer> set = new HashSet<Integer>();
	    Stack<Integer> stack = new Stack<Integer>();
	    stack.push(index);
	    while(!stack.isEmpty()){
	    	int current = stack.pop();
	    	set.add(current);
	    	for(Vertex v : g.getAdjacents(g.getVertex(current))) {
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
