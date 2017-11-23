/**
https://open.kattis.com/problems/natrij
@author Halil Murat Goksel
*/

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Natrij {

    public static void main(String[] args) 
    {
        Kattio io = new Kattio(System.in, System.out);
        String curTime = io.getWord();
        String expTime = io.getWord();
        
        if (curTime.equals(expTime))
        {
            io.println("24:00:00");
            io.close();
            System.exit(0);
        }
        
        int curSec = 0;
        int curMin = 0; 
        int curHour = 0;
        curHour      = Character.getNumericValue(curTime.charAt(0)) * 10;
        curHour     += Character.getNumericValue(curTime.charAt(1));
        curMin       = Character.getNumericValue(curTime.charAt(3)) * 10;
        curMin      += Character.getNumericValue(curTime.charAt(4));
        curSec       = Character.getNumericValue(curTime.charAt(6)) * 10;
        curSec      += Character.getNumericValue(curTime.charAt(7));

        int expHour, expMin, expSec;
        expHour      = Character.getNumericValue(expTime.charAt(0)) * 10;
        expHour     += Character.getNumericValue(expTime.charAt(1));
        expMin       = Character.getNumericValue(expTime.charAt(3)) * 10;
        expMin      += Character.getNumericValue(expTime.charAt(4));
        expSec       = Character.getNumericValue(expTime.charAt(6)) * 10;
        expSec      += Character.getNumericValue(expTime.charAt(7));
        
        // explosion time - current time
        int desSec, desMin, desHour;
        // Substract seconds
        if (expSec < curSec)
        {
            expMin--;
            expSec += 60;
        }
        desSec = expSec - curSec;
        
        if (expMin < curMin)
        {
            expHour--;
            expMin += 60;
        }
        desMin = expMin - curMin;
        
        if (expHour < curHour)
            expHour += 24;
        desHour = expHour - curHour;
        
        // print
        if (desHour < 10) io.print(0);
        io.print(desHour + ":");
        if (desMin < 10) io.print(0);
        io.print(desMin + ":");
        if (desSec < 10) io.print(0);
        io.println(desSec);
        
        io.close();
    }
}

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
