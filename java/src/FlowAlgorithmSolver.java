import java.util.ArrayList;

public class FlowAlgorithmSolver {

	static FlowAlgorithmInstance instance;
	
	// For getPathDFS and visitDFS
	static int [] colors;
	static int [] parents;
	
	public static void printMatrix(int [][] matrix) {
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void printPath(Vertex [] mypath) {
		for(Vertex v : mypath){
			System.out.print(v + " ");
		}
		System.out.println();
	}
	
	public static Vertex [] getPathDFS() {
		colors = new int [instance.V]; // Blanc=0, Gris=1, Noir=2
		parents = new int [instance.V];
		
		for(int i=0; i<instance.V; i++) {
			colors[i]=0;
			parents[i]=-1;
		}
		
		visitDFS(0);
		
		ArrayList<Vertex> mypath = new ArrayList<Vertex>();
		int indexpath = instance.V-1;
		mypath.add(instance.vertices[indexpath]);
		while(parents[indexpath]!=-1 && indexpath!=0){
			mypath.add(instance.vertices[parents[indexpath]]);
			indexpath=parents[indexpath];
		}
		if(indexpath==0) return mypath.toArray(new Vertex [mypath.size()]);
		return null;
	}
	
	public static void visitDFS(int index) {
		colors[index]=1;
		for(int i=0; i<instance.vertices[index].adjacents.size(); i++) {
			if(colors[instance.vertices[index].adjacents.get(i).id]==0) {
				parents[instance.vertices[index].adjacents.get(i).id]=index;
				visitDFS(instance.vertices[index].adjacents.get(i).id);
			}
		}
		colors[index]=2;	
	}
	
	public static int getMinFlow(Vertex [] path) {
		int minFlow=Integer.MAX_VALUE;
		for(int i=0; i<path.length-1; i++) {
			minFlow=Math.min(minFlow,instance.distMatrix[path[i+1].id][path[i].id]);
		}
		return minFlow;
	}
	
	public static void applyPath(int capa,Vertex [] path) {
		for(int i=0; i<path.length-1; i++) {
			instance.distMatrix[path[i+1].id][path[i].id]-=capa;
			instance.bestflot[path[i+1].id][path[i].id]+=capa;
			if(instance.distMatrix[path[i+1].id][path[i].id]==0) {
				path[i+1].adjacents.remove(path[i]);
			}
		}
	}
	
	public static void FordFulkerson() {
		Vertex [] myPath = getPathDFS();
		while(myPath!=null) {
			applyPath(getMinFlow(myPath),myPath);
			myPath = getPathDFS();
		}
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("no instance file");
		} 
		else {
			try {
				instance = FlowAlgorithmParser.parse(args[0]);
				//printMatrix(instance.distMatrix);
				FordFulkerson();
				printMatrix(instance.bestflot);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
