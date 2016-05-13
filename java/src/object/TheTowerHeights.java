package object;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class TheTowerHeights {
	//LinkedList 
	public HashMap<Integer, LinkedList<Vertex>> tower;
	public HashSet<Integer> vertices;
	public int maxHeight = 0;
	public int size = 0;
	
	public TheTowerHeights(int size) {
		tower = new HashMap<Integer, LinkedList<Vertex>>(size*2);
		vertices = new HashSet<Integer>(size);
	}
	
	public void add(Vertex v) {
		if (!vertices.contains(v.id)) {
			if (tower.containsKey(v.h)) { // Already an element at this height, we had to the LL
				tower.get(v.h).add(v);
				vertices.add(v.id);
				size++;
			} else {
				LinkedList<Vertex> ll = new LinkedList<Vertex>();
				ll.add(v);
				tower.put(v.h, ll);
				vertices.add(v.id);
				size++;
			}
		}
		if (v.h > maxHeight) maxHeight = v.h;
	}
	
	public Vertex getHighest() {
		return tower.get(maxHeight).peek();
	}
	
	public void move(Vertex v, int new_h) {
		remove(v);
		v.h = new_h;
		add(v);
	}
	
	public void remove(Vertex v) {
		tower.get(v.h).pop();
		vertices.remove(v.id);
		size--;
		if (tower.get(maxHeight).size() == 0) {
			tower.remove(maxHeight);
			if(size != 0) {
				int i = v.h - 1;
				while (tower.get(i) == null) {
					i--;
				}
				maxHeight = i;
			} else {
				maxHeight = 0;
			}
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}
