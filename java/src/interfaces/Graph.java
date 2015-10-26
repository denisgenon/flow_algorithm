package interfaces;

import java.util.ArrayList;

import object.Edge;
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
	public ArrayList<Vertex> getAdjacents(Vertex vertex);
	
	/**
	 * @return La valeur du flot
	 */
	public int getFlowValue();

	/**
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
	 * @return La capacité de l'edge retiré
	 */
	public int removeEdge(Vertex u, Vertex v);

	/**
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
	 * @param capa : Capacité de l'edge
	 * @param type : 1 pour capaMatrix, 2 pour bestFlow
	 */
	public void addEdge(Vertex u, Vertex v, int capa, int type);

	/**
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
	 * @param type : 1 pour capaMatrix, 2 pour bestFlow
	 * @return L'edge entre u et v
	 */
	public Edge getEdge(Vertex u, Vertex v, int type);
	
	/**
	 * @param u : Vertex à l'origine
	 * @param v : Vertex à la destination
	 * @return La capacité entre u et v
	 */
	public int getCapacity(Vertex u, Vertex v);
	
}
