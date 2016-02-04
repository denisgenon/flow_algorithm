package solver;

import java.util.ArrayList;

import interfaces.Graph;
import object.Vertex;

public class PushRelabel implements Solver {
	public Graph g;
	public int source;
	public int sink;
	public ArrayList<Vertex> actifV = new ArrayList<Vertex>();
	public long timeStart;
	/**
	 * Compute the push relabeling algorithm on the graph
	 * @param g, the representation of the instance
	 */
	public PushRelabel(Graph g) {
		this.g = g;
		this.source = 0;
		this.sink = g.getV() - 1;
		timeStart=System.currentTimeMillis();
		preProcess();
		while(!actifV.isEmpty()) { // While there is active vertex (vertex with excedent)
			Vertex elu = actifV.get(0);// We take any active node (TODO: we need to change this heuristic)
			process(elu);
		}
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
		preProcess();
		while(!actifV.isEmpty()) { // While there is active vertex (vertex with excedent)
			Vertex elu = actifV.get(0);// We take any active node (we need to change this heuristic)
			process(elu);
		}
	}
	/**
	 * Compute the distance label and push a flow on all the neighbors edges of the source
	 */
	public void preProcess() {
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
				invertedVertices[u].adjaDijkstra.add(new Vertex(v.id));
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
	public void process(Vertex v) {
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
		int newCapa = g.removeEdge(origin,desti);
		g.addEdge(desti, origin, newCapa);
		
		Vertex dest = g.getVertex(desti);

		dest.e+=newCapa;
		if(dest.e>0) {
			actifV.add(dest);
		}
	}
	/**
	 * We push a flow on the residual graph and we update our graph representation
	 * @param origin
	 * @param desti
	 * @param flow_value is flow value pushed
	 */
	public void pushFlow(Vertex origin, Vertex desti, int flow_value) {
		// We update the capacity in the residual graph u -> v
		int capacity = g.getCapacity(origin.id, desti.id);
		if(capacity<=flow_value) { // If the edge is full, we remove it
			g.removeEdge(origin.id, desti.id);
		}
		else {
			g.setCapacity(origin.id, desti.id, capacity-flow_value);
		}
		// We update the excedent on the origin and on the desitnation
		origin.e-=flow_value;
		if(origin.e<=0) {
			actifV.remove(origin);
		}
		desti.e+=flow_value;
		if(desti.e>0 && desti.id!=(g.getV()-1) && desti.id!=0 && !actifV.contains(desti)) {
			actifV.add(desti);
		}

		// We update the capacity in the residual graph v -> u
		capacity = g.getCapacity(desti.id, origin.id);
		if(capacity==-1) {
			g.addEdge(desti.id, origin.id, flow_value);
		}
		else {
			g.setCapacity(desti.id, origin.id, capacity+flow_value);
		}
	}
	
	public void getTime() {
		System.out.println((System.currentTimeMillis()-timeStart)+"\n");
	}
}
