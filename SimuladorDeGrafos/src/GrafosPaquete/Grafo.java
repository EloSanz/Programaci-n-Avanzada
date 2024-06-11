package GrafosPaquete;

import java.util.ArrayList;

public class Grafo {
    private int cantNodos;
	private ArrayList<Nodo> nodos;

	public Grafo() {
        this.cantNodos = 0;
        this.nodos = new ArrayList<Nodo>();
    }

	public int getCantNodos() {
		return this.cantNodos;
	}
	
	public void setCantNodos(int n) {
		this.cantNodos = n;
	}
	
	public void agregarNodo(int x, int y) {
		this.nodos.add(new Nodo(this.cantNodos, x, y));
		this.cantNodos++;
	}
	
	public ArrayList<Nodo> getNodos() {
		return this.nodos;
	}
	
	public void agregarArista(int src, int dest, int cost) {
        this.nodos.get(src).getConexiones().put(dest, cost);
    }

    public int[] dijkstra(int src, int dest) {
		//Falta Implementar
    	int[] costos = new int[this.cantNodos];
    	return costos;
    }
}