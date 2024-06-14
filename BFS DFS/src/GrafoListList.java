import java.util.ArrayList;

class GrafoListList {
    int V;
    ArrayList<Edge>[] adj;

    @SuppressWarnings("unchecked")
    public GrafoListList(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Edge>();
        }
    }

    void addEdge(int origen, int destino, int peso) {
        adj[origen].add(new Edge(destino, peso));
    }
 
    void printGrafo() {
        System.out.println("Nodo x: -> (destino, peso)");
        for (int i = 0; i < V; i++) {
            System.out.print("Nodo " + i + ":");
            for (Edge edge : adj[i]) {
                System.out.print(" -> (" + edge.node + ", $" + edge.weight + ")");
            }
            System.out.println();
        }
    }
    
    class Edge {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}