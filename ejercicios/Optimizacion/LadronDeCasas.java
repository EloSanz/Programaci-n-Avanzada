public class LadronDeCasas {
    public static void mostrarMensaje(int valor) {
        System.out.println("El valor máximo es: " + valor);
    }

    public static void main(String[] args) {
        int[] ValoresCasas = {8, 3, 5, 7, 1, 3, 10}; // Corrección en la declaración del arreglo
        int cantidadDeCasas = ValoresCasas.length; // Obtener el tamaño del arreglo
        int[] aux = new int[cantidadDeCasas]; // Corrección en la declaración del arreglo auxiliar
        int BotinMaximo = 0;

        aux[0] = ValoresCasas[0];
        aux[1] = Math.max(ValoresCasas[0], ValoresCasas[1]); // Usar Math.max

        for (int i = 2; i < cantidadDeCasas; i++) {
            aux[i] = Math.max(aux[i - 1], ValoresCasas[i] + aux[i - 2]); // Usar Math.max
        }
        
        BotinMaximo = aux[cantidadDeCasas - 1];
        mostrarMensaje(BotinMaximo);
    }
}
