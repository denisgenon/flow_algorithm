package solver;

import java.util.Arrays;
import java.util.LinkedList;

import interfaces.Graph;
import models.SparseMapGraph;
import object.Arc;
import object.SparseMap;
import object.TheTower;
import object.Vertex;

public class HighestLabelPushRelabel implements Solver {
	public Graph g;
	public int source;
	public int sink;
	public TheTower activesVertices;
	public long timeStart;
	
	/**
	 * Compute the push relabeling algorithm on the graph
	 * @param g, the representation of the instance
	 */
	public HighestLabelPushRelabel(Graph g) {
		this(g, 0, g.getV() - 1);
	}
	
	/**
	 * Compute the push relabeling algorithm on the graph
	 * @param g, the representation of the instance
	 * @param source, the source node
	 * @param sink, the sink node
	 */
	public HighestLabelPushRelabel(Graph g, int source, int sink) {
		this.g = g;
		this.source = source;
		this.sink = sink;
		activesVertices = new TheTower(g.getV());
		timeStart=System.currentTimeMillis();
		preFlow();
		while (!activesVertices.isEmpty()) { // While there is active vertex (vertex with excedent)
			// We take the highest active node
			pushrelabelFlow(activesVertices.getTop());
		}
	} 
	
	/**
	 * Push a flow on all the neighbors edges of the source
	 */
	public void preFlow() {		
		SparseMap[] invertedGraph = new SparseMap[g.getV()];
		for (int i = 0; i < invertedGraph.length; i++) {
			invertedGraph[i] = new SparseMap();
		}
		
		for (int i = 0; i < g.getV(); i++) {
			for (int j : g.getAdjacents(i)) {
				invertedGraph[j].add(new Arc(1, i));
			}
		}
		
		int[] parents = new int[g.getV()];
		Arrays.fill(parents, -1);
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(sink);
		g.getVertex(sink).h = 0;
		while (!queue.isEmpty()) {
			int u = queue.removeFirst();
			for(Arc v : invertedGraph[u].adjacents) {
				if (g.getVertex(v.idDestination).h == 0) {
					parents[v.idDestination] = u;
					g.getVertex(v.idDestination).h = g.getVertex(parents[v.idDestination]).h + 1;
					queue.add(v.idDestination);
				}
			}
		}
		
		g.getVertex(source).h = g.getV();
		g.getVertex(sink).h = 0;
		
		for (int i : g.getAdjacents(source)) {
			pushFillingFlow(source, i);
		}
	}
	
	/**
	 * We push the biggest flow we can on the edge origin->desti
	 * @param origin
	 * @param destination
	 */
	public void pushFillingFlow(int origin, int destination) {
		int flow = g.removeEdge(origin,destination);
		g.addEdge(destination, origin, flow);
		
		Vertex dest = g.getVertex(destination);
		Vertex orig = g.getVertex(origin);

		dest.e += flow;
		if(dest.e > 0) {
			activesVertices.add(orig, dest);
		}
	}
	
	/**
	 * We push a flow on the neighbors of u if we can.
	 * @param u
	 */
	public void pushrelabelFlow(Vertex u) {
		int minimalDistance = Integer.MAX_VALUE;
		for(int i : g.getAdjacents(u.id)) {
			Vertex v = g.getVertex(i);
			minimalDistance = Math.min(minimalDistance, v.h);
			if (u.h - 1 == v.h) { // If we can push the flow u -> v
				pushFlow(u, v, Math.min(u.e, g.getCapacity(u.id, v.id))); // We push!
				return;
			}
		}
		activesVertices.updateTop(minimalDistance + 1);
	}

	
	/**
	 * We push a flow on the residual graph and we update our graph representation
	 * @param origin
	 * @param destination
	 * @param flowValue is flow value pushed
	 */
	public void pushFlow(Vertex origin, Vertex destination, int flowValue) {
		// We update the capacity in the residual graph u -> v
		int capacity = g.getCapacity(origin.id, destination.id);
		if (capacity <= flowValue) { // If the edge is full, we remove it
			g.removeEdge(origin.id, destination.id);
		}
		else {
			g.setCapacity(origin.id, destination.id, capacity - flowValue);
		}
		// We update the excedent on the origin and on the desitnation
		origin.e -= flowValue;
		if(origin.e <= 0) {
			activesVertices.removeTop();
		}
		
		destination.e += flowValue;
		if(destination.e > 0 && destination.id != (g.getV() - 1) && destination.id != 0) {
			activesVertices.add(origin, destination);
		}
		
		// We update the capacity in the residual graph v -> u
		capacity = g.getCapacity(destination.id, origin.id);
		if(capacity == -1) {
			g.addEdge(destination.id, origin.id, flowValue);
		}
		else {
			g.setCapacity(destination.id, origin.id, capacity + flowValue);
		}
	}
	
	public void getTime() {
		System.out.println((System.currentTimeMillis()-timeStart));
	}
	
	/**
	 * Print to the standard output the value of the best flow.
	 */
	public void getResults() {
		System.out.println("|V| : " + g.getV());
		System.out.println("|E| : " + g.getE());
		System.out.println("Max flot : " + g.getVertex(sink).e);
		System.out.println("Temps d'execution : " + (System.currentTimeMillis()-timeStart) + " ms"+"\n");
	}
}
