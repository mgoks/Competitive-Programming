/**
 * Kattis - Get Shorty
 * open.kattis.com/problems/getshorty
 * If you would like run this program on your computer, please change file name to Graph.java.
 */

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Represents a corridor
 * @author Halil Murat
 */
class Edge
{
	final int v;
	final float weight;
	
	Edge(int pV, float pWeight)
	{
		v 	= pV;
		weight 	= pWeight;
	}
}

/**
 * Represents a dungeon
 * @author Halil Murat
 */
public class Graph 
{
	List<List<Edge>> edges;
	int[] degree;
	int nVertices;
	int nEdges;
	final int maxDegree;
	
	Graph(int pNVertices, int pNEdges)
	{
		edges 		= new ArrayList<List<Edge>>(pNVertices);
		for (int i = 0; i < pNVertices; i++)
			edges.add(new ArrayList<Edge>());
		degree 		= new int[pNVertices];
		nVertices 	= pNVertices;
		nEdges 		= pNEdges;
		maxDegree 	= pNVertices - 1;
	}
	
	Edge insertEdge( int x, int y, float f, boolean directed, Kattio io )
	{
		Edge e;
		
		e = new Edge(y, f);
		edges.get(x).add(e);
		degree[x]++;
		
		if (directed == false)
			insertEdge(y,x,f,true,io);
		else
			nEdges++;
		return e;
	}

	/**
	 * Slightly modified version of Dijkstra's Algorithm to find Mikael's 
	 * minimum-possible fraction at the exit (intersection n-1)
	 * @param start		start vertex (vertex #0)
	 * @return		Mikael's fraction at intersection #(n-1)
	 */
	float dijkstra(int start)
	{
		int i;
		boolean[] inTree = new boolean[this.nVertices];
		float[] fraction = new float[nVertices];
		int v, w;
		float factor, frac;
		int[] parent = new int[nVertices];
		
		for (i = 0; i < nVertices; i++)
		{
			fraction[i] = 0.0f;
			parent[i] = -1;
		}
		fraction[start] = 1.0f;
		v = start;
		while (inTree[v] == false)
		{
			inTree[v] = true;
			for (i = 0; i < degree[v]; i++)
			{
				w = edges.get(v).get(i).v;
				factor = edges.get(v).get(i).weight;
				if (fraction[w] < fraction[v] * factor)
				{
					fraction[w] = fraction[v] * factor;
					parent[w] = v;
				}
			}
			v = 0;
			frac = 0.0f;
			for (i = 1; i < nVertices; i++)
			{
				if (inTree[i] == false && frac < fraction[i])
				{
					frac = fraction[i];
					v = i;
				}
			}
		}
		return fraction[nVertices-1];
	}
	
	public static void main(String[] args) 
	{
		int n,m,u,v;
		float f;
		int i;
		Kattio io;
		
		io = new Kattio(System.in, System.out);
		while(true)
		{
			n = io.getInt();
			m = io.getInt();
			if( n == 0 && m == 0 )
				break;
			
			Graph g = new Graph(n, m);	// initialize graph
			for (i = 0; i < m; i++)
			{
				u = io.getInt();
				v = io.getInt();
				f = (float) io.getDouble();
				g.insertEdge(u, v, f, false, io);
			}
			io.println(String.format("%.4f", g.dijkstra(0)));
		}
		io.close();
	}
}

/** Simple yet moderately fast I/O routines.
 * @author: Kattis
 */
class Kattio extends PrintWriter {
   public Kattio(InputStream i) {
       super(new BufferedOutputStream(System.out));
       r = new BufferedReader(new InputStreamReader(i));
   }
   public Kattio(InputStream i, OutputStream o) {
       super(new BufferedOutputStream(o));
       r = new BufferedReader(new InputStreamReader(i));
   }

   public boolean hasMoreTokens() {
       return peekToken() != null;
   }

   public int getInt() {
       return Integer.parseInt(nextToken());
   }

   public double getDouble() {
       return Double.parseDouble(nextToken());
   }

   public long getLong() {
       return Long.parseLong(nextToken());
   }

   public String getWord() {
       return nextToken();
   }

   private BufferedReader r;
   private String line;
   private StringTokenizer st;
   private String token;

   private String peekToken() {
       if (token == null)
           try {
               while (st == null || !st.hasMoreTokens()) {
                   line = r.readLine();
                   if (line == null) return null;
                   st = new StringTokenizer(line);
               }
               token = st.nextToken();
           } catch (IOException e) { }
       return token;
   }

   private String nextToken() {
       String ans = peekToken();
       token = null;
       return ans;
   }
}
