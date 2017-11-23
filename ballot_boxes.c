/**
 * Kattis - Distributing ballot boxes
 * https://open.kattis.com/problems/ballotboxes
 */

#include <stdio.h>

int main()
{
    int N;                  /* number of cities (1 <= N <= 500,000)                         */
    int B;                  /* number of ballot boxes (N <= B <= 2,000,000)                 */
    int hi;                 /* high                                                         */
    int mid;                /* box capacity                                                 */
    int lo;                 /* low                                                          */
    int t;                  /* test case counter                                            */
    int maxpop;             /* the population of the city which has the maximum population  */
    int population[500000]; /* population of i^th city (1 <= pop[i] <= 5,000,000)           */

    for (t = 0; t < 3; t++)
    {
        // Read input
        scanf("%d %d", &N, &B);
        if (N == -1 && B == -1)
            break;
        maxpop = 0;
        for (int i = 0; i < N; i++) {
            scanf("%d", &population[i]);
            if (population[i] > maxpop) maxpop = population[i];
        }

        // Main algorithm
        lo = 1;
        hi = maxpop;
        while (lo < hi) {
            mid = (lo + hi) / 2;    // take the middle of low and high

            int Bneeded = 0;        // number of boxes needed

            /*
             * Here we need to calculate the number of boxes needed for the boxes with the capacity mid.
             * To do that, we could simply do population/mid, but
             * if population < mid, then we would get 0 instead of 1.
             * Thus, we need to do (pop + mid)/mid to prevent that.
             * What if pop = mid? (mid + mid)/mid = 2 !!!
             * Solution: Simply subtract 1 from the numerator!
             */
            for (int i = 0; i < N; i++)
                Bneeded = Bneeded + (population[i] + mid - 1) / mid;

            if (Bneeded > B)
                lo = mid + 1;
            else // if cont <= B
                hi = mid;
        }
        printf("%d\n", lo); // In some cases mid will be off by one, so print lo or hi instead
    }
    return 0;
}
