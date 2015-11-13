package object;

import java.util.ArrayList;
import java.util.Iterator;

public class SplitArray {

	public Edge [] dom;
	public int split;
	public ArrayList<Edge> adjacents;
	public ArrayList<Edge> futurAdjacents;

	public SplitArray(){
		adjacents = new ArrayList<Edge>();
		futurAdjacents = new ArrayList<Edge>();
	}
	
	public void add(Edge t) {
		adjacents.add(t);
	}
	
	public void addFutur(Edge t) {
		futurAdjacents.add(t);
	}
	
	public int remove(int i) {
		
		int oldCapa=-1;
		
		int index=-1;
		for(int in=0; in<split; in++){
			if(dom[in].idDesti==i) {
				index=in;
				in=dom.length;
			}
		}
		int newIndex = split-1;
		
		Edge t = dom[index];
		oldCapa=t.capa;
		dom[index]=dom[newIndex];
		dom[newIndex]=t;
		
		split--;
		
		return oldCapa;
	}
	
	public void add(int i, int capa){
		int index=-1;
		for(int in=split; in<dom.length; in++){
			if(dom[in].idDesti==i) {
				index=in;
				in=dom.length;
			}
		}
		int newIndex = split;

		Edge t = dom[index];
		dom[index]=dom[newIndex];
		dom[newIndex]=t;
		dom[newIndex].setCapa(capa);
		
		split++;
	}
	
	public void compile() {
		dom=new Edge [adjacents.size()+futurAdjacents.size()];
		int bigSize = dom.length;
		split=bigSize-futurAdjacents.size();
		int index=0;
		for(Edge t : adjacents){
			dom[index]=t;
			index++;
		}
		for(Edge t : futurAdjacents){
			dom[index]=t;
			index++;
		}
		
		adjacents=null;
		futurAdjacents=null;
	}
	
	public void print(){
		String sdom="";
		for(int i=0; i<dom.length; i++){
			if(i==split){
				sdom+=("|");
			}
			sdom+=(dom[i]+" ");
		}
		System.out.println(sdom);
		System.out.println();
	}
	
	public Edge[] getDom() {
		return dom;
	}
	
	public int getSize() {
		return split;
	}
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			int index = 0;
			
			@Override
			public boolean hasNext() {
				return index < split;
			}

			@Override
			public Integer next() {
				int id = 0;// TODO
				//curr = curr.getNext();
				return id;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			};
		};
	}
}
