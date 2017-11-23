/**
https://open.kattis.com/problems/t9spelling
@author: Halil Murat Goksel
*/

import java.util.Scanner;

public class T9 
{
    public static char T9RepOf(char c)
    {
        if (c == 'a') return '2';
        else if (c == 'b') return '2';
        else if (c == 'c') return '2';
        else if (c == 'd') return '3';
        else if (c == 'e') return '3';
        else if (c == 'f') return '3';
        else if (c == 'g') return '4';
        else if (c == 'h') return '4';
        else if (c == 'i') return '4';
        else if (c == 'j') return '5';
        else if (c == 'k') return '5';
        else if (c == 'l') return '5';
        else if (c == 'm') return '6';
        else if (c == 'n') return '6';
        else if (c == 'o') return '6';
        else if (c == 'p') return '7';
        else if (c == 'q') return '7';
        else if (c == 'r') return '7';
        else if (c == 's') return '7';
        else if (c == 't') return '8';
        else if (c == 'u') return '8';
        else if (c == 'v') return '8';
        else if (c == 'w') return '9';
        else if (c == 'x') return '9';
        else if (c == 'y') return '9';
        else if (c == 'z') return '9';
        else if (c == ' ') return ' ';
        return '0';
    }
    
    public static String getKeys(String line, int caseNo)
    {
        String keys = "Case #" + caseNo + ": ";
        char prevChar = '0';                            // previous character
        for( int i = 0; i < line.length(); i++)
        {
            if (T9RepOf(prevChar) == T9RepOf(line.charAt(i)))
                keys = keys + " ";
            if (line.charAt(i) == 'a') keys = keys + "2";
            else if (line.charAt(i) == 'b') keys = keys + "22";
            else if (line.charAt(i) == 'c') keys = keys + "222";
            else if (line.charAt(i) == 'd') keys = keys + "3";
            else if (line.charAt(i) == 'e') keys = keys + "33";
            else if (line.charAt(i) == 'f') keys = keys + "333";
            else if (line.charAt(i) == 'g') keys = keys + "4";
            else if (line.charAt(i) == 'h') keys = keys + "44";
            else if (line.charAt(i) == 'i') keys = keys + "444";
            else if (line.charAt(i) == 'j') keys = keys + "5";
            else if (line.charAt(i) == 'k') keys = keys + "55";
            else if (line.charAt(i) == 'l') keys = keys + "555";
            else if (line.charAt(i) == 'm') keys = keys + "6";
            else if (line.charAt(i) == 'n') keys = keys + "66";
            else if (line.charAt(i) == 'o') keys = keys + "666";
            else if (line.charAt(i) == 'p') keys = keys + "7";
            else if (line.charAt(i) == 'q') keys = keys + "77";
            else if (line.charAt(i) == 'r') keys = keys + "777";
            else if (line.charAt(i) == 's') keys = keys + "7777";
            else if (line.charAt(i) == 't') keys = keys + "8";
            else if (line.charAt(i) == 'u') keys = keys + "88";
            else if (line.charAt(i) == 'v') keys = keys + "888";
            else if (line.charAt(i) == 'w') keys = keys + "9";
            else if (line.charAt(i) == 'x') keys = keys + "99";
            else if (line.charAt(i) == 'y') keys = keys + "999";
            else if (line.charAt(i) == 'z') keys = keys + "9999";
            else if (line.charAt(i) == ' ') keys = keys + "0";
            prevChar = line.charAt(i);
        }
        return keys;
    }
    
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();   // number of test cases
        String line = sc.nextLine();
        for (int i = 0; i < n; i++)
        {
            line = sc.nextLine();
            System.out.println(getKeys(line, i+1));
        }
        sc.close();
    }
}
