package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import interfaces.Graph;
import object.Vertex;


@SuppressWarnings("unchecked")
public class HashMapGraph extends SimpleGraph implements Graph {
	public HashMap<Integer, Integer> [] residualGraph;

	public HashMapGraph(String filePath, boolean oriented) {
		this.oriented = oriented;
		parse(filePath);
	}

	@Override
	public void parse(String filePath) {
		try{
			BufferedReader br;
			br = new BufferedReader(new FileReader(filePath));

			String line = br.readLine();

			// Number of items
			String[] data = line.split(" ");
			V = Integer.parseInt(data[0]);
			E = Integer.parseInt(data[1]);
			vertices = new Vertex[V];
			residualGraph = new HashMap[V];
			maxCapacity = 0;

			// Parse the items
			for (int i = 0; i < E; i++) {
				line = br.readLine();
				data = line.split(" ");
				int idVertex1 = Integer.parseInt(data[0]);
				int idVertex2 = Integer.parseInt(data[1]);
				int capa = Integer.parseInt(data[2]);
				maxCapacity = Math.max(capa, maxCapacity);

				// On ajoute les nouveaux vertices
				if(vertices[idVertex1] == null) {
					vertices[idVertex1] = new Vertex(idVertex1);
				}
				if(vertices[idVertex2] == null) {
					vertices[idVertex2] = new Vertex(idVertex2);
				}

				// On ajoute le voisin+distance dans le tableau
				if(residualGraph[idVertex1]==null) residualGraph[idVertex1]= new HashMap<Integer, Integer>();
				if(residualGraph[idVertex2]==null) residualGraph[idVertex2]= new HashMap<Integer, Integer>();
				residualGraph[idVertex1].put(idVertex2, capa);
				if(!oriented) residualGraph[idVertex2].put(idVertex1, capa);
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int [] getAdjacents(int vertex) {
		int[] adjacents = new int [getAdjacentsSize(vertex)];

		Iterator<Integer> keySetIterator = residualGraph[vertex].keySet().iterator(); 
		int i = 0;
		while(keySetIterator.hasNext()){ 
			adjacents[i] = keySetIterator.next();
			i++;
		}
		return adjacents;
	}

	@Override
	public int removeEdge(int u, int v) {
		return residualGraph[u].remove(v);
	}

	@Override
	public void addEdge(int u, int v, int capacity) {
		residualGraph[u].put(v, capacity);
	}

	@Override
	public int getCapacity(int u, int v) {
		if(residualGraph[u]==null) residualGraph[u] = new HashMap<Integer, Integer>(20);
		Integer res = residualGraph[u].get(v);
		if (res==null) return -1;
		return res;
	}
	

	@Override
	public void setCapacity(int u, int v, int capacity) {
		residualGraph[u].replace(v, capacity);
	}

	@Override
	public int getAdjacentsSize(int v) {
		return residualGraph[v].size();
	}

}
