public class Grafo {
    private static final int INFINITO = Integer.MAX_VALUE;
    private int numNodos;
    private int[][] matrizAdyacencia;

    public Grafo(int numNodos) {
        this.numNodos = numNodos;
        matrizAdyacencia = new int[numNodos][numNodos];
        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                if (i == j) {
                    matrizAdyacencia[i][j] = 0;
                } else {
                    matrizAdyacencia[i][j] = INFINITO;
                }
            }
        }
    }

    public void agregarArco(int origen, int destino, int peso) {
        matrizAdyacencia[origen][destino] = peso;
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public int getNumNodos() {
        return numNodos;
    }
}