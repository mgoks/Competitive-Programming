//package coast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * https://open.kattis.com/problems/coast
 * @author Halil Murat
 *
 */
class Vertex
{
	boolean aVisited = false;
	boolean isLand;
	int aX;
	int aY;
	
	/**
	 * Constructor
	 * @param pKey
	 * @param pLand
	 * @param pX
	 * @param pY
	 */
	public Vertex(boolean pIsLand, int pX, int pY)
	{
		isLand = pIsLand;
		aX = pX;
		aY = pY;
	}
	
	void setVisited( boolean pVisited )
	{
		aVisited = pVisited;
	}
	
	boolean getVisited()
	{
		return aVisited;
	}

	@Override
	public String toString()
	{
		return "("+ aX +","+ aY +")";
	}
}

public class CoastLength
{
	/**
	 * 
	 * @param map
	 * @return		2D-array containing the vertices
	 */
	static Vertex[][] getVertices( int[][] map )
	{
		Vertex[][] vertices = new Vertex[map.length][map[0].length];
		for( int i = 0; i < map.length; i++ )
		{
			for( int j = 0; j < map[i].length; j++ )
			{
				if( map[i][j] == 0)
					vertices[i][j] = new Vertex(false, i, j);
				else if( map[i][j] == 1)
					vertices[i][j] = new Vertex(true, i, j);
			}
		}
		return vertices;
	}
	
	/**
	 * 
	 * @param v		2D-array of vertices
	 * @return		an array of linked lists of vertices representing the edges
	 */
	static LinkedList<Vertex>[][] getEdges( Vertex[][] v )
	{
		int n = v.length;
		int m = v[0].length;
		LinkedList<Vertex>[][] edges = new LinkedList[n][m];
		for( int i = 0; i < n; i++ )
		{
			for( int j = 0; j < m; j++ )
			{
				LinkedList<Vertex> adjVertices = new LinkedList<>();
				if( i == 0 && j == 0 )				// top-left corner
				{
					adjVertices.add(v[i+1][j]);
					adjVertices.add(v[i][j+1]);
					edges[i][j] = adjVertices;
				}
				else if( i == 0 && j == m-1)		// top-right corner
				{
					adjVertices.add( v[i+1][j] );
					adjVertices.add( v[i][j-1] );
					edges[i][j] = adjVertices;
				}
				else if( i == n-1 && j == 0)		// bottom-left corner
				{
					adjVertices.add( v[i][j+1] );
					adjVertices.add( v[i-1][j] );
					edges[i][j] = adjVertices;
				}
				else if( i == n-1 && j == m-1)		// bottom-right corner
				{
					adjVertices.add(v[i-1][j]);
					adjVertices.add(v[i][j-1]);
					edges[i][j] = adjVertices;	
				}
				else if( i == 0 )					// top row
				{
					adjVertices.add( v[i][j+1] );
					adjVertices.add( v[i+1][j] );
					adjVertices.add( v[i][j-1] );
					edges[i][j] = adjVertices;
				}
				
				else if( j == 0 )					// left-most column
				{
					adjVertices.add( v[i+1][j] );
					adjVertices.add( v[i][j+1] );
					adjVertices.add( v[i-1][j] );
					edges[i][j] = adjVertices;
				}
				else if( j == m-1 )					// right-most column
				{
					adjVertices.add( v[i+1][j] );
					adjVertices.add( v[i-1][j] );
					adjVertices.add( v[i][j-1] );
					edges[i][j] = adjVertices;
				}
				else if( i == n-1 )					// bottom row
				{
					adjVertices.add( v[i][j+1] );
					adjVertices.add( v[i-1][j] );
					adjVertices.add( v[i][j-1] );
					edges[i][j] = adjVertices;
				}
				else								// inside vertices
				{
					adjVertices.add( v[i+1][j] );
					adjVertices.add( v[i][j+1] );
					adjVertices.add( v[i-1][j] );
					adjVertices.add( v[i][j-1] );
					edges[i][j] = adjVertices;
				}
			}
		}
		return edges;
	}
	
	/**
	 * Uses BFS to find the coast length
	 * @param vertices
	 * @param edges
	 * @return
	 */
	static int getCoastLength( Vertex[][] vertices, LinkedList<Vertex>[][] edges)
	{
		int coastLength = 0;
		LinkedList<Vertex> q = new LinkedList<>();	// initialize a queue
		Vertex v = vertices[0][0];	// Flood the map from top left corner using BFS
		v.setVisited(true);			// mark v as visited
		q.add(v);					// enqueue v
		while( !q.isEmpty() )
		{
			Vertex w = q.removeFirst();		// take the front element of q and assign it to w
			int x = w.aX;
			int y = w.aY;
			LinkedList<Vertex> adjVertices = edges[x][y];
			for( Vertex vertex : adjVertices )
			{
				if( !vertex.getVisited() && !vertex.isLand)	// if vertex wasn't visited before and is not a land
				{
					vertex.setVisited(true);
					q.add(vertex);
				}
				else if( vertex.isLand )
					coastLength++;
			}
		}
		return coastLength;
	}
	
	public static void main(String[] args) 
	{
		// Read input
		Scanner scanner = new Scanner(System.in);
		int n, m;
		n = scanner.nextInt();
		m = scanner.nextInt();
		String line = scanner.nextLine();
		// Surround the map from the input with  zeroes (water squares)
		int N = n+2;
		int M = m+2;
		int[][] map = new int[N][M];
		for( int i = 0; i < N; i++ )
			Arrays.fill(map[i], 0);
		// First and last row and first and last column should be left as zeroes
		// Start at the 1st row of the map (not 0th)
		for( int i = 1; i < N-1; i++ )
		{
			line = scanner.nextLine();
			for( int j = 1; j < M-1; j++ )
			{
				map[i][j] = line.charAt(j-1) - 48;
			}
		}
		scanner.close();
		
		Vertex[][] vertices          = getVertices(map);
		LinkedList<Vertex>[][] edges = getEdges(vertices);
		int coastLength              = getCoastLength( vertices, edges );
		System.out.println(coastLength);
	}
}
