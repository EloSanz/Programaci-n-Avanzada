package ArbolMST;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		int maxCost = Integer.MAX_VALUE;
		int[][] grafo = {{maxCost, 2, maxCost, 4, maxCost, 5, maxCost},
					   	 {2, maxCost, 7, 1, 3, 8, 4},
					   	 {maxCost, 7, maxCost, maxCost, 10, maxCost, 6},
					   	 {4, 1, maxCost, maxCost, 2, maxCost, maxCost},
					   	 {maxCost, 3, 10, 2, maxCost, maxCost, maxCost},
					   	 {5, 8, maxCost, maxCost, maxCost, maxCost, 1},
					   	 {maxCost, 4, 6, maxCost, maxCost, 1, maxCost}};
		printGrafo(grafo);
		MST arbolito = primAlgorithm(grafo);
		System.out.println("Costo del MST: " + arbolito.getCosto());
		System.out.println("Grafo del MST:");
		printGrafo(arbolito.getGrafo());
	}
	
	public static MST primAlgorithm(int[][] grafo) {
		HashSet<Integer> nodosNoAgregados = new HashSet<Integer>();
		PriorityQueue<Arista> cola = new PriorityQueue<Arista>();
		Arista arista;
		int[][] grafoArbol = new int[grafo.length][grafo[0].length];
		int sumaCosto = 0;
		for(int i = 0; i < grafo.length; i++) {
			nodosNoAgregados.add(i);
			for(int j = 0; j < grafo[0].length; j++) {
				grafoArbol[i][j] = Integer.MAX_VALUE;
			}
		}
		nodosNoAgregados.remove(0);
		for(int c = 0; c < grafo[0].length; c++) {
			if(grafo[0][c] != Integer.MAX_VALUE) {
				cola.add(new Arista(0, c, grafo[0][c]));				
			}
		}
		for(int j = 1; j < grafo.length; j++) {
			do {
				arista = cola.poll();				
			} while(!nodosNoAgregados.contains(arista.fin));
			grafoArbol[arista.inicio][arista.fin] = arista.costo;
			nodosNoAgregados.remove(arista.fin);
			sumaCosto += arista.costo;
			for(int c = 0; c < grafo[arista.fin].length; c++) {
				if(grafo[arista.fin][c] != Integer.MAX_VALUE) {
					cola.add(new Arista(arista.fin, c, grafo[arista.fin][c]));				
				}
			}
		}
		return new MST(sumaCosto, grafoArbol);
	}
	
	public static void printGrafo(int[][] grafo) {
		for(int i = 0; i < grafo.length; i++) {
			System.out.print("| ");
			for(int j = 0; j < grafo[0].length; j++) {
				System.out.print((grafo[i][j] == Integer.MAX_VALUE ? "#" : grafo[i][j]) + " | ");
			}
			System.out.println("");
		}
	}


	
}
