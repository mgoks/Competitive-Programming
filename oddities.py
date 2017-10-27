#! /usr/bin/env python3
import sys

sys.stdin.readline()

for line in sys.stdin:
    n = int(line)
    if n % 2 == 0:
        print(n, " is even")
    else:
        print(n, " is odd")
