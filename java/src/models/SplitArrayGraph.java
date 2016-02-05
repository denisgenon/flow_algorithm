package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import interfaces.Graph;
import object.Edge;
import object.SplitArray;
import object.Vertex;

public class SplitArrayGraph extends SimpleGraph implements Graph{
	public SplitArray[] capaMatrix;


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
			maxCapa = 0;

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

				// On ajoute le voisin+distance dans le tableau de sparse Set
				if(capaMatrix[idVertex1]==null) capaMatrix[idVertex1]= new SplitArray();
				if(capaMatrix[idVertex2]==null) capaMatrix[idVertex2]= new SplitArray();
				capaMatrix[idVertex1].add(new Edge(capa,idVertex2));
				capaMatrix[idVertex2].addFutur(new Edge(0,idVertex1)); //TODO: add futur ????

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
	public int getCapacity(int u, int v) {
		for(int i=0; i<capaMatrix[u].split; i++){
			if(capaMatrix[u].dom[i].idDesti==v) {
				return capaMatrix[u].dom[i].getCapacity();
			}
		}
		return -1;
	}

	@Override
	public void setCapacity(int u, int v, int newCapa) {
		for(int i=0; i<capaMatrix[u].split; i++){
			if(capaMatrix[u].dom[i].idDesti==v) {
				capaMatrix[u].dom[i].capa=newCapa;
			}
		}

	}


	@Override
	public int getFlowValue() {
		return vertices[V-1].e;
	}

	@Override
	public int [] getAdjacents(int vertex) {
		SplitArray s = capaMatrix[vertex];
		int [] adja = new int [getAdjacentsSize(vertex)];
		for(int i=0; i<s.split; i++){
			adja[i]=s.dom[i].idDesti;
		}
		return adja;
	}

	@Override
	public int removeEdge(int u, int v) {
		return capaMatrix[u].remove(v);
	}

	@Override
	public void addEdge(int u, int v, int capa) {
		capaMatrix[u].add(v, capa);	
	}

	
	@Override
	public int getAdjacentsSize(int v) {
		return capaMatrix[v].split;
	}

}
