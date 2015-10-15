package models;

import java.util.List;

import object.Vertex;

public interface AugmentingPathGraph { // TODO change name for FF et ED?
	
	public void parse(String filePath);
	
	public int getV();
	
	public List<Vertex> getNeighbors(Vertex vertex);
	
	public void setNeighbors(Vertex u, List<Vertex> vertices);
	
	public Vertex getVertex(int id);
	
	public int getMinFlow(Vertex[] path);
	
	public int getCapacity(Vertex u, Vertex v);
	
	public void setCapacity(Vertex u, Vertex v, int c);

	public int getFlowValue();
	
	public void applyPath(int capacity, Vertex [] path);
}
