package solver;

import java.util.Iterator;

import interfaces.Graph;
import object.TheTowerHeights;
import object.Vertex;

public class HighestLabelPushRelabel implements Solver {
	public Graph g;
	public int source;
	public int sink;
	public TheTowerHeights activesVertices;
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
		activesVertices = new TheTowerHeights(g.getV());
		timeStart=System.currentTimeMillis();
		preFlow();
		int i = 0;
		while(!activesVertices.isEmpty()) { // While there is active vertex (vertex with excedent)
			// We take any active node (we need to change this heuristic)
			/*
			System.out.println("Size of activesVertices before: " + activesVertices.size);
			for (Integer name: activesVertices.tower.keySet()){
	            String key = name.toString();
	            String value = activesVertices.tower.get(name).toString();  
	            System.out.println(key + " " + value);  
			}
			System.out.println("In the tower, maxHeight is " + activesVertices.maxHeight);
			*/
			Vertex v = activesVertices.getHighest();
			//System.out.println(v.id + " is the highest node (" + v.h + ")");
			pushrelabelFlow(v);
			/*System.out.println("Size of activesVertices after: " + activesVertices.size);
			for (Integer name: activesVertices.tower.keySet()){
	            String key = name.toString();
	            String value = activesVertices.tower.get(name).toString();  
	            System.out.println(key + " " + value);  
			}
			System.out.println();*/
			i++;
		}
		System.out.println(i);
	} 
	/**
	 * Push a flow on all the neighbors edges of the source
	 */
	public void preFlow() {
		for (int i : g.getAdjacents(source)) {
			pushFillingFlow(source, i);
		}
		g.getVertex(source).h = g.getV();
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
		activesVertices.move(u, minimalDistance + 1);
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

		dest.e += flow;
		if(dest.e > 0) {
			activesVertices.add(dest);
			//addIn(activesVertices, dest);
		}
		
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
			activesVertices.remove(origin);
		}
		destination.e += flowValue;
		if(destination.e > 0 && destination.id != (g.getV() - 1) && destination.id != 0) {
			activesVertices.add(destination);
			//addIn(activesVertices, destination);
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
}
