package solver;

import java.util.ArrayList;

import interfaces.Graph;
import object.Vertex;

public class PushRelabel implements Solver {
	public Graph g;
	public int source;
	public int sink;
	public ArrayList<Vertex> activeNodes = new ArrayList<Vertex>();
	public long timeStart;
	/**
	 * Compute the push relabeling algorithm on the graph
	 * @param g, the representation of the instance
	 */
	public PushRelabel(Graph g) {
		this(g,0,g.getV()-1);
	}
	
	/**
	 * Compute the push relabeling algorithm on the graph
	 * @param g, the representation of the instance
	 * @param source, the source node
	 * @param sink, the sink node
	 */
	public PushRelabel(Graph g, int source, int sink) {
		this.g = g;
		this.source = source;
		this.sink = sink;
		timeStart=System.currentTimeMillis();
		preFlow();
		while(!activeNodes.isEmpty()) { // While there is active vertex (vertex with excedent)
			Vertex elu = activeNodes.get(0);// We take any active node (we need to change this heuristic)
			pushFlow(elu);
		}
	}
	/**
	 * Compute the distance label and push a flow on all the neighbors edges of the source
	 */
	public void preFlow() {
		computeDistanceLabel();
		for(int i : g.getAdjacents(source)) {
			int v = i;
			pushFillingFlow(source, v);
		}
		g.getVertex(source).h = g.getV();
	}
	/**
	 * Compute the distance of each node from the sink
	 */
	public void computeDistanceLabel() {
		// Inverted Dijkstra
		Vertex[] invertedVertices = new Vertex[g.getV()];

		for (int i = 0; i < g.getV(); i++) {
			Vertex v = new Vertex(i);
			invertedVertices[i] = v;
		}

		for (Vertex v : g.getVertices()) {
			for(int i : g.getAdjacents(v.id)) {
				int u = i;
				// V -5-> U became V <-5- U in invertedCapaMatrix
				invertedVertices[u].adjacents.add(new Vertex(v.id));
			}
		}

		ArrayList<Vertex> notS = new ArrayList<>();
		for (int i = 0; i < invertedVertices.length; i++) {
			invertedVertices[i].h = Integer.MAX_VALUE-1;
			notS.add(invertedVertices[i]);
		}
		
		invertedVertices[sink].h = 0;
		while (notS.size() > 0) {
			Vertex i = findMinimumDistance(notS);
			notS.remove(i);
			for(int p : g.getAdjacents(i.id)) {
				int j = p;
				if (invertedVertices[j].h > invertedVertices[i.id].h + 1) {
					g.getVertex(j).h = invertedVertices[i.id].h + 1;
					invertedVertices[j].h = invertedVertices[i.id].h + 1;
				}
			}
		}
	}
	/**
	 * Find the vertex in vertices with the minimum distance from the sink
	 * @param vertices
	 * @return the nearest vertex from the sink
	 */
	public Vertex findMinimumDistance(ArrayList<Vertex> vertices) {
		Vertex minVertex = new Vertex(vertices.size());
		minVertex.h = Integer.MAX_VALUE;
		for (Vertex v : vertices) {
			if (v.h <= minVertex.h) {
				minVertex = v;
			}
		}
		return minVertex;
	}
	
	/**
	 * Print to the standard output the value of the best flow.
	 */
	public void getResults() {
		System.out.println("|V| : " + g.getV());
		System.out.println("|E| : " + g.getE());
		System.out.println("Max flot : " + g.getFlowValue());
		System.out.println("Temps d'execution : "+(System.currentTimeMillis()-timeStart)+" ms"+"\n");
	}
	/**
	 * We push a flow on the neighbors of v if we can.
	 * @param v
	 */
	public void pushFlow(Vertex v) {
		int hMin=Integer.MAX_VALUE;
		for(int i : g.getAdjacents(v.id)) {
			int uint = i;
			Vertex u = g.getVertex(uint);
			hMin = Math.min(hMin, u.h);
			if (v.h-1 == u.h) { // If we can push the flow v -> u
				pushFlow(v,u,Math.min(v.e, g.getCapacity(v.id, u.id))); // We push!
				return;
			}
		}
		v.h = hMin + 1; // We update the distance
	}

	/**
	 * We push the biggest flow we can on the edge origin->desti
	 * @param origin
	 * @param desti
	 */
	public void pushFillingFlow(int origin, int desti) {
		int newCapacity = g.removeEdge(origin,desti);
		g.addEdge(desti, origin, newCapacity);
		
		Vertex dest = g.getVertex(desti);

		dest.e+=newCapacity;
		if(dest.e>0) {
			activeNodes.add(dest);
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
		if(capacity<=flowValue) { // If the edge is full, we remove it
			g.removeEdge(origin.id, destination.id);
		}
		else {
			g.setCapacity(origin.id, destination.id, capacity-flowValue);
		}
		// We update the excedent on the origin and on the desitnation
		origin.e-=flowValue;
		if(origin.e<=0) {
			activeNodes.remove(origin);
		}
		destination.e+=flowValue;
		if(destination.e>0 && destination.id!=(g.getV()-1) && destination.id!=0 && !activeNodes.contains(destination)) {
			activeNodes.add(destination);
		}

		// We update the capacity in the residual graph v -> u
		capacity = g.getCapacity(destination.id, origin.id);
		if(capacity==-1) {
			g.addEdge(destination.id, origin.id, flowValue);
		}
		else {
			g.setCapacity(destination.id, origin.id, capacity+flowValue);
		}
	}
	
	public void getTime() {
		System.out.println((System.currentTimeMillis()-timeStart)+"\n");
	}
}
