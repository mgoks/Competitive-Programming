/**
 * https://open.kattis.com/problems/ones
 */

#include <stdio.h>

int min_multiple( int n )
{
    int can  = 1;     // candidate multiple of n which consists only of ones
    int d    = 1;     // number of 1's in candidate

    // while candidate is not divisible by n (if it was divisible, we would have the solution)
    while (can % n != 0)
    {
        // (add 1 to the left of candidate) mod n
        // Here we are applying modular division because candidate can get too big.
        // 11 mod 3 = 2, 111 mod 3 = 0!!!
        can = (can*10 + 1) % n;
        d++;
    }
    return d;
}

int main()
{
    int n;
    while (scanf("%d", &n) == 1)
        printf("%d\n", min_multiple(n));

    return 0;
}
