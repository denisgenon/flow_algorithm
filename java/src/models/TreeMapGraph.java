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
	public TreeMap<Integer, Integer> [] capaMatrix; //TODO change name

	public TreeMapGraph(String filePath) {
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
			capaMatrix = new TreeMap[V];
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
	public int getFlowValue() {
		return vertices[V-1].e;
	}

	@Override
	public int removeEdge(int u, int v) {
		return capaMatrix[u].remove(v);
	}

	@Override
	public void addEdge(int u, int v, int capa) {
		capaMatrix[u].put(v, capa);
	}

	@Override
	public int getCapacity(int u, int v) {
		if(capaMatrix[u]==null) capaMatrix[u] = new TreeMap<Integer, Integer>();
		Integer res = capaMatrix[u].get(v);
		if (res==null) return -1;
		return res;
	}


	@Override
	public void setCapacity(int u, int v, int newCapa) {
		capaMatrix[u].replace(v, newCapa);
	}

	@Override
	public int getAdjacentsSize(int v) {
		return capaMatrix[v].size();
	}
}
