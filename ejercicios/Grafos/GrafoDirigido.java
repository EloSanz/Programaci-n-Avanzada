public class GrafoDirigido {
    private int[][] matrizAdyacencia;
    private int numVertices;

    public GrafoDirigido(int numVertices) {
        this.numVertices = numVertices;
        matrizAdyacencia = new int[numVertices][numVertices];
    }

    // Método para agregar una arista dirigida
    public void agregarArista(int origen, int destino, int peso) {
        matrizAdyacencia[origen][destino] = peso;
    }

    // Método para mostrar la matriz de adyacencia
    public void mostrarMatrizAdyacencia() {
        System.out.println("Matriz de Adyacencia:");
        System.out.print("    "); // Espacio inicial para la columna de índices
        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%3d ", i);
        }
        System.out.println();
        
        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%3d ", i); // Etiqueta de fila
            for (int j = 0; j < numVertices; j++) {
                System.out.printf("%3d ", matrizAdyacencia[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GrafoDirigido grafo = new GrafoDirigido(5);

        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(0, 4, 100);
        grafo.agregarArista(1, 2, 50);
        grafo.agregarArista(2, 4, 1);
        grafo.agregarArista(2, 3, 1);

        // Mostrar la matriz de adyacencia
        grafo.mostrarMatrizAdyacencia();
    }
}
