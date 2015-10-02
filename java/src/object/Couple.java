package object;

public class Couple {

	public int a;
	public int b;
	
	public Couple(int a, int b) {
		this.a=a;
		this.b=b;
	}
	@Override
	public boolean equals(Object o) {
		Couple c = (Couple)o;
		if (this.a==c.a && this.b==c.b) return true;
		return false;
	}
}
