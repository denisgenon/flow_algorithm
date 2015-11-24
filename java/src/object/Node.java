package object;

public class Node {
	private Edge element;
	private Node next;

	public Node(Edge element, Node next){
		this.element = element;
		this.next = next;
	}
	public Edge getElement() {
		return element;
	}
	public Node getNext() {
		return next;
	}
	public void setElement(Edge element) {
        this.element = element;
    }
 
    public void setNext(Node next) {
        this.next = next;
    }
}