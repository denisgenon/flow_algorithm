package interfaces;

import java.util.ArrayList;

import object.Tuple;
import object.Vertex;

public interface Graph {
	
	public void parse(String filePath);
	
	public int getV();
	
	public int getE();
	
	public Vertex getVertex(int id);
	
	public Vertex[] getVertices();
	
	public ArrayList<Vertex> getAdjacents(Vertex vertex);
	
	public int getFlowValue();

	public int remove(Vertex u, Vertex v);

	public void add(Vertex u, Vertex v, int capa, int type);

	public Tuple getTuple(Vertex u, Vertex v, int type);
	
	public int getCapacity(Vertex u, Vertex v);
	
}
