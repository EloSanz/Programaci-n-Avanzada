import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    private static int min(int r, int i) {
        return (r<i) ? r : i ;
    }
    public static void main(String[] args) throws Exception {
        solution_On();
    }

    public static void solutionN_N() throws FileNotFoundException{
        File f = new File("almacenamiento.in");
        Scanner s = new Scanner(f);
        String l1 = s.nextLine();
        String l2 = s.nextLine();
        s.close();

        int appsInstaladas = Integer.parseInt(l1.split(" ")[0]);
        int mbNecesarios = Integer.parseInt(l1.split(" ")[1]);
        int[] mbAppsInstaladas = new int[appsInstaladas];

        int i = 0;
        for (String mbApps : l2.split(" ")) {
            mbAppsInstaladas[i] = Integer.parseInt(mbApps);
            i++;
        }

        int finalCount = Integer.MAX_VALUE;
        for (int j = 0; j < mbAppsInstaladas.length; j++) {
            int mbAcum = 0;
            for (int k = j; k < mbAppsInstaladas.length; k++) {
                if (mbAcum >= mbNecesarios) {
                    if (k - j < finalCount) {
                        finalCount = k - j;
                    }
                    break;
                }
                mbAcum += mbAppsInstaladas[k];
            }
            if (mbAcum >= mbNecesarios) {
                if (mbAppsInstaladas.length - j < finalCount) {
                    finalCount = mbAppsInstaladas.length - j;
                }
            }
        }

        System.out.println(finalCount);
    }
    

    public static void solution_On() throws Exception
    /*
     * Técnica: 2 punteros
     * Se que si empiezo despues, necesariamente termina igual o después, no 
     * puede terminar antes.
     * Avance amortizado
     * El i avanza hasta n, y j hasta a n veces, son n cosas para hacer, no puedo hacer más porque nunca retrocedo
     * Independientemente de cada operación que hago, el amortizado total es On
     * El total nos queda n + n = 2n = O(n)
     * J queda entre distintas operaciones del ciclo de afuera
     * J es persistente entre distintas operaciones, no se resetea.
     * Su total no va a multiplicarse.
     */
    {
        //Carga de datos
        File f = new File("almacenamiento.in");
        Scanner s1 = new Scanner(f);
        String l1 = s1.nextLine();
        String l2 = s1.nextLine();
        s1.close();

        int appsInstaladas = Integer.parseInt(l1.split(" ")[0]);
        int mbNecesarios = Integer.parseInt(l1.split(" ")[1]);
        int[] mbAppsInstaladas = new int[appsInstaladas];

        int c = 0;
        for (String mbApps : l2.split(" ")) {
            mbAppsInstaladas[c] = Integer.parseInt(mbApps);
            c++;
        }
        //Fin Carga de datos
        int tam = mbNecesarios;
        int r = appsInstaladas;//supongo que debo eliminar todas
        int n = appsInstaladas;
        int j = 0, memoria = 0;

        for(int i = 0; i < n; i++){
            while (j < n && memoria < tam) { 
                memoria+= mbAppsInstaladas[j];
                j+= 1;
            }
            if(memoria >= tam)
                r = min(r,j-i);             //j es el primero que no borro
            //else Break
            memoria-= mbAppsInstaladas[i];  //i es el primero que agregué
        }
        System.err.println(r);
    }


    public static void solution_O_n_Log_n()
    {
        //sin implementar aun

    }
}
