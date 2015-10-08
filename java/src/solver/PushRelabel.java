package solver;

import java.util.ArrayList;
import java.util.LinkedList;

import object.Node;
import object.Vertex;
import flowAlgorithm.FlowAlgorithmInstance;

public class PushRelabel {
	public FlowAlgorithmInstance instance;
	public ArrayList<Vertex> actifV = new ArrayList<Vertex>();

	public void preProcess() {
		// TODO BFS sur tous les noeuds pour mettre leurs h
		for(Vertex v : instance.vertices[0].adjacents) {
			chargeMax(instance.vertices[0],v);
		}
		instance.vertices[0].h=instance.E;
	}

	public void process() {
		preProcess();
		while(!actifV.isEmpty()){
			Vertex elu = actifV.get(0);// on prend un actif
			pushRelabel(elu);
		}
	}

	public void pushRelabel(Vertex v) {
		int hMin=Integer.MAX_VALUE;
		for(Vertex u : v.adjacents) {
			hMin=Math.min(hMin, u.h);
			if (u.h-1==v.h) {
				chargeCapa(v,u,Math.min(v.e,Node.getNode(v.id,u.id,instance.capaMatrix).capa)); // push
				return;
			}
		}
		v.h=hMin+1;
	}

	public void initH(){
		
		int[] distances = new int[instance.V];
		Vertex[] parent = new Vertex[instance.V];

		for (int i = 0; i < instance.V; i++) {
			distances[i] = Integer.MAX_VALUE;
			parent[i] = null;
		}

		LinkedList<Vertex> queue = new LinkedList<>();

		// Sommet ?
		distances[0] = 0;
		queue.add(instance.vertices[0]);
		while (!queue.isEmpty()) {
			Vertex u = queue.removeFirst();
			for (Vertex v : u.adjacents) {
				if (distances[v.id] == Integer.MAX_VALUE) {
					distances[v.id] = distances[u.id] + 1;
					parent[v.id] = u;
					queue.add(v);
				}
			}
		}
		int index = instance.V-1;
		int i = 0;
		while (parent[index] != null && index != 0) {
			instance.vertices[index].h=i;
			index = parent[index].id;
			i++;
		}
	}

	public void chargeCapa(Vertex origin, Vertex desti, int capa) {

		Node myT = Node.getNode(origin.id, desti.id,instance.capaMatrix);
		if(myT.capa<=capa) { // On enleve l'arete si la capa dispo est 0
			Node.removeNode(origin.id, desti.id,instance.capaMatrix);
			origin.adjacents.remove(desti);
		}
		else { // on enleve la capa dans le bon sens sinon
			myT.capa-=capa;
		}

		origin.e-=capa;
		if(origin.e<=0)actifV.remove(origin);
		desti.e+=capa;
		if(desti.e>0)actifV.add(desti);

		myT = Node.getNode(desti.id, origin.id,instance.capaMatrix);
		if(myT==null) { // on crée l'arete si elle n'existe pas
			Node.addNode(desti.id, origin.id,capa,instance.capaMatrix); 
			desti.adjacents.add(origin);
		}
		else { // on rajoute la capa dans le sens inverse sinon
			myT.capa+=capa;
		}
	}

	public void chargeMax(Vertex origin, Vertex desti) {

		int newCapa = Node.removeNode(origin.id, desti.id, instance.capaMatrix);
		Node.addNode(desti.id,origin.id,newCapa,instance.capaMatrix);

		origin.adjacents.remove(desti);
		if(!desti.adjacents.contains(origin)) desti.adjacents.add(origin);
		
		desti.e+=newCapa;
		if(desti.e>0) actifV.add(desti);
	}
}
