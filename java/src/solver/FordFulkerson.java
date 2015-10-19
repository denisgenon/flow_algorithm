package solver;

import java.util.ArrayList;

import models.Graph;
import object.Vertex;

public class FordFulkerson {
	public Graph g;
	// For getPath
	public int [] colors;
	public int [] parents;
	
	public FordFulkerson(Graph g) {
		this.g = g;
		Vertex [] myPath = getPath();
		while(myPath!=null) {
			g.applyPath(g.getMinFlow(myPath),myPath);
			myPath = getPath();
		}
	}
	
	public void getResult() {
		System.out.println("|V| : "+g.getV());
		System.out.println("|E| : "+g.getE());
		System.out.println("Max flot : " + g.getFlowValue()+"\n");
	}
	
	public Vertex [] getPath() {
		
		colors = new int [g.getV()]; // Blanc=0, Gris=1, Noir=2
		parents = new int [g.getV()];
		
		for(int i=0; i<g.getV(); i++) {
			colors[i]=0;
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
		colors[index]=1;
		for(Vertex v : g.getNeighbors(g.getVertex(index))) {
			// TODO Prend ton un object Vertex ?
			if(colors[v.id]==0) {
				parents[v.id]=index;
				visitDFS(v.id);
			}
		}
		colors[index]=2;
	}

}
