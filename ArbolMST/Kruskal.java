import ArbolMST.Arista;
import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {
    static int v, e; // vertices, arcos
    static int MAX = 100005;
    static int parent[] = new int[MAX];
    static ArrayList<Arista> edges = new ArrayList<>();
    static ArrayList<Arista> answer = new ArrayList<>();

    // UNION-FIND
    static int find(int i) {
        if (parent[i] == i) {
            return i;
        } else {
            parent[i] = find(parent[i]); // Path compression
            return parent[i];
        }
    }

    static void unionFind(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        parent[rootX] = rootY;
    }

    static void kruskal() {
        Arista actual;
        int aux, i, x, y;
        aux = i = 0;
        Collections.sort(edges);

        while (aux < (v - 1) && i < edges.size()) {
            actual = edges.get(i);
            x = find(actual.inicio);
            y = find(actual.fin);

            if (x != y) {
                answer.add(actual);
                aux++;
                unionFind(x, y);
            }else{
                System.out.println("bucle con "+
                actual.inicio + " " 
                + actual.fin 
                + " arista: " + actual.costo);
            }
            i++;
        }
    }

    // Inicializar los padres para cada nodo
    static void initializeParents() {
        for (int i = 0; i < v; i++) {
            parent[i] = i;
        }
    }

    public static void main(String[] args) {
        // Ejemplo de prueba
        v = 4; // Número de vértices
        e = 5; // Número de arcos

        edges.add(new Arista(0, 1, 10));
        edges.add(new Arista(0, 2, 6));
        edges.add(new Arista(0, 3, 5));
        edges.add(new Arista(1, 3, 15));
        edges.add(new Arista(2, 3, 4));

        initializeParents();

        kruskal();

        System.out.println("Arcos en el Árbol de Expansión Mínima:");
        for (Arista arista : answer) {
            System.out.println(arista.inicio + " -- " + arista.fin + " == " + arista.costo);
        }
    }
}
