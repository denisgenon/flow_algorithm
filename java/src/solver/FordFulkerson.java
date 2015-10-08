package solver;

import java.util.ArrayList;

import flowAlgorithm.FlowAlgorithmInstance;

import object.Node;
import object.Vertex;

public class FordFulkerson {
	
	public FlowAlgorithmInstance instance;
	// For getPath
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
			minFlow=Math.min(minFlow,Node.getNode(path[i+1].id, path[i].id,instance.capaMatrix).capa);
		}
		return minFlow;
	}
	
	public void applyPath(int capa,Vertex [] path) {
		for(int i=0; i<path.length-1; i++) {
			Node myT = Node.getNode(path[i+1].id, path[i].id,instance.capaMatrix);
			if(myT.capa<=capa) { // On enleve l'arete si la capa dispo est 0
				Node.removeNode(path[i+1].id, path[i].id,instance.capaMatrix);
				path[i+1].adjacents.remove(path[i]);
			}
			else { // on enleve la capa dans le bon sens sinon
				myT.capa-=capa;
			}
			
			myT = Node.getNode(path[i].id, path[i+1].id,instance.capaMatrix);
			if(myT==null) { // on crée l'arete si elle n'existe pas
				Node.addNode(path[i].id, path[i+1].id,capa,instance.capaMatrix); 
				path[i].adjacents.add(path[i+1]);
			}
			else { // on rajoute la capa dans le sens inverse sinon
				myT.capa+=capa;
			}
			
			myT = Node.getNode(path[i].id, path[i+1].id,instance.bestflot); // on augmente le flot courant	
			if(myT==null) Node.addNode(path[i].id, path[i+1].id,capa,instance.bestflot); 
			else {
				myT.capa+=capa;
			}
		}
	}
	
	public static int getFlotValue(Node[] capaMatrix) {
		int value = 0;
		Node t = capaMatrix[capaMatrix.length-1];
		while(t!=null){
			value+=t.capa;
			t=t.next;
		}
		return value;
	}
}
