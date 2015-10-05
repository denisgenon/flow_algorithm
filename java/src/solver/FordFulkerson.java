package solver;

import java.util.ArrayList;

import flowAlgorithm.FlowAlgorithmInstance;

import object.Vertex;

public class FordFulkerson {
	
	public FlowAlgorithmInstance instance;
	// For getPathDFS and visitDFS
	public int [] colors;
	public int [] parents;
	
	public FordFulkerson(FlowAlgorithmInstance instance) {
		this.instance = instance;
		Vertex [] myPath = getPath();
		while(myPath!=null) {
			applyPath(getMinFlow(myPath),myPath);
			myPath = getPath();
		}
	}
	
	public void getResult() {
		//FlowAlgorithmSolver.printMatrix(instance.bestflot);
		System.out.println("Max flot : "+getFlotValue(instance.bestflot));
	}
	
	public Vertex [] getPath() {
		colors = new int [instance.V]; // Blanc=0, Gris=1, Noir=2
		parents = new int [instance.V];
		
		for(int i=0; i<instance.V; i++) {
			colors[i]=0;
			parents[i]=-1;
		}
		
		visitDFS(0);
		
		ArrayList<Vertex> mypath = new ArrayList<Vertex>();
		int indexpath = instance.V-1;
		mypath.add(instance.vertices[indexpath]);
		while(parents[indexpath]!=-1 && indexpath!=0){
			mypath.add(instance.vertices[parents[indexpath]]);
			indexpath=parents[indexpath];
		}
		if(indexpath==0) return mypath.toArray(new Vertex [mypath.size()]);
		return null;
	}
	
	public void visitDFS(int index) {
		colors[index]=1;
		for(Vertex v : instance.vertices[index].adjacents) {
			if(colors[v.id]==0) {
				parents[v.id]=index;
				visitDFS(v.id);
			}
		}
		colors[index]=2;	
	}
	
	public int getMinFlow(Vertex [] path) {
		int minFlow=Integer.MAX_VALUE;
		for(int i=0; i<path.length-1; i++) {
			minFlow=Math.min(minFlow,instance.distMatrix[path[i+1].id][path[i].id]);
		}
		return minFlow;
	}
	
	public void applyPath(int capa,Vertex [] path) {
		for(int i=0; i<path.length-1; i++) {
			instance.distMatrix[path[i+1].id][path[i].id]-=capa; // on enleve la capa dans le bon sens
			instance.distMatrix[path[i].id][path[i+1].id]+=capa; // on rajoute la capa dans le sens inverse
			instance.bestflot[path[i+1].id][path[i].id]+=capa;	// on augmente le flot courant
			
			// On enleve l'ar�te si la capa dispo est 0
			if(instance.distMatrix[path[i+1].id][path[i].id]==0) {
				path[i+1].adjacents.remove(path[i]);
			}
			
			// On rajoute une ar�te dans le sens inverse si c'est la premiere fois qu'on ajoute de la capa sur elle
			if(instance.distMatrix[path[i].id][path[i+1].id]==capa) {
				path[i].adjacents.add(path[i+1]);
			}
		}
	}
	
	public static int getFlotValue(int [][] matrix) {
		int value = 0;
		for(int i=0; i<matrix[0].length;i++){
			value+=matrix[0][i];
		}
		return value;
	}
}
