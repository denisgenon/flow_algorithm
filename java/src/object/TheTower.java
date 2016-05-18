package object;

import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;

public class TheTower {
	public LinkedList<Vertex>[] tower;
	public BitSet isActive;
	public int[] next;
	public int index; // pointer of top element

	@SuppressWarnings("unchecked")
	public TheTower(int size) {
		next = new int[2 * size];
		Arrays.fill(next, -1);

		isActive = new BitSet();
		tower = new LinkedList[2 * size];
		for(int i = 0; i < 2 * size; i++) {
			tower[i] = new LinkedList<>();
		}
		index = 0;
	}

	public Vertex getTop() {
		return tower[index].getFirst();
	}

	public void add(Vertex u, Vertex v) { // On push de u -> v: Il faut update la structure
		if(isActive.get(v.id)) return;
		if(tower[v.h].isEmpty()) {
			next[v.h] = next[u.h];
		}
		if (index < v.h || isEmpty()) index = v.h;
		tower[v.h].add(v);
		isActive.set(v.id);
		next[u.h] = v.h;
		
	}

	public void updateTop(int new_h) {
		Vertex u = tower[index].removeFirst();
		if(tower[u.h].isEmpty()) {
			next[new_h] = next[u.h];
		} else {
			next[new_h] = u.h;
		}
		u.h = new_h;
		tower[new_h].add(u);
		index = new_h;
	}

	public void removeTop() {
		Vertex u = tower[index].removeFirst();
		if(tower[index].isEmpty()) {
			index = next[u.h];
		}
		isActive.clear(u.id);
	}

	public boolean isEmpty() {
		return isActive.cardinality() == 0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < tower.length; i++) {
			if (tower[i].size() > 0) {
				sb.append(next[i] == -1 ? "-" : next[i]);
				sb.append(" ");
				sb.append(i);
				sb.append(": ");
				sb.append(tower[i]);
				if(index == i) {
					sb.append(" <--");
				}
				sb.append("\n");
			}
		}

		sb.append(isActive);
		sb.append("\n");
		return sb.toString();
	}
}
