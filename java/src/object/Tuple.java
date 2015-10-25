package object;

public class Tuple {
	public int capa;
	public int index;
	
	public Tuple(int capa, int index){
		this.capa=capa;
		this.index=index;
	}
	
	public int getCapa() {
		return capa;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setCapa(int capa) {
		this.capa = capa;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public String toString() {
		return ("[->"+index+": "+capa+"]");
	}
	
}
