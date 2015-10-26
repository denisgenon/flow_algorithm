package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import object.SplitArray;
import object.Edge;
import object.Vertex;
import interfaces.Graph;

public class SplitArrayGraph implements Graph{
	public Vertex [] vertices;
	public SplitArray[] capaMatrix;
	public int V;
	public int E;
	
	public SplitArrayGraph(String filePath) {
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
			vertices = new Vertex[V];
			capaMatrix = new SplitArray[V];

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

				// On ajoute le voisin+distance dans le tableau de sparse Set
				if(capaMatrix[idVertex1]==null) capaMatrix[idVertex1]= new SplitArray();
				if(capaMatrix[idVertex2]==null) capaMatrix[idVertex2]= new SplitArray();
				capaMatrix[idVertex1].add(new Edge(capa,idVertex2));
				capaMatrix[idVertex2].addFutur(new Edge(0,idVertex1));
			}
			
			for(SplitArray s : capaMatrix){
				s.compile();
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
	public int getCapacity(Vertex u, Vertex v) {
		return getEdge(u,v,1).getCapacity();
	}
	
	// Type : utile seulement pour AP
	public Edge getEdge(Vertex u, Vertex v, int type) {
		for(int i=0; i<capaMatrix[u.id].split; i++){
			if(capaMatrix[u.id].dom[i].idDesti==v.id) {
				return capaMatrix[u.id].dom[i];
			}
		}
		return null;
		
	}

	@Override
	public int getFlowValue() {
		return vertices[V-1].e;
	}

	@Override
	public ArrayList<Vertex> getAdjacents(Vertex vertex) {
		SplitArray s = capaMatrix[vertex.id];
		ArrayList<Vertex> adja = new ArrayList<Vertex>();
		for(int i=0; i<s.split; i++){
			adja.add(vertices[s.dom[i].idDesti]);
		}
		return adja;
	}

	@Override
	public int removeEdge(Vertex u, Vertex v) {
		return capaMatrix[u.id].remove(v.id);
	}

	@Override
	public void addEdge(Vertex u, Vertex v, int capa, int type) {
		if(type==1) capaMatrix[u.id].add(v.id, capa);	
	}

}
