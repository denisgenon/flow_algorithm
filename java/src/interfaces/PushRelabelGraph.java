package interfaces;

import java.util.ArrayList;

import object.Vertex;


public interface PushRelabelGraph extends Graph {
	
	public void chargeCapa(Vertex origin, Vertex desti, int capa, ArrayList<Vertex> actifV);
	
	public void chargeMax(Vertex origin, Vertex desti, ArrayList<Vertex> actifV);
	
	public int getCapacity(Vertex u, Vertex v);
	
}
