
public class FloydWarshall {
    private static final int INFINITO = Integer.MAX_VALUE;
    private int numNodos;
    private int[][] distancias;

    public FloydWarshall(Grafo grafo) {
        this.numNodos = grafo.getNumNodos();
        this.distancias = new int[numNodos][numNodos];
        int[][] matrizAdyacencia = grafo.getMatrizAdyacencia();

        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                if (i == j) {
                    distancias[i][j] = 0;
                }else{
                    distancias[i][j] = matrizAdyacencia[i][j];
                }
            }
        }
    }
    public void floydWarshall() {
        for (int k = 0; k < numNodos; k++) {
            for (int i = 0; i < numNodos; i++) {
                for (int j = 0; j < numNodos; j++) {
                    if (distancias[i][k] != INFINITO && distancias[k][j] != INFINITO)
                            distancias[i][j] = Math.min(distancias[i][j] , distancias[i][k] + distancias[k][j]);
                    } 
                }
            }
    }

    public int[][] getDistancias() {
        return distancias;
    }

    public static void main(String[] args) {
        // Crear el grafo
        int numNodos = 5;
        Grafo grafo = new Grafo(numNodos);

        // Agregar arcos al grafo
        grafo.agregarArco(0, 1, 2);
        grafo.agregarArco(0, 2, 1);
        grafo.agregarArco(0, 4, 3);
        grafo.agregarArco(1, 3, 4);
        grafo.agregarArco(2, 1, 1);
        grafo.agregarArco(2, 4, 1);
        grafo.agregarArco(3, 0, 1);
        grafo.agregarArco(3, 2, 3);
        grafo.agregarArco(3, 4, 5);

        FloydWarshall floydWarshall = new FloydWarshall(grafo);
        floydWarshall.floydWarshall();

        int[][] resultado = floydWarshall.getDistancias();

        mostrarMatriz(grafo.getMatrizAdyacencia());
        System.out.println();
        mostrarMatriz(resultado);
    }
    public static void mostrarMatriz(int[][] matriz) {
        int n = matriz.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matriz[i][j] == INFINITO) {
                    System.out.printf("%-4s", "INF");
                } else {
                    System.out.printf("%-4d", matriz[i][j]);
                }
            }
            System.out.println();
        }
    }
    
}

