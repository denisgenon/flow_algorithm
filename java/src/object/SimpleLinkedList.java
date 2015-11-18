package object;

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
		int res = -1;
		if (current == null) {
			System.out.println("La liste est vide");
			return res;
		}
		if (current.getElement().idDesti == y) {
			head = current.getNext();
			size--;
			return current.getElement().getCapacity();
		}
		while(current.getNext() != null) {
			if(current.getNext().getElement().idDesti == y) {
				res = current.getNext().getElement().getCapacity();
				current.setNext(current.getNext().getNext());
				size--;
				return res;
			}
			else {
				current = current.getNext();
			}
		}
		return res;
	}

	public void addNode(int y, int capa){
		if (size == 0) {
			head = new Node(new Edge(capa, y), null);
		}
		else {
			Node current = head;
			head = new Node(new Edge(capa, y), current);
		}
		size++;
	}

	public Node getNode(int y){
		Node current = head;
		while(current != null) {
			if(current.getElement().idDesti == y) {
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
			System.out.println(c.getElement().getIndex());
			c = c.getNext();
		}
	}
}