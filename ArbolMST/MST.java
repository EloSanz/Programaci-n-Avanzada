package ArbolMST;
public class MST {
	private int costo;
	private int[][] grafo;
	
	public MST(int costo, int[][] grafo) {
		this.costo = costo;
		this.grafo = grafo;
	}
	
	public int getCosto() {
		return this.costo;
	}
	
	public int[][] getGrafo() {
		return this.grafo;
	}
}
