import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private static final int INFINITO = Integer.MAX_VALUE;

    static int v, e;
    static int MAX = 100000;
    @SuppressWarnings("unchecked")
    static ArrayList<Integer> ady[] = new ArrayList[MAX];
    static long distance[] = new long[MAX];

    public static long[] bfs(GrafoListList grafo, int startNode) {
        int v = grafo.V;
        long[] distance = new long[v];
        for (int i = 0; i < v; i++) {
            distance[i] = INFINITO;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(startNode);
        distance[startNode] = 0;

        while (!q.isEmpty()) {
            int actual = q.poll();
            for (GrafoListList.Edge next : grafo.adj[actual]) {
                if (distance[next.node] == INFINITO) {
                    distance[next.node] = distance[actual] + next.weight;
                    q.add(next.node);
                }
            }
        }
        return distance;
    }
    public static long[] bfs(int[][] adjacencyMatrix, int startNode){
        int v = adjacencyMatrix.length;
        long[] distance = new long[v];
        for (int i = 0; i < v; i++) {
            distance[i] = INFINITO;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(startNode);
        distance[startNode] = 0;

        while (!q.isEmpty()) {
            int actual = q.poll();
            for (int next = 0; next < v; next++) {
                if (adjacencyMatrix[actual][next] != INFINITO && distance[next] == INFINITO) {
                    distance[next] = distance[actual] + adjacencyMatrix[actual][next];
                    q.add(next);
                }
            }
        }
        return distance;
    }
    public static void main(String[] args) {
        //bfsConListaDeListas();
        bfsConMatrizDeAdyacencia();
    }

    public static void bfsConListaDeListas() {
        int v = 6;
        GrafoListList grafo = new GrafoListList(v);
        // Añadiendo aristas
        grafo.addEdge(0, 1, 1);
        grafo.addEdge(0, 2, 2);
        grafo.addEdge(1, 3, 3);
        grafo.addEdge(1, 4, 4);
        grafo.addEdge(2, 4, 5);
        grafo.addEdge(3, 4, 6);
        grafo.addEdge(3, 5, 7);

        // Llamada a BFS desde el nodo 0
        int startNode = 2;
        long[] distancias = bfs(grafo, startNode);

        // Mostrando distancias

        grafo.printGrafo();

        System.out.println("Distancias desde el nodo " + startNode + ":");
        for (int i = 0; i < v; i++) {
            System.out.println("Nodo " + i + ": " + (distancias[i] == INFINITO?"INF": distancias[i]) );
        }
    }

    public static void bfsConMatrizDeAdyacencia() {
        int v = 9;
        GrafoMatAdy grafoMatAdy = new GrafoMatAdy(v);
        grafoMatAdy.addEdge(0, 1, 1);
        grafoMatAdy.addEdge(0, 8, 1);
        grafoMatAdy.addEdge(1, 2, 1);
        grafoMatAdy.addEdge(2, 3, 1);
        grafoMatAdy.addEdge(8, 4, 1);
        grafoMatAdy.addEdge(8, 5, 1);

        int startNode = 0;
        long[] distancias = bfs(grafoMatAdy.getMatrizAdyacencia(), startNode);
        
        System.out.println("Distancias desde el nodo " + startNode + ":");
        for (int i = 0; i < v; i++) {
            System.out.println("Nodo " + i + ": " + (distancias[i] == INFINITO?"#": distancias[i]) );
        }
    }



    
}
