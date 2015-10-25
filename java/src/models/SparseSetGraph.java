package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import object.SparseSet;
import object.Tuple;
import object.Vertex;
import interfaces.Graph;

public class SparseSetGraph implements Graph{
	public Vertex [] vertices;
	public SparseSet[] capaMatrix;
	public int V;
	public int E;
	
	public SparseSetGraph(String filePath) {
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
			capaMatrix = new SparseSet[V];

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
				if(capaMatrix[idVertex1]==null) capaMatrix[idVertex1]= new SparseSet();
				if(capaMatrix[idVertex2]==null) capaMatrix[idVertex2]= new SparseSet();
				capaMatrix[idVertex1].add(new Tuple(capa,idVertex2));
				capaMatrix[idVertex2].addFutur(new Tuple(0,idVertex1));
			}
			
			for(SparseSet s : capaMatrix){
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
		int index=-1;
		for(int i=0; i<capaMatrix[u.id].map.length; i++){
			if(capaMatrix[u.id].map[i]==v.id) {
				index=i;
				i=capaMatrix[u.id].map.length;
			}
		}
		return capaMatrix[u.id].dom[index].getCapa();
	}
	
	// Type : utile seulement pour AP
	public Tuple getTuple(Vertex u, Vertex v, int type) {
		int index=-1;
		for(int i=0; i<capaMatrix[u.id].size; i++){
			if(capaMatrix[u.id].map[i]==v.id) {
				index=i;
				i=capaMatrix[u.id].map.length;
			}
		}
		if (index==-1) return null;
		return capaMatrix[u.id].dom[index];
	}

	@Override
	public int getFlowValue() {
		return vertices[V-1].e;
	}

	@Override
	public ArrayList<Vertex> getAdjacents(Vertex vertex) {
		SparseSet s = capaMatrix[vertex.id];
		ArrayList<Vertex> adja = new ArrayList<Vertex>();
		for(int i=0; i<s.size; i++){
			adja.add(vertices[s.dom[i].index]);
		}
		return adja;
	}

	@Override
	public int remove(Vertex u, Vertex v) {
		return capaMatrix[u.id].remove(v.id);
	}

	@Override
	public void add(Vertex u, Vertex v, int capa, int type) {
		if(type==1) capaMatrix[u.id].add(v.id, capa);	
	}

}
