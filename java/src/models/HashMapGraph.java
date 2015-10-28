package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import object.MyHashMap;
import object.Vertex;
import interfaces.Graph;

public class HashMapGraph implements Graph {
	public Vertex [] vertices;
	public MyHashMap[] capaMatrix;
	public MyHashMap[] bestFlow;
	public int V;
	public int E;

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
	public int getV() {
		return V;
	}

	@Override
	public int getE() {
		return E;
	}

	@Override
	public Vertex getVertex(int id) {
		return vertices[id];
	}

	@Override
	public Vertex[] getVertices() {
		return vertices;
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
		if (type==1) capaMatrix[u.id].map.put(v.id, capa);
		if (type==2) {
			
			bestFlow[u.id].map.put(v.id, capa);
		}
	}

	@Override
	public int getCapacity(Vertex u, Vertex v, int type) {
		if (type==1){
			Integer res = capaMatrix[u.id].map.get(v.id);
			if (res==null) return -1;
			return res;
		}
		if (type==2){
			if(bestFlow[u.id]==null) bestFlow[u.id] = new MyHashMap();
			Integer res = bestFlow[u.id].map.get(v.id);
			if (res==null) return -1;
			return res;
		}
		return -1;
	}

	@Override
	public void setCapacity(Vertex u, Vertex v, int newCapa, int type) {
		if (type==1) capaMatrix[u.id].map.replace(v.id, newCapa);
		if (type==2) bestFlow[u.id].map.replace(v.id, newCapa);
	}

}
