/**
 * Restaurant orders: open.kattis.com/problems/orders
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Halil Murat
 */
public class Orders {
    
    static ArrayList<Integer> backtrack( long[] p, int[] menu, int index, ArrayList<Integer> ordered, int item )
    {
        if( index == 0 )
            return ordered;
        else if( p[index - menu[item]] > 0 )
        {
            index = index - menu[item];
            ordered.add(menu[item]);
            return backtrack(p, menu, index, ordered, item);
        }
        else
            return backtrack(p, menu, index, ordered, ++item);
    }
    
    static void order( int total, int[] menu, HashMap<Integer,Integer> unsortedMenu)
    {
        /*
         * new array p[0...total_cost]
         * each index i refers to a price level i SEK
         */
        long[] p = new long[total + 1];
        Arrays.fill(p, 0);
        p[0] = 1;
                
        // Main loop
        // for all items on the menu
        for( int i = 0; i < menu.length; i++ )          
        {
        	/*
        	 * scan all price levels in p from the cost j of item i to total cost
        	 * p[j] == 0 means it impossible to make an order with total cost j, 
        	 * given using the first i items
        	 * p[j] > 0  means it is possible
        	 */
            for( int j = menu[i]; j < p.length; j++ )   
                    p[j] += p[j - menu[i]];   
        }
        if( p[p.length-1] > 1 )
            System.out.println("Ambiguous");
        else if( p[p.length-1] == 0 )
            System.out.println("Impossible");
        else if( p[p.length-1] == 1) 
        {
            int item = 0;
            int index = p.length - 1;
            ArrayList<Integer> orderedCosts = new ArrayList<Integer>();
            backtrack(p, menu, index, orderedCosts, item).toString();
            int[] orderedItems = new int[orderedCosts.size()];
            for( int i = 0; i < orderedCosts.size(); i++ )
            	orderedItems[i] = unsortedMenu.get(orderedCosts.get(i));
            Arrays.sort(orderedItems);
            for( int i = 0; i < orderedItems.length; i++ )
            	System.out.print(orderedItems[i] + " ");
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        
        // Read from standard input
        Scanner sc = new Scanner(System.in);
        // number of items on the menu
        int n = sc.nextInt();                   
        int[] sortedMenu = new int[n];
        for( int i = 0; i < sortedMenu.length; i++ )
            sortedMenu[i] = sc.nextInt();
        // number of orders given to waiter
        int m = sc.nextInt();                   
        int[] totals = new int[m];
        for( int i = 0; i < totals.length; i++ )
            totals[i] = sc.nextInt();
        sc.close();
        
        HashMap<Integer,Integer> unsortedMenu = new HashMap<>(n);
        for( int i = 0; i < sortedMenu.length; i++ )
        	unsortedMenu.put(sortedMenu[i], i+1);
        	
        Arrays.sort(sortedMenu);
        for( int i = 0; i < totals.length; i++ )
            order( totals[i], sortedMenu, unsortedMenu);
    }
}
