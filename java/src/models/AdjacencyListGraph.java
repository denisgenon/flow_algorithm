package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import interfaces.Graph;
import object.Node;
import object.SimpleLinkedList;
import object.Vertex;

public class AdjacencyListGraph extends SimpleGraph implements Graph {
	public SimpleLinkedList[] capaMatrix;
	public SimpleLinkedList[] bestFlow; // que pour Augmenting Path

	/*public int cgetAdjacents = 0;
	public int cremoveEdge = 0;
	public int caddEdge = 0;
	public int cgetCapacity = 0;
	public int csetCapacity = 0;*/

	public AdjacencyListGraph(String filePath) {

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
			capaMatrix = new SimpleLinkedList[V];
			bestFlow = new SimpleLinkedList[super.V];
			vertices = new Vertex[V];
			maxCapa = 0;
			
			for (int j = 0; j < V; j++) {
				capaMatrix[j] = new SimpleLinkedList();
				bestFlow[j] = new SimpleLinkedList();
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
				
				capaMatrix[idVertex1].addNode(idVertex2, capa);
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public int getFlowValue(int type) {
		/*System.out.println("getAdjacents : "+cgetAdjacents);
		System.out.println("removeEdge : "+cremoveEdge);
		System.out.println("addEdge : "+caddEdge);
		System.out.println("getCapacity : "+cgetCapacity);
		System.out.println("setCapacity : "+csetCapacity);*/
		
		if(type==1){
			int value = 0;
			
			Node i = bestFlow[bestFlow.length-1].getFirst();

			while(i != null){
				value += i.getElement().getCapacity();
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
	public int [] getAdjacents(int vertex) {
		
		int [] adja = new int [getAdjacentsSize(vertex)];
		Node n = capaMatrix[vertex].getFirst();
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
		//cremoveEdge++;
		return capaMatrix[u].removeNode(v);
	}

	@Override
	public void addEdge(int u, int v, int capa, int type) {
		//caddEdge++;
		SimpleLinkedList[] currentData = (SimpleLinkedList[]) getGraphType(type);
		currentData[u].addNode(v, capa);
	}

	@Override
	public int getCapacity(int u, int v, int type) {
		//cgetCapacity++;
		SimpleLinkedList[] currentData = (SimpleLinkedList[]) getGraphType(type);
		Node myN = currentData[u].getNode(v);
		if (myN != null) return myN.getElement().getCapacity();
		else return -1;

	}

	@Override
	public void setCapacity(int u, int v, int newCapa, int type) {
		//csetCapacity++;
		SimpleLinkedList[] currentData = (SimpleLinkedList[]) getGraphType(type);
		currentData[u].getNode(v).getElement().setCapa(newCapa);
	}
	
	@Override
	public Object[] getGraphType(int type) {
		if(type==1) return capaMatrix;
		if(type==2) return bestFlow;
		return null;
	}

	@Override
	public int getAdjacentsSize(int i) {
		return capaMatrix[i].getSize();
	}
}