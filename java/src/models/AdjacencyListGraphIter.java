package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import interfaces.GraphIter;
import object.Arc;
import object.NodeIter;
import object.SimpleLinkedListIter;
import object.Vertex;

public class AdjacencyListGraphIter extends SimpleGraphIter implements GraphIter {
	public SimpleLinkedListIter[] capaMatrix;
	public SimpleLinkedListIter[] bestFlow; // que pour Augmenting Path

	public AdjacencyListGraphIter(String filePath) {
		parse(filePath);
	}

	@Override
	public void parse(String filePath) {
		try {
			BufferedReader br;

			br = new BufferedReader(new FileReader(filePath));

			String line = br.readLine();

			// Number of items
			String[] data = line.split(" ");
			V = Integer.parseInt(data[0]);
			E = Integer.parseInt(data[1]);
			capaMatrix = new SimpleLinkedListIter[V];
			bestFlow = new SimpleLinkedListIter[super.V];
			vertices = new Vertex[V];
			capacities = new HashMap<>();
			
			for (int j = 0; j < V; j++) {
				capaMatrix[j] = new SimpleLinkedListIter();
				bestFlow[j] = new SimpleLinkedListIter();
			}

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
				capaMatrix[idVertex1].addNode(idVertex2);
				capacities.put(new Arc(idVertex1, idVertex2), capa);
				capaMatrix[idVertex2].addNode(idVertex1);
				capacities.put(new Arc(idVertex2, idVertex1), 0);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public int getFlowValue(int type) {
		if(type==1){
			int value = 0;
			NodeIter i = bestFlow[bestFlow.length-1].getFirst();

			while(i != null){
				value += capacities.get(new Arc(bestFlow.length-1, i.getId()));
				i = i.getNext();
			}
			return value;
		}
		if(type==2){
			return vertices[V-1].e;
		}
		return -1;
	}

	@Override
	public Iterator<Integer> getAdjacents(int vertex) {
		return capaMatrix[vertex].iterator();
	}

	/*
	@Override
	public int removeEdge(int u, int v) {
		return capaMatrix[u].removeNode(v);
	}

	@Override
	public void addEdge(int u, int v, int capa, int type) {
		SimpleLinkedList[] currentData = (SimpleLinkedList[]) getGraphType(type);
		currentData[u].addNode(v, capa);
	}
	*/
	
	public Object[] getGraphType(int type) {
		if(type==1) return capaMatrix;
		if(type==2) return bestFlow;
		return null;
	}

	public int getAdjacentsSize(int i) {
		return capaMatrix[i].getSize();
	}
}