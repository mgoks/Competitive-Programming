/**
https://open.kattis.com/problems/countingstars
*/

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Halil Murat
 * Kattis - Counting Stars
 * https://open.kattis.com/problems/countingstars
 */
public class CountingStars2 
{
    static char[][] removeStar(char[][] sky, int x, int y)
    {
        //Base case
        if (sky[x][y] == '#')
            return sky;
        
        // Induction step
        sky[x][y] = '#';
        if (x > 0)                      // if sky_x,y is on rows 1..m
            removeStar(sky, x-1, y);    // call removeStar() on the index one above of sky_x,y
        if (x < sky.length-1)           // if sky_x,y is on rows 0..m-1
            removeStar(sky, x+1, y);    // call removeStar() on the index below
        if (y > 0)                      // if it is on columns 1..n
            removeStar(sky, x, y-1);    // ... on the index to the left of sky_x,y
        if (y < sky[x].length-1)        // if it is on columns 0..n-1
            removeStar(sky, x, y+1);    // call on index on the right
        
        return sky;
    }
    
    static int countStars(char[][] sky)
    {
        int nStars = 0;
        for (int i = 0; i < sky.length; i++)
        {
            for (int j = 0; j < sky[i].length; j++)
            {
                if (sky[i][j] == '-')               // if sky_i,j is a star or a part of a star
                {
                    nStars++;                       // increment the counter
                    sky = removeStar(sky, i, j);    // remove the star from the sky
                }
                
            }
        }
        return nStars;
    }
    
    public static void main(String[] args) 
    {
        // Read input and put in a 2D-array
        int m,n,i,j;
        char[][] sky;
        String line;
        Scanner sc = new Scanner(System.in);
        
        int case_ = 0;
        while (sc.hasNext())
        {
            m = sc.nextInt();
            n = sc.nextInt();
            sky = new char[m][n];
            line = sc.nextLine();
            for (i = 0; i < m; i++)
            {
                line = sc.nextLine();
                for (j = 0; j < n; j++)
                    sky[i][j] = line.charAt(j);
            }
            // Print output of countStars()
            System.out.println("Case " + ++case_ + ": " + countStars(sky));
        }
        sc.close();
    }
}

class Kattio extends PrintWriter 
{
    public Kattio(InputStream i) 
    {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    
    public Kattio(InputStream i, OutputStream o) 
    {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() 
    {
        return peekToken() != null;
    }

    public int getInt() 
    {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() 
    {
        return Double.parseDouble(nextToken());
    }

    public long getLong() 
    {
        return Long.parseLong(nextToken());
    }

    public String getWord() 
    {
        return nextToken();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() 
    {
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

    private String nextToken() 
    {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
