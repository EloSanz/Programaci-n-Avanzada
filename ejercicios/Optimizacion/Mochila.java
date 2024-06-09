import java.util.HashMap;
import java.util.Map;

public class Mochila {
    
    static class Item {
        int valor;
        int peso;
        
        Item(int valor, int peso) {
            this.valor = valor;
            this.peso = peso;
        }
    }
    static Item[] items = {
        new Item(200, 2),
        new Item(350, 2), 
        new Item(100, 1), 
        new Item(300, 2), 
        new Item(550, 4)
    };
    static Map<String, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        int pesoMaximo = 4;
        int valorMaximo = mochila(0, pesoMaximo);
        System.out.println("El valor máximo que se puede robar es: $" + valorMaximo + "K");
    }

    public static int mochila(int i, int pesoRestante) {
        if (i == items.length) 
            return 0;//caso base
        String clave = i + "-" + pesoRestante;
        
        if (memo.containsKey(clave)) 
            return memo.get(clave);

        int sinActual = mochila(i + 1, pesoRestante);

        int conActual = 0;
        
        if (items[i].peso <= pesoRestante) 
            conActual = items[i].valor + mochila(i + 1, pesoRestante - items[i].peso);

        int resultado = Math.max(sinActual, conActual);

        memo.put(clave, resultado);
        return resultado;
    }
}
