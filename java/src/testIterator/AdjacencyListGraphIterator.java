package testIterator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import object.NodeIter;
import object.Vertex;

public class AdjacencyListGraphIterator extends SimpleGraphIter implements GraphIterator {
	public SimpleLinkedListIterator[] voisins;

	public AdjacencyListGraphIterator(String filePath) {
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
			voisins = new SimpleLinkedListIterator[V];

			vertices = new Vertex[V];
			capacities = new HashMap<>();
			capacitiesBestFlow = new HashMap<>();
			
			for (int j = 0; j < V; j++) {
				voisins[j] = new SimpleLinkedListIterator();
			}

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
				voisins[idVertex1].addNode(idVertex2);
				capacities.put(new Arc(idVertex1, idVertex2), capa);
				capacitiesBestFlow.put(new Arc(idVertex1, idVertex2), 0);
				
				voisins[idVertex2].addNode(idVertex1);
				capacities.put(new Arc(idVertex2, idVertex1), 0); // TODO si elle existe déjà, pas mettre la capa à 0
				capacitiesBestFlow.put(new Arc(idVertex2, idVertex1), 0);
				
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public int getFlowValue(int type) {
		if(type==1){
			int value = 0;
			NodeIter i = voisins[getV()-1].getFirst();

			while(i != null){
				value += capacitiesBestFlow.get(new Arc(getV()-1, i.getId()));
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
	public Iterator<Integer> getAdjacents(int vertex) {
		return voisins[vertex].iterator();
	}
	
	public int getAdjacentsSize(int i) {
		return voisins[i].getSize();
	}
}