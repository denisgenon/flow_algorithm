package object;

import java.util.ArrayList;

public class SplitArray {

	public Arc [] dom;
	public int split;
	public ArrayList<Arc> adjacents;
	public ArrayList<Arc> futurAdjacents;

	public SplitArray(){
		adjacents = new ArrayList<Arc>();
		futurAdjacents = new ArrayList<Arc>();
	}
	
	public void add(Arc t) {
		adjacents.add(t);
	}
	
	public void addFutur(Arc t) {
		futurAdjacents.add(t);
	}
	
	public int remove(int i) {
		
		int oldCapacity=-1;
		
		int index=-1;
		for(int in=0; in<split; in++){
			if(dom[in].idDestination==i) {
				index=in;
				in=dom.length;
			}
		}
		int newIndex = split-1;
		
		Arc t = dom[index];
		oldCapacity=t.capacity;
		dom[index]=dom[newIndex];
		dom[newIndex]=t;
		
		split--;
		
		return oldCapacity;
	}
	
	public void add(int i, int capacity){
		int index=-1;
		for(int in=split; in<dom.length; in++){
			if(dom[in].idDestination==i) {
				index=in;
				in=dom.length;
			}
		}
		int newIndex = split;

		Arc t = dom[index];
		dom[index]=dom[newIndex];
		dom[newIndex]=t;
		dom[newIndex].setCapacity(capacity);
		
		split++;
	}
	
	public void compile() {
		dom=new Arc [adjacents.size()+futurAdjacents.size()];
		int bigSize = dom.length;
		split=bigSize-futurAdjacents.size();
		int index=0;
		for(Arc t : adjacents){
			dom[index]=t;
			index++;
		}
		for(Arc t : futurAdjacents){
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
	
	public Arc[] getDom() {
		return dom;
	}
	
	public int getSize() {
		return split;
	}
}
