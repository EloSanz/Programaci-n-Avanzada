package GrafosPaquete;

import java.util.HashMap;

public class Nodo {
	public final int id;
	private int x, y;
	private HashMap<Integer, Integer> conexiones = new HashMap<Integer, Integer>();
	
	public Nodo(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public HashMap<Integer, Integer> getConexiones() {
		return this.conexiones;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
