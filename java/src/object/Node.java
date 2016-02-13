package object;

public class Node {
	private Arc element;
	private Node next;

	public Node(Arc element, Node next){
		this.element = element;
		this.next = next;
	}
	public Arc getElement() {
		return element;
	}
	public Node getNext() {
		return next;
	}
	public void setElement(Arc element) {
        this.element = element;
    }
 
    public void setNext(Node next) {
        this.next = next;
    }
}