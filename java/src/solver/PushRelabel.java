package solver;

import interfaces.Graph;

import java.util.ArrayList;

import object.Edge;
import object.Vertex;

public class PushRelabel {
	public Graph g;
	public ArrayList<Vertex> actifV = new ArrayList<Vertex>();
	public long timeStart;

	public void preProcess() {
		computeDistanceLabel();
		while(g.getAdjacents(g.getVertex(0)).size()>0) {
			chargeMax(g.getVertex(0), g.getAdjacents(g.getVertex(0)).get(0), actifV);
		}
		g.getVertex(0).h = g.getV();
	}
	
	public void computeDistanceLabel() {
		// Faire un Dijkstra à l'envers
		Vertex[] invertedVertices = new Vertex[g.getV()];
		
		for (int i = 0; i < g.getV(); i++) {
			Vertex v = new Vertex(i);
			invertedVertices[i] = v;
		}
		
		for (Vertex v : g.getVertices()) {
			for (Vertex u : g.getAdjacents(v)) {
				// V -5-> U devient V <-5- U dans invertedCapaMatrix
				invertedVertices[u.id].adjaDijkstra.add(new Vertex(v.id)); // TODO adjacents doit être add en fonction de la structure de donnée
			}
		}
		
		
		//FlowAlgorithmInstance invertedInstance = new FlowAlgorithmInstance(invertedCapaMatrix, instance.vertices, instance.V, instance.E);
		ArrayList<Vertex> notS = new ArrayList<>();
		for (int i = 0; i < invertedVertices.length; i++) {
			invertedVertices[i].h = Integer.MAX_VALUE-1;
			notS.add(invertedVertices[i]);
		}
		
		invertedVertices[g.getV() - 1].h = 0;
		while (notS.size() > 0) {
			Vertex i = findMinimumDistance(notS);
			notS.remove(i);
			for (Vertex j : g.getAdjacents(i)) {
				if (invertedVertices[j.id].h > invertedVertices[i.id].h + 1) {
				//if (j.h > i.h + 1) { // pas de cout de distance sur les aretes!
					//j.h = i.h + 1;
					g.getVertex(j.id).h = invertedVertices[i.id].h + 1;
					invertedVertices[j.id].h = invertedVertices[i.id].h + 1;
				}
			}
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

	public void process(Graph g) {
		this.g = g;
		timeStart=System.currentTimeMillis();
		preProcess();
		while(!actifV.isEmpty()){
			Vertex elu = actifV.get(0);// on prend un actif
			pushRelabel(elu);
		}
	}
	
	public void getResult() {
		System.out.println("|V| : " + g.getV());
		System.out.println("|E| : " + g.getE());
		System.out.println("Max flot : " + g.getFlowValue());
		System.out.println("Temps d'éxecution : "+(System.currentTimeMillis()-timeStart)+" ms"+"\n");
	}

	public void pushRelabel(Vertex v) {
		int hMin=Integer.MAX_VALUE;
		for(Vertex u : g.getAdjacents(v)) {
			hMin = Math.min(hMin, u.h);
			if (v.h-1 == u.h) {
				chargeCapa(v,u,Math.min(v.e,g.getCapacity(v, u)),actifV); // push
				return;
			}
		}
		v.h = hMin + 1;
	}
	
	public void chargeMax(Vertex origin, Vertex desti, ArrayList<Vertex> actifV) {

		int newCapa = g.removeEdge(origin,desti);
		g.addEdge(desti, origin, newCapa,1);

		desti.e+=newCapa;
		if(desti.e>0) {
			actifV.add(desti);
		}
	}
	
	public void chargeCapa(Vertex origin, Vertex desti, int capa, ArrayList<Vertex> actifV) {
		Edge myT = g.getEdge(origin, desti,1);
		if(myT.capa<=capa) { // On enleve l'arete si la capa dispo est 0
			g.removeEdge(origin, desti);
		}
		else { // on enleve la capa dans le bon sens sinon
			myT.capa-=capa;
		}
		
		origin.e-=capa;
		if(origin.e<=0) {
			actifV.remove(origin);
		}
		desti.e+=capa;
		if(desti.e>0 && desti.id!=(g.getV()-1) && desti.id!=0 && !actifV.contains(desti)) {
			actifV.add(desti);
		}

		myT = g.getEdge(desti, origin,1);
		if(myT==null) { // on crée l'arete si elle n'existe pas
			g.addEdge(desti, origin, capa,1);
		}
		else { // on rajoute la capa dans le sens inverse sinon
			myT.capa+=capa;
		}

	}
}
