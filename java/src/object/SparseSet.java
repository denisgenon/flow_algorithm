package object;

public class SparseSet {
	
	public Tuple [] dom;
	public int [] map;
	public int size;
	
	public SparseSet(int size){
		dom = new Tuple [size];
		map = new int [size];
		this.size=size;
	}

}
