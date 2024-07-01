import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Tarjan {
	private final int notVisited = -1;
	private int v, currentVisitId;
	private int[][] graph;
	int[] lowLinkValues, visitIds;
	boolean[] inStack;
	Stack<Integer> stack;
	List<List<Integer>> sccsList;
	
	public Tarjan(int[][] graph) {
		this.graph = graph;
		this.v = graph.length;
		this.currentVisitId = 0;
		this.lowLinkValues = new int[v]; 
		this.visitIds = new int[v];
		this.inStack = new boolean[v];
		this.stack = new Stack<>();
		this.sccsList = new ArrayList<>();
		
		Arrays.fill(this.visitIds, this.notVisited);
	    Arrays.fill(this.lowLinkValues, 0); 
	    Arrays.fill(this.inStack, false);
	}
	
	public List<List<Integer>> findSCCs() {
	    for(int i = 0; i < this.v; i++) {
	    	if(this.visitIds[i] == this.notVisited) {
	    		tarjanDFS(i);
 	    	}
	    }
		return this.sccsList;
	}
	
	private void tarjanDFS(int currentNode) {
		this.stack.push(currentNode);
		this.inStack[currentNode] = true;
		this.visitIds[currentNode] = this.currentVisitId;
		this.lowLinkValues[currentNode] = this.currentVisitId;
		this.currentVisitId++;
		
		for(int nextNode = 0; nextNode < this.v; nextNode++) {
			if(this.graph[currentNode][nextNode] != Integer.MAX_VALUE) {
				if(this.visitIds[nextNode] == this.notVisited) {
					tarjanDFS(nextNode);
				}	
				if(this.inStack[nextNode]) 
					this.lowLinkValues[currentNode] = Math.min(this.lowLinkValues[currentNode], this.lowLinkValues[nextNode]);
			}
		}
		
		//Si currentNode es el comienzo de un SCC
		if(this.visitIds[currentNode] == this.lowLinkValues[currentNode]) {
			List<Integer> currentSCC = new ArrayList<>();
			int node;
			do {
				node = this.stack.pop();
				this.inStack[node] = false;
				this.lowLinkValues[node] = this.visitIds[currentNode];
				currentSCC.add(node);
			} while(node != currentNode);
			this.sccsList.add(currentSCC);
		}
	}
}
