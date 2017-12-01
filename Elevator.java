/**
 * Elevator Trouble
 * https://open.kattis.com/problems/elevatortrouble
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author Halil Murat
 */
public class Elevator 
{
    /**
     * @param f     number of floors
     * @param s     start floor
     * @param g     goal floor
     * @param u     number of levels you will go up when you press up
     * @param d     number of levels you will go down when you press down
     * @param adj   contains the reachability information for all floors
     * @return      least number of button pushes required to reach floor g from s, if g is reachable
     *              -1, if g is not reachable
     */
    public static int bfs( int f, int s, int g, int u, int d, int[][] adj )
    {
        int nxt;            // next floor
        int cur;            // current floor
        boolean[] dis;      // dis[i] == true if floor i is already discovered
        boolean[] vis;      // vis[i] == true if floor i is already visited
        int[] par;          // par[i] is the preceding (PARent) floor to floor i (in MST?)
        Deque<Integer> q;   // breadth-first search queue
        
        dis = new boolean[f+1];
        vis = new boolean[f+1];
        par = new int[f+1];
        q = new ArrayDeque<>(f);
        
        // BFS algorithm
        q.add(s);
        dis[s] = true;
        while (!q.isEmpty())
        {
            // Discover floor(s) reachable from the current floor
            cur = q.peekFirst();
            for (int i = 0; i < 2; i++)
            {
                nxt = adj[cur][i];
                if (nxt > 0 && dis[nxt] == false){
                    q.add(nxt);
                    dis[nxt] = true;
                    par[nxt] = cur;
                    if (nxt == g) return getNumOfPushes(s, par, g);
                }
            }
            vis[cur] = true;
            q.removeFirst();
        }
        // If g can't be discovered
        return -1;
    }
    
    /**
     * @param par   par[i] is the preceding (PARent) floor to floor i (in MST?)
     * @param g     goal floor
     * @return      the minimum number of button pushes you have to do the reach floor g
     */
    public static int getNumOfPushes( int s, int[] par, int g)
    {
        int push = 0;
        int cur  = g;   // the floor you are currently on
        
        while (cur != s)
        {
            if (par[cur] > 0) cur = par[cur];
            push++;
        }
        return push;
    }
    
    public static void main(String[] args) 
    {
        Scanner sc;
        int f,s,g,u,d;  
        int[][] adj;    // contains the reachability information for all floors
        
        // Read input
        sc = new Scanner(System.in);
        f = sc.nextInt();
        s = sc.nextInt();
        g = sc.nextInt();
        u = sc.nextInt();
        d = sc.nextInt();
        sc.close();

        /* Populate adj:
         * In the 0th column, the lower floor directly reachable from floor i is stored.
         * In the 1st column, the higher floor is stored.
         * A zero indicates it is not possible go in that direction from floor i. */
        adj = new int[f+1][2];
        for (int i = 1; i < adj.length; i++)
        {
            if (i-d > 0) adj[i][0] = i-d;
            if (i+u <=f) adj[i][1] = i+u;
        }
        
        if (g == s)
        {
            System.out.println(0);
        }
        else
        {
            int ans = bfs(f,s,g,u,d,adj);
            if (ans == -1) System.out.println("use the stairs");
            else System.out.println(ans);
        }
    }
}
