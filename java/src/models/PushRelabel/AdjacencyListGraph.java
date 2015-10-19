package models.PushRelabel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import object.Node;
import object.Vertex;
import interfaces.PushRelabelGraph;

public class AdjacencyListGraph implements PushRelabelGraph {
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
	public void chargeCapa(Vertex origin, Vertex desti, int capa, ArrayList<Vertex> actifV) {
		Node myT = Node.getNode(origin.id, desti.id, capaMatrix);
		if(myT.capa<=capa) { // On enleve l'arete si la capa dispo est 0
			Node.removeNode(origin.id, desti.id,capaMatrix);
			origin.adjacents.remove(desti);
		}
		else { // on enleve la capa dans le bon sens sinon
			myT.capa-=capa;
		}

		origin.e-=capa;
		if(origin.e<=0) actifV.remove(origin);
		desti.e+=capa;
		if(desti.e>0) actifV.add(desti);

		myT = Node.getNode(desti.id, origin.id,capaMatrix);
		if(myT==null) { // on crée l'arete si elle n'existe pas
			Node.addNode(desti.id, origin.id,capa,capaMatrix); 
			desti.adjacents.add(origin);
		}
		else { // on rajoute la capa dans le sens inverse sinon
			myT.capa+=capa;
		}
	}
	
	public void chargeMax(Vertex origin, Vertex desti, ArrayList<Vertex> actifV) {

		int newCapa = Node.removeNode(origin.id, desti.id, capaMatrix);
		Node.addNode(desti.id,origin.id,newCapa,capaMatrix);

		origin.adjacents.remove(desti);
		if(!desti.adjacents.contains(origin)) desti.adjacents.add(origin);
		
		desti.e+=newCapa;
		if(desti.e>0) actifV.add(desti);
	}

	@Override
	public int getCapacity(Vertex v, Vertex u) {
		return Node.getNode(v.id,u.id,capaMatrix).capa;
	}

}
