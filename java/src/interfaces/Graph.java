package interfaces;

import object.Vertex;

public interface Graph {
	
	public void parse(String filePath);
	
	public int getV();
	
	public int getE();
	
	public Vertex getVertex(int id);
	
	public Vertex[] getVertices();
	
}
