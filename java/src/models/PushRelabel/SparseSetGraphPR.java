package models.PushRelabel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import object.SparseSet;
import object.Tuple;
import object.Vertex;
import interfaces.PushRelabelGraph;

public class SparseSetGraphPR implements PushRelabelGraph{
	public Vertex [] vertices;
	public SparseSet[] capaMatrix;
	public int V;
	public int E;
	
	public SparseSetGraphPR(String filePath) {
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
	public void chargeCapa(Vertex origin, Vertex desti, int capa, ArrayList<Vertex> actifV) {
		Tuple myT = getTuple(origin, desti);
		if(myT.capa<=capa) { // On enleve l'arete si la capa dispo est 0
			capaMatrix[origin.id].remove(desti.id);
		}
		else { // on enleve la capa dans le bon sens sinon
			myT.capa-=capa;
		}
		
		origin.e-=capa;
		if(origin.e<=0) {
			actifV.remove(origin);
		}
		desti.e+=capa;
		if(desti.e>0 && desti.id!=V-1 && desti.id!=0 && !actifV.contains(desti)) {
			actifV.add(desti);
		}

		myT = getTuple(desti, origin);
		if(myT==null) { // on crée l'arete si elle n'existe pas
			capaMatrix[desti.id].add(origin.id,capa); 
		}
		else { // on rajoute la capa dans le sens inverse sinon
			myT.capa+=capa;
		}

	}

	@Override
	public void chargeMax(Vertex origin, Vertex desti, ArrayList<Vertex> actifV) {
		// On enleve desti des voisins de origin
		int capa = getCapacity(origin, desti);
		capaMatrix[origin.id].remove(desti.id);

		// On ajoute origin aux voisins de desti
		capaMatrix[desti.id].add(origin.id, capa);

		desti.e+=capa;
		if(desti.e>0) {
			actifV.add(desti);
		}
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
	
	public Tuple getTuple(Vertex u, Vertex v) {
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
	public ArrayList<Vertex> getAdjacent(Vertex vertex) {
		SparseSet s = capaMatrix[vertex.id];
		ArrayList<Vertex> adja = new ArrayList<Vertex>();
		for(int i=0; i<s.size; i++){
			adja.add(vertices[s.dom[i].vertex]);
		}
		return adja;
	}

}
