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
	public HashMap<Integer, Integer> [] capaMatrix;
	public HashMap<Integer, Integer> [] bestFlow;

	public HashMapGraph(String filePath) {
		parse(filePath);
		bestFlow = new HashMap[V];
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
			capaMatrix = new HashMap[V];
			maxCapa = 0;

			// Parse the items
			for (int i = 0; i < E; i++) {
				line = br.readLine();
				data = line.split(" ");
				int idVertex1 = Integer.parseInt(data[0]);
				int idVertex2 = Integer.parseInt(data[1]);
				int capa = Integer.parseInt(data[2]);
				maxCapa = Math.max(capa, maxCapa);

				// On ajoute les nouveaux vertices
				if(vertices[idVertex1] == null) {
					vertices[idVertex1] = new Vertex(idVertex1);
				}
				if(vertices[idVertex2] == null) {
					vertices[idVertex2] = new Vertex(idVertex2);
				}

				// On ajoute le voisin+distance dans le tableau
				if(capaMatrix[idVertex1]==null) capaMatrix[idVertex1]= new HashMap<Integer, Integer>();
				if(capaMatrix[idVertex2]==null) capaMatrix[idVertex2]= new HashMap<Integer, Integer>();
				capaMatrix[idVertex1].put(idVertex2, capa);
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int [] getAdjacents(int vertex) {
		HashMap<Integer, Integer> myMap = capaMatrix[vertex];
		int [] adja = new int [getAdjacentsSize(vertex)];

		Iterator<Integer> keySetIterator = myMap.keySet().iterator(); 
		int i = 0;
		while(keySetIterator.hasNext()){ 
			Integer key = keySetIterator.next();
			adja[i]=key;
			i++;
		}

		return adja;
	}

	@Override
	public int getFlowValue(int type) {
		if(type==1){
			int value = 0;
			HashMap<Integer, Integer> myMap = bestFlow[V-1];
			Iterator<Integer> keySetIterator = myMap.keySet().iterator(); 
			while(keySetIterator.hasNext()){ 
				Integer key = keySetIterator.next();
				value+=myMap.get(key);
			}
			return value;
		}
		if(type==2){
			return vertices[V-1].e;
		}
		return -1;
	}

	@Override
	public int removeEdge(int u, int v) {
		return capaMatrix[u].remove(v);
	}

	@Override
	public void addEdgeResidualGraph(int u, int v, int capa) {
		capaMatrix[u].put(v, capa);
	}
	@Override
	public void addEdgeGraph(int u, int v, int capa) {
		bestFlow[u].put(v, capa);
	}

	@Override
	public int getCapacityResidualGraph(int u, int v) {
		if(capaMatrix[u]==null) capaMatrix[u] = new HashMap<Integer, Integer>(20);
		Integer res = capaMatrix[u].get(v);
		if (res==null) return -1;
		return res;
	}
	
	@Override
	public int getCapacityGraph(int u, int v) {
		if(bestFlow[u]==null) bestFlow[u] = new HashMap<Integer, Integer>(20);
		Integer res = bestFlow[u].get(v);
		if (res==null) return -1;
		return res;
	}

	@Override
	public void setCapacityResidualGraph(int u, int v, int newCapa) {
		capaMatrix[u].replace(v, newCapa);
	}
	@Override
	public void setCapacityGraph(int u, int v, int newCapa) {
		bestFlow[u].replace(v, newCapa);
	}

	@Override
	public int getAdjacentsSize(int v) {
		return capaMatrix[v].size();
	}

}
