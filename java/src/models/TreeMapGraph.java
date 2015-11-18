package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

import interfaces.Graph;
import object.Vertex;

@SuppressWarnings("unchecked")
public class TreeMapGraph extends SimpleGraph implements Graph {
	public TreeMap<Integer, Integer> [] capaMatrix;
	public TreeMap<Integer, Integer> [] bestFlow;

	public TreeMapGraph(String filePath) {
		parse(filePath);
		bestFlow = new TreeMap[V];
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
			capaMatrix = new TreeMap[V];

			// Parse the items
			for (int i = 0; i < E; i++) {
				line = br.readLine();
				data = line.split(" ");
				int idVertex1 = Integer.parseInt(data[0]);
				int idVertex2 = Integer.parseInt(data[1]);
				int capa = Integer.parseInt(data[2]);

				// On ajoute les nouveaux vertices
				if(vertices[idVertex1] == null) {
					vertices[idVertex1] = new Vertex(idVertex1);
				}
				if(vertices[idVertex2] == null) {
					vertices[idVertex2] = new Vertex(idVertex2);
				}

				// On ajoute le voisin+distance dans le tableau
				if(capaMatrix[idVertex1]==null) capaMatrix[idVertex1]= new TreeMap<Integer, Integer>();
				if(capaMatrix[idVertex2]==null) capaMatrix[idVertex2]= new TreeMap<Integer, Integer>();
				capaMatrix[idVertex1].put(idVertex2, capa);
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int [] getAdjacents(int vertex) {
		TreeMap<Integer, Integer> myMap = capaMatrix[vertex];
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
			TreeMap<Integer, Integer> myMap = bestFlow[V-1];
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
	public void addEdge(int u, int v, int capa, int type) {
		TreeMap<Integer, Integer> [] currentData = (TreeMap[]) getGraphType(type);
		currentData[u].put(v, capa);
	}

	@Override
	public int getCapacity(int u, int v, int type) {
		TreeMap<Integer, Integer>[] currentData = (TreeMap[]) getGraphType(type);
		if(currentData[u]==null) currentData[u] = new TreeMap<Integer, Integer>();
		Integer res = currentData[u].get(v);
		if (res==null) return -1;
		return res;
	}

	@Override
	public void setCapacity(int u, int v, int newCapa, int type) {
		TreeMap<Integer, Integer>[] currentData = (TreeMap[]) getGraphType(type);
		currentData[u].replace(v, newCapa);
	}

	@Override
	public Object[] getGraphType(int type) {
		if(type==1) return capaMatrix;
		if(type==2) return bestFlow;
		return null;
	}

	@Override
	public int getAdjacentsSize(int v) {
		return capaMatrix[v].size();
	}
}
