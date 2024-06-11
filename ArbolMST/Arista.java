package ArbolMST;
public class Arista implements Comparable<Arista> {
	public final int inicio, fin, costo;
	
	public Arista(int i, int f, int c) {
		this.inicio = i;
		this.fin = f;
		this.costo = c;
	}
	 @Override
     public int compareTo(Arista b)
     {
         return this.costo - b.costo;
     }
}
