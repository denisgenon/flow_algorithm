package interfaces;

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
	public int [] getAdjacents(int vertex);
	
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
	 */
	public void addEdgeResidualGraph(int u, int v, int capa);
	
	/**
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
	 * @param capa : Capacité de l'edge
	 */
	public void addEdgeGraph(int u, int v, int capa); //TODO never used
	
	/**
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
	 * @return La capacité entre u et v
	 */
	public int getCapacityResidualGraph(int u, int v);
	
	/**
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
 	 * @return La capacité entre u et v
	 */
	public int getCapacityGraph(int u, int v);
	
	/**
	 * 
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
	 * @param newCapa : la capacité entre u et v
	 */
	public void setCapacityResidualGraph(int u, int v, int newCapa);
	
	/**
	 * 
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
	 * @param newCapa : la capacité entre u et v
	 */
	public void setCapacityGraph(int u, int v, int newCapa);
	
	/**
	 * 
	 * @param v 
	 * @return Le nombre de voisins de v
	 */
	public int getAdjacentsSize(int v);
	
	/**
	 * 
	 * @return La capacité maximum des edges
	 */
	public int getMaxCapacity();
	
}
