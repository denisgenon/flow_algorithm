package object;

public class Node extends Edge{
	public Node next;
	
	public Node(int index, int capa, Node next){
		super(capa, index);
		this.next=next;
	}
	
	@Override
	public String toString() {
		return ("["+super.idDesti + ", " + capa+"]");
	}
	
	// Retourne la valeur du node enlevé
	public static int removeNode(int x, int y, Node [] capaMatrix) {
		Node current = capaMatrix[x];
		int res=-1;
		if(current==null){
			System.out.println("La liste est vide");
			return res;
		}
		if(current.idDesti==y){
			capaMatrix[x]=current.next;
			return current.capa;
		}
		while(current.next!=null) {
			if(current.next.idDesti==y) {
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
		if(current==null) {
			capaMatrix[x]=new Node(y,capa,null);
		}
		else {
			capaMatrix[x]=new Node(y,capa,current);
		}
	}

	public static Node getNode(int x, int y, Node [] capaMatrix){
		Node current = capaMatrix[x];
		while(current!=null) {
			if(current.idDesti==y) {
				return current;
			}
			current=current.next;
		}
		return null;
	}
}
