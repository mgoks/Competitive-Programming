/* IN PROGRESS */

package coast;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Halil Murat
 * Kattis - Get Shorty
 */

/**
 * Represents a corridor in the maze
 * @author Halil Murat
 */
class Edge
{
	int aV;			// neighbouring vertex
	float aFactor; 	// weapon factor on this edge
	
	Edge(int pV, float pFactor)
	{
		aV = pV;
		aFactor = pFactor;
	}
	
	@Override
	public String toString()
	{
		return "--" + aFactor + "--> " + aV;
	}
}

/**
 * Represents the maze
 * @author Halil Murat
 */
class Graph
{
	static final int MAXV 	   = 10000;	// maximum number of vertices
	static final int MAXDEGREE = 9999;	// maximum vertex outdegree - TODO: necessary?
	
	Edge[][] aEdges;					// adjacency info
	int[] aDegree;						// outdegree of each vertex - TODO: necessary?
	int aV;								// number of vertices in graph
	int aE;								// number of edges in graph
	
	/**
	 * Creates an empty graph and initializes it
	 * @param pEdges	adjacency info
	 * @param pDegree	utdegree of each vertex - TODO: necessary?
	 * @param pV		number of vertices in graph
	 * @param pE		number of edges in graph
	 */
	Graph(Edge[][] pEdges, int[] pDegree, int pV, int pE)
	{
		aEdges = pEdges;
		aDegree = pDegree;
		Arrays.fill(aDegree, 0); 	// TODO: necessary?
		aV = pV;
		aE = pE;
	}
	
	/**
	 * TODO: update factor
	 * @param x
	 * @param y
	 * @param directed	a Boolean flag directed to identify whether we need to insert two copies of each edge or only one
	 */
	void insertEdge( int x, int y, float f, boolean directed )
	{
		if (this.aDegree[x] > MAXDEGREE)
			System.out.println("Warning: insertion("+x+","+y+" exceeds max degree");
		
		this.aEdges[x][this.aDegree[x]] = new Edge(y, f);
		this.aDegree[x]++;
		
		if (directed == false)
			this.insertEdge(y, x, f, true);
		else
			this.aE++;
	}
	
	void print()
	{
		int i, j;
		for (i = 0; i < this.aV; i++){
			System.out.println(i + ":");
			for( j = 0; j < this.aDegree[i]; j++ )
				System.out.println(" " + this.aEdges[i][j]);
			System.out.println();
		}
	}
}


public class GetShorty_260572202 
{
	/**
	 * Creates a graph and fills it with the values it read from the input
	 * @param directed	a boolean value indicating whether the graph is directed or not
	 * @return			a complete graph as described in the input
	 */
	public static Graph readGraphFromInput(boolean directed)
	{
		int n; 		// number of vertices
		int m;		// number of edges
		int x, y;	// vertices in edge (x,y)
		float f;	// 
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		m = s.nextInt();
		Edge[][] edges = new Edge[n][n-1];	// adjacency info
		int[] degree  = new int[n];
		Graph g = new Graph(edges, degree, n, m);
		for( int i = 1; i <= m; i++ ){
			x = s.nextInt();
			y = s.nextInt();
			f = s.nextFloat();
			g.insertEdge(x, y, f, directed);
		}
		s.close();
		return g;
	}
	
	public static void main(String[] args) 
	{
		Graph g = readGraphFromInput(false);
		g.print();
	}
}
