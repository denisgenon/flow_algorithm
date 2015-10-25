package object;

public class Node extends Tuple{
	//public int index;
	//public int capa;
	public Node next;
	
	public Node(int index, int capa, Node next){
		super(capa, index);
		this.next=next;
	}
	
	@Override
	public String toString() {
		return ("["+super.index + ", " + capa+"]");
	}
	
	// Retourne la valeur du node enlevé
	public static int removeNode(int x, int y, Node [] capaMatrix) {
		Node current = capaMatrix[x];
		int res=-1;
		if(current==null){
			System.out.println("La liste est vide");
			return res;
		}
		if(current.index==y){
			capaMatrix[x]=current.next;
			return current.capa;
		}
		while(current.next!=null) {
			if(current.next.index==y) {
				res=current.next.capa;
				current.next = current.next.next;
				return res;
			}
			else {
				current = current.next;
			}
		}
		
		return res;
	}

	public static void addNode(int x, int y, int capa, Node [] capaMatrix){
		Node current = capaMatrix[x];
		boolean flag = true;
		if(current==null) {
			capaMatrix[x]=new Node(y,capa,null);
		}
		else if(current.index>y){
			capaMatrix[x]=new Node(y,capa,current);
			flag=false;
		}
		else {
			while(current.next!=null && flag) {
				if(current.index>y) {
					Node newTuple = new Node(y,capa,current);
					capaMatrix[x]=newTuple;
					flag = false;
				}
				else if(current.next.index>y) {
					Node newTuple = new Node(y,capa,current.next);
					current.next=newTuple;
					flag = false;
				}
				current = current.next;
			}
			if (flag) {
				Node newTuple = new Node(y,capa,null);
				current.next=newTuple;
			}
		}
	}

	public static Node getNode(int x, int y, Node [] capaMatrix){
		Node current = capaMatrix[x];
		while(current!=null) {
			if(current.index==y) {
				return current;
			}
			current=current.next;
		}
		return null;
	}
}
