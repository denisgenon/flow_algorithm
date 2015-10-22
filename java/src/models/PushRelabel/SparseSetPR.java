package models.PushRelabel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import object.Vertex;
import interfaces.PushRelabelGraph;

public class SparseSetPR implements PushRelabelGraph{
	public Vertex [] vertices;
	public int V;
	public int E;

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
				// TODO

			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getV() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getE() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vertex getVertex(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertex[] getVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void chargeCapa(Vertex origin, Vertex desti, int capa,
			ArrayList<Vertex> actifV) {
		// TODO Auto-generated method stub

	}

	@Override
	public void chargeMax(Vertex origin, Vertex desti, ArrayList<Vertex> actifV) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCapacity(Vertex u, Vertex v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFlowValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
