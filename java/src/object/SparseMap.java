package object;

import java.util.ArrayList;
import java.util.HashMap;

public class SparseMap {

	public Arc [] dom;
	public HashMap<Integer, Integer> map;
	public int split;
	public ArrayList<Arc> adjacents;
	public ArrayList<Arc> futurAdjacents;

	public SparseMap(){
		adjacents = new ArrayList<Arc>();
		futurAdjacents = new ArrayList<Arc>();
		map = new HashMap<Integer, Integer>();
	}
	
	public void add(Arc t) {
		adjacents.add(t);
	}
	
	public void addFutur(Arc t) {
		futurAdjacents.add(t);
	}
	
	public int remove(int i) {
		
		int index = map.get(i);
		Arc t = dom[index];
		int oldCapacity = t.capacity;
		int otheri = dom[split-1].idDestination;
		
		dom[index]=dom[split-1];
		dom[split-1]=t;
		
		map.replace(i, split-1);
		map.replace(otheri, index);
		
		split--;
		return oldCapacity;
	}
	
	public void add(int i, int capacity){
		int index=map.get(i);
		int newIndex = split;
		
		int otheri = dom[split].idDestination;

		Arc t = dom[index];
		dom[index]=dom[newIndex];
		dom[newIndex]=t;
		dom[newIndex].setCapacity(capacity);
		
		map.replace(i, split);
		map.replace(otheri, index);
		
		split++;
	}
	
	public int get(int i){
		int index = map.get(i);
		if(index < split){
			return dom[index].getCapacity();
		}
		else return -1;
	}
	
	public void set(int i, int newCapacity){
		dom[map.get(i)].capacity=newCapacity;
	}
	
	public void compile() {
		dom=new Arc [adjacents.size()+futurAdjacents.size()];
		int bigSize = dom.length;
		split=bigSize-futurAdjacents.size();
		int index=0;
		for(Arc t : adjacents){
			dom[index]=t;
			map.put(t.idDestination, index);
			index++;
		}
		for(Arc t : futurAdjacents){
			dom[index]=t;
			map.put(t.idDestination, index);
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
	
	public HashMap<Integer, Integer> getMap() {
		return map;
	}
	
	public int getSize() {
		return split;
	}
}
