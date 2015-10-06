package object;

public class Tuple {
	public int index;
	public int capa;
	public Tuple next;
	
	public Tuple(int index, int capa, Tuple next) {
		this.index=index;
		this.capa=capa;
		this.next=next;
	}
	
	@Override
	public String toString() {
		return ("["+index + ", " + capa+"]");
	}
	
	public static void removeNode(int x, int y, Tuple [] capaMatrix) {
		Tuple current = capaMatrix[x];
		boolean flag = true;
		if(current==null){
			System.out.println("La liste est vide");
			return;
		}
		if(current.index==y){
			capaMatrix[x]=current.next;
			flag=false;
		}
		while(current.next!=null && flag) {
			if(current.next.index==y) {
				current.next = current.next.next;
				flag = false;
			}
			else {
				current = current.next;
			}
		}
	}

	public static void addNode(int x, int y, int capa, Tuple [] capaMatrix){
		Tuple current = capaMatrix[x];
		boolean flag = true;
		if(current==null) {
			capaMatrix[x]=new Tuple(y,capa,null);
		}
		else if(current.index>y){
			capaMatrix[x]=new Tuple(y,capa,current);
			flag=false;
		}
		else {
			while(current.next!=null && flag) {
				if(current.index>y) {
					Tuple newTuple = new Tuple(y,capa,current);
					capaMatrix[x]=newTuple;
					flag = false;
				}
				else if(current.next.index>y) {
					Tuple newTuple = new Tuple(y,capa,current.next);
					current.next=newTuple;
					flag = false;
				}
				current = current.next;
			}
			if (flag) {
				Tuple newTuple = new Tuple(y,capa,null);
				current.next=newTuple;
			}
		}
	}

	public static Tuple getNode(int x, int y, Tuple [] capaMatrix){
		Tuple current = capaMatrix[x];
		while(current!=null) {
			if(current.index==y) {
				return current;
			}
			current=current.next;
		}
		return null;
	}
}
