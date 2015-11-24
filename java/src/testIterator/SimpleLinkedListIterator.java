package testIterator;

import java.util.Iterator;


public class SimpleLinkedListIterator {
	protected NodeIter head;
	protected int size;

	public SimpleLinkedListIterator() {
		head = null;
		size = 0;
	} 

	public void addNode(int y){
		if (size == 0) {
			head = new NodeIter(y, null);
		}
		else {
			NodeIter current = head;
			head = new NodeIter(y, current);
		}
		size++;
	}

	public NodeIter getNode(int y){
		NodeIter current = head;
		while(current != null) {
			if(current.getId() == y) {
				return current;
			}
			current=current.getNext();
		}
		return null;
	}

	public NodeIter getFirst() {
		return head;
	}

	public int getSize() {
		return size;
	}
	
	public void printList() {
		NodeIter c = head;
		while (c != null) {
			System.out.println(c.getId());
			c = c.getNext();
		}
	}
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			NodeIter curr = head;

			@Override
			public boolean hasNext() {
				return curr != null;
			}

			@Override
			public Integer next() {
				int id = curr.getId();
				curr = curr.getNext();
				return id;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			};
		};
	}
}