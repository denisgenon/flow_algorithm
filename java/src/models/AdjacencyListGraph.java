package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import interfaces.Graph;
import object.Node;
import object.Vertex;

public class AdjacencyListGraph extends SimpleGraph implements Graph {
	public Node[] capaMatrix;
	public Node[] bestFlow; // que pour Augmenting Path

	/*public int cgetAdjacents = 0;
	public int cremoveEdge = 0;
	public int caddEdge = 0;
	public int cgetCapacity = 0;
	public int csetCapacity = 0;*/

	public AdjacencyListGraph(String filePath) {
		parse(filePath);
		bestFlow = new Node[super.V];
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
			capaMatrix = new Node [V];
			vertices = new Vertex[V];

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

				// On ajoute la distance dans la matrice des distances
				Node.addNode(idVertex1, idVertex2, capa, capaMatrix);
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
			Node t = bestFlow[bestFlow.length-1];
			while(t != null){
				value += t.capa;
				t = t.next;
			}
			return value;
		}
		if(type==2){
			return vertices[V-1].e;
		}
		return -1;
	}

	@Override
	public ArrayList<Integer> getAdjacents(int vertex) {
		//cgetAdjacents++;
		ArrayList<Integer> adja = new ArrayList<Integer>();
		Node n = capaMatrix[vertex];
		while(n!=null){
			adja.add(n.idDesti);
			n = n.next;
		}
		return adja;
	}

	@Override
	public int removeEdge(int u, int v) {
		//cremoveEdge++;

		return Node.removeNode(u, v, capaMatrix);
	}

	@Override
	public void addEdge(int u, int v, int capa, int type) {
		//caddEdge++;
		Node[] currentData = (Node[]) getGraphType(type);
		Node.addNode(u, v, capa, currentData);
	}

	@Override
	public int getCapacity(int u, int v, int type) {
		//cgetCapacity++;
			
		Node[] currentData = (Node[]) getGraphType(type);
		Node myN = Node.getNode(u,v,currentData);
		if(myN!=null) return myN.capa;
		else return -1;

	}

	@Override
	public void setCapacity(int u, int v, int newCapa, int type) {
		//csetCapacity++;
		Node[] currentData = (Node[]) getGraphType(type);
		Node.getNode(u,v,currentData).capa=newCapa;
	}
	
	public Object[] getGraphType(int type) {
		if(type==1) return capaMatrix;
		if(type==2) return bestFlow;
		return null;
	}
}
