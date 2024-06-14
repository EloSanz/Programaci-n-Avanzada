import java.beans.Visibility;
import java.util.Stack;

//Utilizo DFS para calcular los costos desde un nodo inicial hasta todos los demás (Un Dijkstra).
public class DFS {

    private static final int INFINITO = Integer.MAX_VALUE;

    public static long[] dfsRecursivo(GrafoListList grafo, int startNode) {
        int v = grafo.V;
        long[] distance = new long[v];
        for (int i = 0; i < v; i++) {
            distance[i] = INFINITO;
        }
        boolean[] visited = new boolean[v];

        dfsUtil(grafo, startNode, 0, distance, visited);

        return distance;
    }

    private static void dfsUtil(GrafoListList grafo,
            int node, long dist, long[] distance, boolean[] visited) {

        visited[node] = true;
        distance[node] = dist;

        for (GrafoListList.Edge edge : grafo.adj[node]) {
            if (!visited[edge.node]) {
                dfsUtil(grafo, edge.node, dist + edge.weight, distance, visited);
            }
        }
    }

    public static long[] dfsIterativo(int[][] adjacencyMatrix, int startNode) {
        int v = adjacencyMatrix.length;
        long[] distance = new long[v];
        for (int i = 0; i < v; i++) {
            distance[i] = INFINITO;
        }
        boolean[] visited = new boolean[v];
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);
        distance[startNode] = 0;

        while (!stack.isEmpty()) {
            int actual = stack.pop();
            if (!visited[actual]) {
                visited[actual] = true;
                for (int next = 0; next < v; next++) {
                    if (adjacencyMatrix[actual][next] != INFINITO && !visited[next]) {
                        distance[next] = Math.min(distance[next], distance[actual] + adjacencyMatrix[actual][next]);
                        stack.push(next);
                    }
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        // dfsConListaDeListas();
        recorridoDfsComun();

    }

    static void recorridoDfsComun() {
        int numNodos = 11;
        GrafoMatAdy grafo = new GrafoMatAdy(numNodos);
        grafo.addEdge(0, 1, 1);
        grafo.addEdge(0, 8, 1);
        grafo.addEdge(1, 2, 1);
        grafo.addEdge(1, 5, 1);
        grafo.addEdge(2, 3, 1);
        grafo.addEdge(4, 5, 1);
        grafo.addEdge(5, 6, 1);
        grafo.addEdge(5, 9, 1);
        grafo.addEdge(6, 7, 1);
        grafo.addEdge(8, 9, 1);
        grafo.addEdge(8, 4, 1);

        grafo.addEdge(9, 10, 1);

        int nodeStart = 0;
        boolean[] visitados = new boolean[numNodos];
        for (int i = 0; i < numNodos; i++)
            visitados[i] = false;
        int[][] matAd = grafo.getMatrizAdyacencia();
        Stack<Integer> stack = new Stack<>();

        stack.push(nodeStart);

        while (!stack.isEmpty()) {
            int actual = stack.pop();
            if (!visitados[actual]) {
                visitados[actual] = true;
                System.err.println(actual + " ");// por ejemplo para hacer algo en el recorrido
                for (int next = 0; next < numNodos; next++) {
                    if (matAd[actual][next] != INFINITO && !visitados[next]) {
                        stack.push(next);
                    }
                }
            }
        }
        /*
         * si visitados tiene valores en false, es porque son un subarbol
         */
        // grafo.printGrafo();
    }

    public static void dfsConListaDeListas() {
        int v = 6;
        GrafoListList grafo = new GrafoListList(v);

        grafo.addEdge(0, 1, 1);
        grafo.addEdge(0, 2, 2);
        grafo.addEdge(1, 3, 3);
        grafo.addEdge(1, 4, 4);
        grafo.addEdge(2, 4, 5);
        grafo.addEdge(3, 4, 6);
        grafo.addEdge(3, 5, 7);

        int startNode = 2;
        long[] distancias = dfsRecursivo(grafo, startNode);

        grafo.printGrafo();

        System.out.println("Distancias desde el nodo " + startNode + ":");
        for (int i = 0; i < v; i++) {
            System.out.println("Nodo " + i + ": " + (distancias[i] == INFINITO ? "INF" : distancias[i]));
        }
    }
}
