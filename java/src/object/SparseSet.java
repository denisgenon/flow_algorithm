package object;

import java.util.ArrayList;

public class SparseSet {

	public Tuple [] dom;
	public int [] map;
	public int size;
	public ArrayList<Tuple> adjacents;
	public ArrayList<Tuple> futurAdjacents;

	public SparseSet(){
		adjacents = new ArrayList<Tuple>();
		futurAdjacents = new ArrayList<Tuple>();
	}
	
	public void add(Tuple t) {
		adjacents.add(t);
	}
	
	public void addFutur(Tuple t) {
		futurAdjacents.add(t);
	}
	
	public void remove(int i) {
		int index=-1;
		for(int in=0; in<size; in++){
			if(map[in]==i) {
				index=in;
				in=map.length;
			}
		}
		int newIndex = size-1;
		
		Tuple t = dom[index];
		dom[index]=dom[newIndex];
		dom[newIndex]=t;
		
		int ind = map[index];
		map[index]=map[newIndex];
		map[newIndex]=ind;
		
		size--;
	}
	
	public void add(int i, int capa){
		int index=-1;
		for(int in=size; in<map.length; in++){
			if(map[in]==i) {
				index=in;
				in=map.length;
			}
		}
		int newIndex = size;

		Tuple t = dom[index];
		dom[index]=dom[newIndex];
		dom[newIndex]=t;
		dom[newIndex].setCapa(capa);
		
		int ind = map[index];
		map[index]=map[newIndex];
		map[newIndex]=ind;
		
		size++;
	}
	
	public void compile() {
		dom=new Tuple [adjacents.size()+futurAdjacents.size()];
		int bigSize = dom.length;
		size=bigSize-futurAdjacents.size();
		int index=0;
		for(Tuple t : adjacents){
			dom[index]=t;
			index++;
		}
		for(Tuple t : futurAdjacents){
			dom[index]=t;
			index++;
		}
		
		map = new int [bigSize];
		for(int i=0; i<map.length; i++){
			map[i]=dom[i].vertex;
		}
		adjacents=null;
		futurAdjacents=null;
	}
	
	public void print(){
		String sdom="";
		String smap="";
		for(int i=0; i<dom.length; i++){
			if(i==size){
				sdom+=("|");
			}
			sdom+=(dom[i]+" ");
			smap+=(map[i]+" ");
		}
		System.out.println(sdom);
		System.out.println(smap);
		System.out.println();
	}
	
	public Tuple[] getDom() {
		return dom;
	}
	
	public int[] getMap() {
		return map;
	}
	
	public int getSize() {
		return size;
	}
}
