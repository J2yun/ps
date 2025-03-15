import sys
import heapq

input = sys.stdin.readline


N, K = map(int, input().split())
items = []
bags = []

for _ in range(N):
    heapq.heappush(items, list(map(int, input().split())))
for _ in range(K):
    bags.append(int(input()))

bags.sort()

s = 0
heap = []

for max_weight in bags:
    while items and items[0][0] <= max_weight:
        heapq.heappush(heap, -items[0][1])
        heapq.heappop(items)
    if heap:
        s -= heapq.heappop(heap)

print(s)
