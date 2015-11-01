package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import interfaces.Graph;
import object.MyHashMap;
import object.Vertex;

public class HashMapGraph extends SimpleGraph implements Graph {
	public MyHashMap[] capaMatrix;
	public MyHashMap[] bestFlow;

	public HashMapGraph(String filePath) {
		parse(filePath);
		bestFlow = new MyHashMap[V];
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
			capaMatrix = new MyHashMap[V];

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
				if(capaMatrix[idVertex1]==null) capaMatrix[idVertex1]= new MyHashMap();
				if(capaMatrix[idVertex2]==null) capaMatrix[idVertex2]= new MyHashMap();
				capaMatrix[idVertex1].map.put(idVertex2, capa);
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Vertex> getAdjacents(Vertex vertex) {
		HashMap<Integer, Integer> myMap = capaMatrix[vertex.id].map;
		ArrayList<Vertex> myArray = new ArrayList<Vertex>();

		Iterator<Integer> keySetIterator = myMap.keySet().iterator(); 
		while(keySetIterator.hasNext()){ 
			Integer key = keySetIterator.next();
			myArray.add(vertices[key]);
		}

		return myArray;
	}

	@Override
	public int getFlowValue(int type) {
		if(type==1){
			int value = 0;
			HashMap<Integer, Integer> myMap = bestFlow[V-1].map;
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
	public int removeEdge(Vertex u, Vertex v) {
		return capaMatrix[u.id].map.remove(v.id);
	}

	@Override
	public void addEdge(Vertex u, Vertex v, int capa, int type) {
		MyHashMap[] currentData = (MyHashMap[]) getGraphType(type);
		currentData[u.id].map.put(v.id, capa);
	}

	@Override
	public int getCapacity(int u, int v, int type) {
		MyHashMap[] currentData = (MyHashMap[]) getGraphType(type);
		if(currentData[u]==null) currentData[u] = new MyHashMap();
		Integer res = currentData[u].map.get(v);
		if (res==null) return -1;
		return res;
	}

	@Override
	public void setCapacity(Vertex u, Vertex v, int newCapa, int type) {
		MyHashMap[] currentData = (MyHashMap[]) getGraphType(type);
		currentData[u.id].map.replace(v.id, newCapa);
	}

	@Override
	public Object[] getGraphType(int type) {
		if(type==1) return capaMatrix;
		if(type==2) return bestFlow;
		return null;
	}

}
