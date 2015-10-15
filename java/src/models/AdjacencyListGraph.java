package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import object.Node;
import object.Vertex;

public class AdjacencyListGraph implements AugmentingPathGraph {
	public Node[] capaMatrix;
	public Node[] bestFlow;
	public Vertex [] vertices;
	public int V;
	public int E;

	public AdjacencyListGraph(String filePath) {
		parse(filePath);
		bestFlow = new Node[V];
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

				// On ajoute les voisins dans les vertices
				vertices[idVertex1].adjacents.add(vertices[idVertex2]);

				// On ajoute la distance dans la matrice des distances
				Node.addNode(idVertex1, idVertex2, capa, capaMatrix);

			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getV() {
		return V;
	}

	@Override
	public ArrayList<Vertex> getNeighbors(Vertex vertex) {
		Vertex v = (Vertex) vertex;
		return v.adjacents;
	}

	@Override
	public void setNeighbors(Vertex u, List<Vertex> vertices) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vertex getVertex(int id) {
		return vertices[id];
	}

	@Override
	public int getMinFlow(Vertex[] path) {
		int minFlow=Integer.MAX_VALUE;
		for(int i=0; i<path.length-1; i++) {
			minFlow=Math.min(minFlow, Node.getNode(path[i+1].id, path[i].id, capaMatrix).capa);
		}
		return minFlow;
	}

	@Override
	public int getCapacity(Vertex u, Vertex v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCapacity(Vertex u, Vertex v, int c) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFlowValue() {
		int value = 0;
		Node t = bestFlow[bestFlow.length-1];
		while(t != null){
			value += t.capa;
			t = t.next;
		}
		return value;
	}

	@Override
	public void applyPath(int capacity, Vertex[] path) {
		for(int i=0; i<path.length-1; i++) {
			Node myT = Node.getNode(path[i+1].id, path[i].id, capaMatrix);
			if(myT.capa<=capacity) { // On enleve l'arete si la capa dispo est 0
				Node.removeNode(path[i+1].id, path[i].id, capaMatrix);
				path[i+1].adjacents.remove(path[i]);
			}
			else { // on enleve la capa dans le bon sens sinon
				myT.capa-=capacity;
			}
			
			myT = Node.getNode(path[i].id, path[i+1].id, capaMatrix);
			if(myT==null) { // on crée l'arete si elle n'existe pas
				Node.addNode(path[i].id, path[i+1].id,capacity, capaMatrix); 
				path[i].adjacents.add(path[i+1]);
			}
			else { // on rajoute la capa dans le sens inverse sinon
				myT.capa+=capacity;
			}
			
			myT = Node.getNode(path[i].id, path[i+1].id, bestFlow); // on augmente le flot courant	
			if(myT==null) Node.addNode(path[i].id, path[i+1].id, capacity, bestFlow); 
			else {
				myT.capa+=capacity;
			}
		}
	}

}
