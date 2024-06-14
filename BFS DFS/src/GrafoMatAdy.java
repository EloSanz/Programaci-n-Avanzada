public class GrafoMatAdy {
    private static final int INFINITO = Integer.MAX_VALUE;
    private int numNodos;
    private int[][] matrizAdyacencia;

    public GrafoMatAdy(int numNodos) {
        this.numNodos = numNodos;
        matrizAdyacencia = new int[numNodos][numNodos];
        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                if (i != j) {
                    matrizAdyacencia[i][j] = INFINITO;
                }//lo demas empieza en 0
            }
        }
    }

    public void addEdge(int origen, int destino, int peso) {
        matrizAdyacencia[origen][destino] = peso;
        //matrizAdyacencia[destino][origen] = peso;//No dirigida
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public int getNumNodos() {
        return numNodos;
    }
    public void printGrafo()
    {
        for(int i = 0; i < numNodos; i++){
            System.out.println("");
            for(int j = 0; j < numNodos; j++)
                System.out.print(matrizAdyacencia[i][j] == INFINITO? "INF ": matrizAdyacencia[i][j] + " ");
        }
    }
}