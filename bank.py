#!/usr/bin/python3

# Bank queue: open.kattis.com/problems/bank

from collections import defaultdict
from heapq import *

# Read N and T from standard input
# N: number of people in the queue
# T: time in minutes until Oliver closes the bank
N, T = list(map(int, input().split()))
d = defaultdict(list)

# Read the rest of the input and add it to the dictionary d
# c: amount of cash in Swedish crowns person i has
# t: time in minutes from now after which person i leaves if not served
for n in range(N):
    c, t = list(map(int, input().split()))
    d[t].append(c)

amounts_heap = []
max_amount = 0

# Greedy solution:
#
for t in range(T)[::-1]:
    # Add all amounts of customers that will leave by the same time to a max-heap
    for amount in d[t]:
        # Negate the price since heapq is min-heap by default
        heappush(amounts_heap, -amount)
    # Pop the maximum amount of cash from the customers who are willing to wait at least t minutes
    if amounts_heap:
        max_amount += heappop(amounts_heap)

print(-max_amount)
