import java.util.Arrays;
import java.util.List;

public class Main {

	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		int[][] tarjanTestGrafo1 = { {INF, 1, INF, INF, INF, INF, INF}, 
									{INF, INF, 1, INF, 1, INF, 1}, 
									{INF, INF, INF, 1, INF, INF, INF}, 
									{INF, INF, 1, INF, 1, 1, INF}, 
									{INF, INF, INF, INF, INF, 1, INF}, 
									{INF, INF, INF, INF, 1, INF, INF}, 
									{1, INF, 1, INF, INF, INF, INF} };
		
		printMatrix("Grafo Test Tarjan 1", tarjanTestGrafo1);
		Tarjan tarjanTest1 = new Tarjan(tarjanTestGrafo1);
		List<List<Integer>> tarjanResult1 = tarjanTest1.findSCCs();
		
		System.out.println("Componentes Fuertemente Conexas: ");
		for(List<Integer> scc : tarjanResult1) {
			System.out.println(scc);
		}
		
		int[][] tarjanTestGrafo2 = { {INF, 1, INF, INF, INF, 1, INF, INF, INF}, 
									 {1, INF, INF, INF, INF, INF, 1, INF, INF}, 
									 {INF, 1, INF, INF, INF, INF, INF, 1, 1}, 
									 {INF, INF, 1, INF, INF, INF, INF, INF, INF}, 
									 {INF, INF, INF, 1, 1, INF, INF, INF, 1}, 
									 {1, INF, INF, INF, INF, INF, 1, INF, INF}, 
									 {INF, INF, INF, INF, INF, INF, INF, 1, INF}, 
									 {INF, INF, INF, INF, INF, INF, 1, INF, INF}, 
									 {INF, INF, INF, 1, INF, INF, INF, 1, INF} }; 
		
		printMatrix("\nGrafo Test Tarjan 2", tarjanTestGrafo2);
		Tarjan tarjanTest2 = new Tarjan(tarjanTestGrafo2);
		List<List<Integer>> tarjanResult2 = tarjanTest2.findSCCs();
		
		System.out.println("Componentes Fuertemente Conexas: ");
		for(List<Integer> scc : tarjanResult2) {
		System.out.println(scc);
		}
	}
	
	private static void printMatrix(String s, int[][] mat) {
		String elem;
		int max = getMaxStringLengthMatrix(mat);
		System.out.println(s + "\n");
		for(int i = 0; i < mat.length; i++) {
			System.out.print(i + ") ");
			for(int j = 0; j < mat[0].length; j++) {
				String n = mat[i][j] == INF ? "X" : String.valueOf(mat[i][j]);
				elem = String.format("| %" + max + "s ", n);
				System.out.print(elem);
			}
			System.out.println("|");
		}
		System.out.println("");
	}
	private static int getMaxStringLengthMatrix(int[][] mat) {
		int max = 0;
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[0].length; j++) {
				if(Integer.toString(mat[i][j]).length() > max && mat[i][j] != INF)
					max = Integer.toString(mat[i][j]).length();
			}
		}
		return max;
	}
}
