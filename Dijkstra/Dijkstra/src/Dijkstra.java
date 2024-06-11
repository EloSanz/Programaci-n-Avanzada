
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.List;

public class Dijkstra {
    static int v, e; // cantidad de nodos y aristas
    static final int MAX = 100000; // Cantidad máxima de Nodos
    static List<Node>[] ady = new ArrayList[MAX]; // Lista de adyacencia del grafo
    static boolean[] marked = new boolean[MAX]; // Para marcar nodos visitados
    static long[] distance = new long[MAX]; // Para llevar las distancias a cada nodo
    static int[] prev = new int[MAX]; // Para almacenar las rutas

    static class Node implements Comparable<Node> {
        int adjacent;
        long cost;

        public Node(int adjacent, long cost) {
            this.adjacent = adjacent;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    // nodo inicial s
    static void dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0)); // Se inserta a la cola el nodo inicial.
        for (int i = 0; i < v; i++) {
            distance[i] = Long.MAX_VALUE; // Inicializamos las distancias a infinito
        }
        distance[s] = 0;

        while (!pq.isEmpty()) {
            int actual = pq.peek().adjacent; // Vemos el adyacente del primero, no eliminamos
            pq.poll(); // Ahora sí lo sacamos de la cola
            if (!marked[actual]) { // Si no visité el nodo
                marked[actual] = true;
                for (Node neighbor : ady[actual]) { // Para todos los adyacentes
                    int adjacent = neighbor.adjacent;
                    long weight = neighbor.cost;
                    if (!marked[adjacent]) {
                        if (distance[adjacent] > distance[actual] + weight) { // Si es mas barato llegar a v pasando por w
                            distance[adjacent] = distance[actual] + weight; // Mejoro el costo pasando por w
                            prev[adjacent] = actual; // Actualizo las rutas
                            pq.add(new Node(adjacent, distance[adjacent]));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < MAX; i++) {
            ady[i] = new ArrayList<>();
        }

        v = 5; // Número de vértices
        e = 6; // Número de aristas

        ady[0].add(new Node(1, 10));
        ady[0].add(new Node(3, 30));
        ady[0].add(new Node(4, 100));
        ady[1].add(new Node(2, 50));
        ady[2].add(new Node(4, 10));
        ady[3].add(new Node(2, 20));
        ady[3].add(new Node(4, 60));

        dijkstra(0);

        System.out.println("Distancias desde 0");
        for (int i = 0; i < v; i++) {
            System.out.print(distance[i] + " ");
        }
    }
}
