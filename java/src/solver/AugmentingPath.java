package solver;

import java.util.ArrayList;

import interfaces.Graph;

public abstract class AugmentingPath implements Solver {
	public Graph g;
	protected int source;
	protected int sink;
	protected int flow = 0;

	public long timeStart;
	public boolean timeout=false;
	public int limitTime=600000; // en ms
	
	public AugmentingPath(){}
	
	/**
	 * Run the augmenting path algorithm on the Graph g
	 * @param g, the representation of the instance
	 * @param source, the source node
	 * @param sink, the sink node
	 */
	public AugmentingPath(Graph g, int source, int sink) {
		this.g = g;
		this.source = source;
		this.sink = sink;
		int addedFlow = 0;
		timeStart = System.currentTimeMillis();
		addedFlow = getPath();
		while(addedFlow != 0 && !timeout) { // While there is a path between the source and the sink in the residual graph
			flow += addedFlow;
			addedFlow = getPath(); // Search for a new path in the residual graph
			timeout = (System.currentTimeMillis()-timeStart) > limitTime;
		}
	}

	/**
	 * Print to the standard output the value of the maximum flow
	 */
	public void getResults() {
		if(!timeout){
			System.out.println("|V| : "+g.getV());
			System.out.println("|E| : "+g.getE());
			//System.out.println("Max flot : " + g.getFlowValue(1));
			System.out.println("Max flow : " + this.flow);
			System.out.println("Run time : "+(System.currentTimeMillis()-timeStart)+" ms"+"\n");
		}
		else{
			System.out.println("|V| : "+g.getV());
			System.out.println("|E| : "+g.getE());
			System.out.println("Timeout. Max flot : "+ this.flow +"\n");
		}
	}
	
	public void getTime() {
		System.out.println((System.currentTimeMillis()-timeStart));
	}

	public abstract int getPath();

	/**
	 * Apply the capacity change on the path in the residual graph and in the graph (pushing the flow)
	 * @param capacity
	 * @param path
	 */
	public void applyPath(int capacity, ArrayList<Integer> path) {
		for(int i = 0; i < path.size()-1; i++) {
			int currentCapacity = g.getCapacity(path.get(i+1), path.get(i));
			if(currentCapacity <= capacity) { // On enleve l'arete si la capa dispo est 0
				g.removeEdge(path.get(i+1), path.get(i));
			}
			else { // on enleve la capa dans le bon sens sinon
				g.setCapacity(path.get(i+1), path.get(i), currentCapacity-capacity);
			}

			currentCapacity = g.getCapacity(path.get(i), path.get(i+1));
			if(currentCapacity == -1) { // on crée l'arete si elle n'existe pas
				g.addEdge(path.get(i), path.get(i+1), capacity);
			}
			else { // on rajoute la capa dans le sens inverse sinon
				g.setCapacity(path.get(i), path.get(i+1), currentCapacity+capacity);
			}
		}
	}
}
