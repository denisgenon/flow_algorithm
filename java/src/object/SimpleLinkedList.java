package object;

import java.util.Iterator;

public class SimpleLinkedList {
	protected Node head;
	protected int size;

	public SimpleLinkedList() {
		head = null;
		size = 0;
	} 

	// Retourne la valeur du node enlevé
	public int removeNode(int y) {
		Node current = head;
		int result = -1;
		if (current == null) {
			System.out.println("La liste est vide");
			return result;
		}
		if (current.getElement().idDestination == y) {
			head = current.getNext();
			size--;
			return current.getElement().getCapacity();
		}
		while(current.getNext() != null) {
			if(current.getNext().getElement().idDestination == y) {
				result = current.getNext().getElement().getCapacity();
				current.setNext(current.getNext().getNext());
				size--;
				return result;
			}
			else {
				current = current.getNext();
			}
		}
		return result;
	}

	public void addNode(int y, int capacity){
		if (size == 0) {
			head = new Node(new Arc(capacity, y), null);
		}
		else {
			Node current = head;
			head = new Node(new Arc(capacity, y), current);
		}
		size++;
	}

	public Node getNode(int y){
		Node current = head;
		while(current != null) {
			if(current.getElement().idDestination == y) {
				return current;
			}
			current=current.getNext();
		}
		return null;
	}

	public Node getFirst() {
		return head;
	}

	public int getSize() {
		return size;
	}
	
	public void printList() {
		Node c = head;
		while (c != null) {
			System.out.println(c.getElement().getId());
			c = c.getNext();
		}
	}
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			Node current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Integer next() {
				int id = current.getElement().idDestination;
				current = current.getNext();
				return id;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			};
		};
	}
}