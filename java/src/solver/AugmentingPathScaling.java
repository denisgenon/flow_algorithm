package solver;

import interfaces.Graph;

public abstract class AugmentingPathScaling {
	public Graph g;
	public long timeStart;

	public AugmentingPathScaling(Graph g) {
		this.g = g;
		timeStart=System.currentTimeMillis();
		int delta = (int) (2*Math.log(g.getMaxCapacity()));
		while(delta>=1) {
			int [] myPath = getPath(delta);
			if(myPath == null) {
				delta = (int) Math.round(delta/2);
			}
			else {
				applyPath(getMinFlow(myPath),myPath);
			}
		}
	}

	public int getMinFlow(int[] path) {
		int minFlow=Integer.MAX_VALUE;
		for(int i=0; i<path.length-1; i++) {
			minFlow=Math.min(minFlow,g.getCapacity(path[i+1], path[i],1));
		}
		return minFlow;
	}

	public void getResult() {

		System.out.println("|V| : "+g.getV());
		System.out.println("|E| : "+g.getE());
		System.out.println("Max flot : " + g.getFlowValue(1));
		System.out.println("Temps d'execution : "+(System.currentTimeMillis()-timeStart)+" ms"+"\n");
	}

	public abstract int [] getPath(int delta);

	public void applyPath(int capacity, int[] path) {
		for(int i=0; i<path.length-1; i++) {
			int currentCapa = g.getCapacity(path[i+1], path[i],1);
			if(currentCapa<=capacity) { // On enleve l'arete si la capa dispo est 0
				g.removeEdge(path[i+1], path[i]);
			}
			else { // on enleve la capa dans le bon sens sinon
				g.setCapacity(path[i+1], path[i], currentCapa-capacity,1);
			}

			currentCapa = g.getCapacity(path[i],path[i+1],1);
			if(currentCapa==-1) { // on crée l'arete si elle n'existe pas
				g.addEdge(path[i], path[i+1], capacity,1);
			}
			else { // on rajoute la capa dans le sens inverse sinon
				g.setCapacity(path[i], path[i+1], currentCapa+capacity,1);
			}

			currentCapa = g.getCapacity(path[i], path[i+1], 2); // on augmente le flot courant	
			if(currentCapa==-1) g.addEdge(path[i], path[i+1], capacity, 2); 
			else {
				g.setCapacity(path[i], path[i+1], currentCapa+capacity,2);
			}
		}
	}
}