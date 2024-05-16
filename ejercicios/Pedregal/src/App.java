
import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        int[] casas = {11,10,3,6,8,1,7,12};
        System.out.println(robar(casas));
            /*File f = new File("pedregal.in");
            Scanner s = new Scanner(f);
            String terrainDimensions = s.nextLine();
            String houseDimensions = s.nextLine();
            
            int terrainWidth = Integer.parseInt(terrainDimensions.split(" ")[0]);
            int terrainHeight = Integer.parseInt(terrainDimensions.split(" ")[1]);
    
            int houseWidth = Integer.parseInt(houseDimensions.split(" ")[0]);
            int houseHeight = Integer.parseInt(houseDimensions.split(" ")[1]);
    
            int cantPiedras = Integer.parseInt(s.nextLine());
    
            if (terrainWidth < 1 || terrainHeight < 1 || terrainWidth > 1000 || terrainHeight > 1000 ||
                    houseWidth < 1 || houseHeight < 1 || houseWidth > 100 || houseHeight > 100 ||
                    cantPiedras < 1 || cantPiedras > 1000) {
                System.out.println("Error al ingresar los datos.");
                System.exit(0);
            }
    
            int[][] terrain = new int[terrainWidth][terrainHeight];
            for (int x = 0; x < terrainWidth; x++) {
                for (int y = 0; y < terrainHeight; y++) {
                    terrain[x][y] = 0;
                }
            }
    
            for (int i = 0; i < cantPiedras; i++) {
                String stonePos = s.nextLine();
                int x = Integer.parseInt(stonePos.split(" ")[0]) - 1;
                int y = Integer.parseInt(stonePos.split(" ")[1]) - 1;
                terrain[x][y] = 1;
            }
    
            s.close();

            mostrarTerreno(terrain);
            boolean construirCasa = false;
            int casaX = -1, casaY = -1;
            String orientacion = "";
    
            for (int y = 0; y < terrainHeight && !construirCasa; y++) {//Recorro por filas
                for (int x = 0; x < terrainWidth; x++) {//recorro por columnas
                    boolean flagNS = true, flagEO = true;
                    if (x <= terrainWidth - houseWidth && y <= terrainHeight - houseHeight) {//Mientras entre de ancho
                        for (int y1 = y; y1 < y + houseHeight && flagNS; y1++) {   //mientras entre de alto         
                            for (int x1 = x; x1 < x + houseWidth; x1++) {
                                if (terrain[x1][y1] == 1) {
                                    flagNS = false;
                                }
                            }
                        }
                    } else flagNS = false;
                    if (x <= terrainWidth - houseHeight && y <= terrainHeight - houseWidth) {
                        for (int y1 = y; y1 < y + houseWidth && flagEO; y1++) {
                            for (int x1 = x; x1 < x + houseHeight; x1++) {
                                if (terrain[x1][y1] == 1) {
                                    flagEO = false;
                                }
                            }
                        }
                    } else
                        flagEO = false;
                    if (flagNS || flagEO) {
                        construirCasa = true;
                        casaX = x;
                        casaY = y;
                        orientacion = flagNS ? "N/S" : "E/O";
                        System.out.println("La casa se puede construir en posicion X: " + casaX +
                        " y posicion Y: " + casaY + " con orientacion " + orientacion);
                    }
                }
            }
    
            if (!construirCasa)
                System.out.println("La casa no se puede construir");
            */
    }

    public static void mostrarTerreno(int[][] matriz) {
        System.out.println("Terreno: ");

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("");

    }
    public static int robar(int[] casas) {
        if(casas.length == 1) 
            return casas[0];
        if(casas.length == 2) 
            return Math.max(casas[0], casas[1]);

        int[] valores = new int[casas.length];
        valores[0] = casas[0];
        valores[1] = Math.max(casas[0], casas[1]);

        for(int i = 2; i < casas.length; i++) 
            valores[i] = Math.max(valores[i - 2] + casas[i], valores[i - 1]);
            
        return valores[casas.length -1 ];//devuelvo la máxima acumulada
    }

}
