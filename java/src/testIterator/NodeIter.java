package testIterator;

public class NodeIter {
	private int id;
	private NodeIter next;

	public NodeIter(int id, NodeIter next){
		this.id = id;
		this.next = next;
	}
	public int getId() {
		return id;
	}
	public NodeIter getNext() {
		return next;
	}
	public void setId(int id) {
        this.id = id;
    }
 
    public void setNext(NodeIter next) {
        this.next = next;
    }
}