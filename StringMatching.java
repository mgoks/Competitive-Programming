import java.util.ArrayList;
import java.util.Scanner;

public class StringMatching 
{
    private static int[] piOf( char[] pattern )
    {
        int[] pi = new int[pattern.length];
        int index = 0;
        int i = 1;
        while( i < pattern.length )
        {
            if( pattern[i] == pattern[index] )
            {
                pi[i] = index + 1;
                index++;
                i++;
            }
            else
            {
                if( index != 0 )
                    index = pi[index-1];
                else
                {
                    pi[i] = 0;
                    i++;
                }
            }
        }
        return pi;
    }
    
    static void kmp( String pattern, String text )
    {
        int[] pi = piOf( pattern.toCharArray() );
        ArrayList<Integer> rho = new ArrayList<Integer>();
        int i = 0;                                          // index for pattern
        int j = 0;                                          // index for text
        while( j < text.length() )                          // scan all characters
        {
            if( text.charAt(j) == pattern.charAt(i) )       // if characters match
            {
                i++;
                if( i == pattern.length() )                 // if all characters match
                { 
                    rho.add(j - pattern.length() + 1);      // add the position of the match to rho
                    i = pi[i-1];                            // go to the position that is stored in pi[i-1] and continue searching from there 
                }
                j++;
            }
            else if( i > 0 )
            {
                i = pi[i-1];
            }
            else
            {
                j++;
            }
        }
        for (Integer index: rho)
        {
                System.out.print(index + " ");              // print the indices stored in rho
        }
    }
    
    public static void main(String[] args) 
    {
        // Read from standard input
        Scanner sc = new Scanner(System.in);
        ArrayList<String> input = new ArrayList<String>();
        while (sc.hasNext())
            input.add(sc.nextLine());
        sc.close();
        
        for( int i = 0; i < input.size(); i += 2 )
        {
            kmp( input.get(i), input.get(i+1) );
            System.out.println("");
        }
    }
}
