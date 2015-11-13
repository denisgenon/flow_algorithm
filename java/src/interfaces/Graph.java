package interfaces;

import java.util.Iterator;

import object.Vertex;

public interface Graph {
	
	/**
	 * Parse le fichier
	 * 
	 * |V| |E|
	 * u v capa
	 * 
	 * @param filePath
	 */
	public void parse(String filePath);
	
	/**
	 * @return |V|
	 */
	public int getV();
	
	/**
	 * @return |E|
	 */
	public int getE();
	
	/**
	 * @param Numéro du vertex
	 * @return L'objet Vertex correspondant
	 */
	public Vertex getVertex(int id);
	
	/**
	 * @return Un tableau contenant tout les vertices
	 */
	public Vertex[] getVertices();
	
	/**
	 * @param vertex
	 * @return Les vertices voisins du vertex
	 */
	//public ArrayList<Integer> getAdjacents(int vertex);
	public Iterator<Integer> getAdjacents(int vertex);
	
	/**
	 * 
	 * @param type : 1 pour pushRelabel, 2 pour Augmenting Path
	 * @return la valeur du flot
	 */
	public int getFlowValue(int type);

	/**
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
	 * @return La capacité de l'edge retiré
	 */
	public int removeEdge(int u, int v);

	/**
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
	 * @param capa : Capacité de l'edge
	 * @param type : 1 pour capaMatrix, 2 pour bestFlow
	 */
	public void addEdge(int u, int v, int capa, int type);
	
	/**
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
	 * @return La capacité entre u et v
	 */
	public int getCapacity(int u, int v, int type);
	
	/**
	 * 
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
	 * @param newCapa : la capacité entre u et v
	 */
	public void setCapacity(int u, int v, int newCapa, int type);
	
	public Object[] getGraphType(int type);

	public int getAdjacentsSize(int i);
	
}
