public class GrafoNoDirigido {
    private int[][] matrizAdyacencia;
    private int numVertices;

    public GrafoNoDirigido(int numVertices) {
        this.numVertices = numVertices;
        matrizAdyacencia = new int[numVertices][numVertices];
    }

    public void agregarArista(int origen, int destino, int peso) {
        matrizAdyacencia[origen][destino] = peso;
        matrizAdyacencia[destino][origen] = peso; 
    }
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
        GrafoNoDirigido grafo = new GrafoNoDirigido(5);

        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(0, 4, 100);
        grafo.agregarArista(1, 2, 50);
        grafo.agregarArista(2, 4, 1);
        grafo.agregarArista(2, 3, 1);

        // Mostrar la matriz de adyacencia
        grafo.mostrarMatrizAdyacencia();
    }

}