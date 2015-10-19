package solver;

import interfaces.PushRelabelGraph;

import java.util.ArrayList;

import object.Vertex;

public class PushRelabel {
	public PushRelabelGraph instance;
	public ArrayList<Vertex> actifV = new ArrayList<Vertex>();

	public void preProcess() {
		computeDistanceLabel();
		for(Vertex v : instance.getVertex(0).adjacents) {
			instance.chargeMax(instance.getVertex(0),v, actifV);
		}
		instance.getVertex(0).h = instance.getE();
	}
	
	public void computeDistanceLabel() {
		// Faire un Dijkstra à l'envers
		Vertex[] invertedVertices = new Vertex[instance.getV()];
		
		for (int i = 0; i < instance.getV(); i++) {
			Vertex v = new Vertex(i);
			invertedVertices[i] = v;
		}
		
		for (Vertex v : instance.getVertices()) {
			for (Vertex u : v.adjacents) {
				// V -5-> U devient V <-5- U dans invertedCapaMatrix
				invertedVertices[u.id].adjacents.add(new Vertex(v.id));
			}
		}
		
		//FlowAlgorithmInstance invertedInstance = new FlowAlgorithmInstance(invertedCapaMatrix, instance.vertices, instance.V, instance.E);
		ArrayList<Vertex> notS = new ArrayList<>();
		for (Vertex v : invertedVertices) {
			notS.add(v);
			v.h = Integer.MAX_VALUE;
		}
		
		invertedVertices[instance.getV() - 1].h = 0;
		while (notS.size() > 0) {
			Vertex i = findMinimumDistance(notS);
			notS.remove(i);
			for (Vertex j : i.adjacents) {
				if (j.h > i.h + 1) { // pas de cout de distance sur les aretes!
					j.h = i.h + 1;
				}
			}
		}
		
		for (int i = 0; i < invertedVertices.length; i++) {
			instance.getVertex(i).h = invertedVertices[i].h;
		}				
	}
	
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

	public void process(PushRelabelGraph instance) {
		this.instance = instance;
		preProcess();
		while(!actifV.isEmpty()){
			Vertex elu = actifV.get(0);// on prend un actif
			pushRelabel(elu);
		}
	}

	public void pushRelabel(Vertex v) {
		int hMin=Integer.MAX_VALUE;
		for(Vertex u : v.adjacents) {
			hMin=Math.min(hMin, u.h);
			if (u.h-1 == v.h) {
				instance.chargeCapa(v,u,Math.min(v.e,instance.getCapacity(v, u)),actifV); // push
				return;
			}
		}
		v.h=hMin+1;
	}
}
