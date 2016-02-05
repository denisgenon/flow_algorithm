package solver;

import interfaces.Graph;

public abstract class AugmentingPath implements Solver{
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
	 */
	public AugmentingPath(Graph g) {
		this.g = g;
		this.source = 0;
		this.sink = g.getV() - 1;
		int addedFlow = 0;
		timeStart=System.currentTimeMillis();
		int [] myPath = getPath(); //TODO: run method ?
		while(myPath!=null && !timeout) { // While there is a path between the source and the sink in the residual graph
			addedFlow = getMinFlow(myPath);
			flow += addedFlow;
			applyPath(addedFlow, myPath); // Apply the path found on the residual graph with the bottleneck of the path
			myPath = getPath(); // Search for a new path in the residual graph
			timeout=(System.currentTimeMillis()-timeStart)>limitTime;
		}
	}
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
		timeStart=System.currentTimeMillis();
		int [] myPath = getPath();
		while(myPath!=null && !timeout) { // While there is a path between the source and the sink in the residual graph
			addedFlow = getMinFlow(myPath);
			flow += addedFlow;
			applyPath(addedFlow, myPath); // Apply the path found on the residual graph with the bottleneck of the path
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
			minFlow=Math.min(minFlow,g.getCapacity(path[i+1], path[i]));
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
			//System.out.println("Max flot : " + g.getFlowValue(1));
			System.out.println("Max flot : " + this.flow);
			System.out.println("Temps d'execution : "+(System.currentTimeMillis()-timeStart)+" ms"+"\n");
		}
		else{
			System.out.println("|V| : "+g.getV());
			System.out.println("|E| : "+g.getE());
			System.out.println("Timeout. Max flot : "+ this.flow +"\n");
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
			int currentCapa = g.getCapacity(path[i+1], path[i]);
			if(currentCapa<=capacity) { // On enleve l'arete si la capa dispo est 0
				g.removeEdge(path[i+1], path[i]);
			}
			else { // on enleve la capa dans le bon sens sinon
				g.setCapacity(path[i+1], path[i], currentCapa-capacity);
			}

			currentCapa = g.getCapacity(path[i],path[i+1]);
			if(currentCapa==-1) { // on crée l'arete si elle n'existe pas
				g.addEdge(path[i], path[i+1], capacity);
			}
			else { // on rajoute la capa dans le sens inverse sinon
				g.setCapacity(path[i], path[i+1], currentCapa+capacity);
			}
		}
	}
}
