package solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import interfaces.Graph;
import object.Vertex;

public abstract class AugmentingPath {
	public Graph g;
	public int [] parents; // For getPath
	public long timeStart;
	public boolean timeout=false;
	public int limitTime=300000; // en ms

	public AugmentingPath(Graph g) {
		this.g = g;
		timeStart=System.currentTimeMillis();
		int [] myPath = getPath();
		
		while(myPath!=null && !timeout) {
			applyPath(getMinFlow(myPath),myPath);
			myPath = getPath();
			timeout=(System.currentTimeMillis()-timeStart)>limitTime;
		}
	}

	public int getMinFlow(int[] path) {
		int minFlow=Integer.MAX_VALUE;
		for(int i=0; i<path.length-1; i++) {
			minFlow=Math.min(minFlow,g.getCapacity(path[i+1], path[i],1));
		}
		return minFlow;
	}

	public void getResult() {
		if(!timeout){
			System.out.println("|V| : "+g.getV());
			System.out.println("|E| : "+g.getE());
			System.out.println("Max flot : " + g.getFlowValue(1));
			System.out.println("Temps d'execution : "+(System.currentTimeMillis()-timeStart)+" ms"+"\n");
		}
		else{
			System.out.println("|V| : "+g.getV());
			System.out.println("|E| : "+g.getE());
			System.out.println("Timeout. Max flot : "+g.getFlowValue(1)+"\n");
		}
	}

	public abstract int [] getPath();

	public void applyPath(int capacity, int[] path) {
		for(int i=0; i<path.length-1; i++) {
			int currentCapa = g.getCapacity(path[i+1], path[i], 1);
			if(currentCapa<=capacity) { // On enleve l'arete si la capa dispo est 0
				g.removeEdge(path[i+1], path[i]);
			}
			else { // on enleve la capa dans le bon sens sinon
				g.setCapacity(path[i+1], path[i], currentCapa-capacity,1);
			}

			currentCapa = g.getCapacity(path[i],path[i+1], 1);
			if(currentCapa == -1) { // on crée l'arete si elle n'existe pas
				g.addEdge(path[i], path[i+1], capacity,1);
			}
			else { // on rajoute la capa dans le sens inverse sinon
				g.setCapacity(path[i], path[i+1], currentCapa+capacity,1);
			}

			currentCapa = g.getCapacity(path[i], path[i+1], 2); // on augmente le flot courant	
			
			if(currentCapa <= 0) {
				
				g.addEdge(path[i], path[i+1], capacity, 2);
			}
			else {
				g.setCapacity(path[i], path[i+1], currentCapa+capacity, 2);
			}
		}
	}
}