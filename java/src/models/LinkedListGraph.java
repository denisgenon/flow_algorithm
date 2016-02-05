package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import interfaces.Graph;
import object.Node;
import object.SimpleLinkedList;
import object.Vertex;

public class LinkedListGraph extends SimpleGraph implements Graph {
	public SimpleLinkedList[] residualGraph;
	
	int gA=0, rE=0, aE=0, gC=0, sC=0;

	public LinkedListGraph(String filePath) {
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
			residualGraph = new SimpleLinkedList[V];
			vertices = new Vertex[V];
			maxCapa = 0;
			
			for (int j = 0; j < V; j++) {
				residualGraph[j] = new SimpleLinkedList();
			}

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
				
				residualGraph[idVertex1].addNode(idVertex2, capa);
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public int getFlowValue() {
		System.out.println("gA : "+gA);
		System.out.println("rE : "+rE);
		System.out.println("aE : "+aE);
		System.out.println("gC : "+gC);
		System.out.println("sC : "+sC);
		
		return vertices[V-1].e;
	}

	@Override
	public int [] getAdjacents(int vertex) {
		gA++;
		
		int [] adja = new int [getAdjacentsSize(vertex)];
		Node n = residualGraph[vertex].getFirst();
		int i = 0;
		while(n!=null){
			adja[i]=n.getElement().getIndex();
			i++;
			n = n.getNext();
		}
		return adja;
	}

	@Override
	public int removeEdge(int u, int v) {
		rE++;
		return residualGraph[u].removeNode(v);
	}

	@Override
	public void addEdge(int u, int v, int capa) {
		aE++;
		residualGraph[u].addNode(v, capa);
	}

	@Override
	public int getCapacity(int u, int v) {
		gC++;
		Node myN = residualGraph[u].getNode(v); //TODO change name
		if (myN != null) return myN.getElement().getCapacity();
		else return -1;
	}

	
	@Override
	public void setCapacity(int u, int v, int newCapa) {
		sC++;
		residualGraph[u].getNode(v).getElement().setCapa(newCapa);
	}


	@Override
	public int getAdjacentsSize(int i) {
		return residualGraph[i].getSize();
	}
}