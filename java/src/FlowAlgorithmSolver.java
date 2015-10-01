import java.util.ArrayList;

public class FlowAlgorithmSolver {

	static FlowAlgorithmInstance instance;
	
	// For getPathDFS and visitDFS
	static int [] colors;
	static int [] parents;
	
	public static void printMatrix(int [][] matrix) {
		System.out.println("Il y a "+instance.V+" sommets");
		System.out.println("Il y a "+instance.E+" arêtes");
		
		for(int i=0; i<instance.V; i++) {
			System.out.print("Voisin de "+instance.vertices[i]+" : ");
			for(Vertex v : instance.vertices[i].adjacents) {
				System.out.print(v.id+" ");
			}
			System.out.println();
		}
		
		System.out.println("Matrice des distances :");
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
		
		return mypath.toArray(new Vertex [mypath.size()]);
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
	
	public static void FordFulkerson(int [][] matrix) {
		/**
		 * while (chemin augmentant) {
		 * 		augmenter le flot
		 * }
		 */
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("no instance file");
		} 
		else {
			try {
				instance = FlowAlgorithmParser.parse(args[0]);
				//printMatrix(instance.distMatrix);
				printPath(getPathDFS());

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
