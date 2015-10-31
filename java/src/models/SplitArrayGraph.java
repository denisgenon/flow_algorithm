package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import interfaces.Graph;
import object.Edge;
import object.SplitArray;
import object.Vertex;

public class SplitArrayGraph extends SimpleGraph implements Graph{
	public SplitArray[] capaMatrix;
	public SplitArray[] bestFlow;


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
			bestFlow = new SplitArray[V];

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

				// On initialise le graph des flots
				if(bestFlow[idVertex1]==null) bestFlow[idVertex1]= new SplitArray();
				if(bestFlow[idVertex2]==null) bestFlow[idVertex2]= new SplitArray();
				bestFlow[idVertex1].addFutur(new Edge(0,idVertex2));
				bestFlow[idVertex2].addFutur(new Edge(0,idVertex1));

			}

			for(SplitArray s : capaMatrix){
				s.compile();
			}

			for(SplitArray s : bestFlow){
				s.compile();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public int getCapacity(Vertex u, Vertex v, int type) {
		SplitArray[] currentData = (SplitArray[]) getGraphType(type);
		for(int i=0; i<currentData[u.id].split; i++){
			if(currentData[u.id].dom[i].idDesti==v.id) {
				return currentData[u.id].dom[i].getCapacity();
			}
		}
		return -1;
	}

	@Override
	public void setCapacity(Vertex u, Vertex v, int newCapa, int type) {
		SplitArray[] currentData = (SplitArray[]) getGraphType(type);
		for(int i=0; i<currentData[u.id].split; i++){
			if(currentData[u.id].dom[i].idDesti==v.id) {
				currentData[u.id].dom[i].capa=newCapa;
			}
		}

	}

	@Override
	public int getFlowValue(int type) {
		if(type==1) {
			int value=0;
			for(int i=0; i<bestFlow[V-1].split; i++){
				value+=bestFlow[V-1].dom[i].capa;
			}
			return value;
		}
		if(type==2) return vertices[V-1].e;
		return -1;
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
		SplitArray[] currentData = (SplitArray[]) getGraphType(type);
		currentData[u.id].add(v.id, capa);	
	}

	@Override
	public Object[] getGraphType(int type) {
		if(type==1) return capaMatrix;
		if(type==2) return bestFlow;
		return null;
	}

}
