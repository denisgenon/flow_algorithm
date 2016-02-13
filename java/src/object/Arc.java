package object;

public class Arc {
	public int capacity;
	public int idDestination;
	
	public Arc(int capa, int id){
		this.capacity=capa;
		this.idDestination=id;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getId() {
		return idDestination;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public void setId(int id) {
		this.idDestination = id;
	}
	
	@Override
	public String toString() {
		return ("[->"+idDestination+": "+capacity+"]");
	}
	
}
