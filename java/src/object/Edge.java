package object;

public class Edge {
	public int capa;
	public int idDesti;
	
	public Edge(int capa, int index){
		this.capa=capa;
		this.idDesti=index;
	}
	
	public int getCapacity() {
		return capa;
	}
	
	public int getIndex() {
		return idDesti;
	}
	
	public void setCapa(int capa) {
		this.capa = capa;
	}
	
	public void setIndex(int index) {
		this.idDesti = index;
	}
	
	@Override
	public String toString() {
		return ("[->"+idDesti+": "+capa+"]");
	}
	
}
