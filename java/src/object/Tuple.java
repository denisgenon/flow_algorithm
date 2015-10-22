package object;

public class Tuple {
	public int capa;
	public int vertex;
	
	public Tuple(int capa, int vertex){
		this.capa=capa;
		this.vertex=vertex;
	}
	
	public int getCapa() {
		return capa;
	}
	
	public int getVertex() {
		return vertex;
	}
	
	public void setCapa(int capa) {
		this.capa = capa;
	}
	
	public void setVertex(int vertex) {
		this.vertex = vertex;
	}
}
