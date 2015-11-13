package solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import interfaces.Graph;
import object.Vertex;

public class FordFulkerson extends AugmentingPath {
	public FordFulkerson(Graph g) {
		super(g);
	}
	public int [] getPath() {

		parents = new int [g.getV()];

		for(int i=0; i<g.getV(); i++) {
			parents[i]=-1;
		}

		visitDFS(0);

		ArrayList<Integer> mypath = new ArrayList<Integer>();
		int indexpath = g.getV() - 1;
		mypath.add(indexpath); 
		while(parents[indexpath]!=-1 && indexpath!=0){
			mypath.add(parents[indexpath]); 
			indexpath=parents[indexpath];
		}
		// TODO Faire une arrayList de Vertex et le convertir en tableau ou faire une arrayList d'integer et le convertir en tableau d'int (via une itération)?
		if(indexpath==0) {
			int [] res = new int [mypath.size()];
			int index=0;
			for(int i : mypath){
				res[index]=i;
				index++;
			}
			return res;
		}
		return null;
	}
}
