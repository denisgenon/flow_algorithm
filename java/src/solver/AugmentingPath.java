package solver;

import interfaces.Graph;

public abstract class AugmentingPath implements Solver{
	public Graph g;

	public long timeStart;
	public boolean timeout=false;
	public int limitTime=600000; // en ms
	
	public AugmentingPath(){}
	
	/**
	 * Run the augmenting path algorithm on the Graph g
	 * @param g, the representation of the instance
	 */
	public AugmentingPath(Graph g) {
		this.g = g;
		timeStart=System.currentTimeMillis();
		int [] myPath = getPath();
		while(myPath!=null && !timeout) { // While there is a path between the source and the sink in the residual graph
			applyPath(getMinFlow(myPath), myPath); // Apply the path found on the residual graph with the bottleneck of the path
			myPath = getPath(); // Search for a new path in the residual graph
			timeout=(System.currentTimeMillis()-timeStart)>limitTime;
		}
	}

	/**
	 * Give the bottleneck of the path
	 * @param the path
	 * @return the value of the bottleneck of the path
	 */
	public int getMinFlow(int[] path) {
		int minFlow=Integer.MAX_VALUE;
		for(int i=0; i<path.length-1; i++) {
			minFlow=Math.min(minFlow,g.getCapacity(path[i+1], path[i],1));
		}
		return minFlow;
	}

	/**
	 * Print to the standard output the value of the maximum flow
	 */
	public void getResults() {
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
	
	public void getTime() {
		System.out.println((System.currentTimeMillis()-timeStart)+"\n");
	}

	public abstract int [] getPath();

	/**
	 * Apply the capacity change on the path in the residual graph and in the graph (pushing the flow)
	 * @param capacity
	 * @param path
	 */
	public void applyPath(int capacity, int[] path) {
		for(int i=0; i<path.length-1; i++) {
			int currentCapa = g.getCapacity(path[i+1], path[i],1);
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
}
