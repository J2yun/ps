import sys

sys.setrecursionlimit(1000000)
input = sys.stdin.readline

N, R, Q = map(int, input().split())
graph = [[] for _ in range(N + 1)]
for _ in range(N - 1):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

visit = [False] * (N + 1)
size = [0] * (N + 1)


def cntSize(root):
    size[root] = 1
    visit[root] = True
    for child in graph[root]:
        if not visit[child]:
            cntSize(child)
            size[root] += size[child]


cntSize(R)

for _ in range(Q):
    n = int(input())
    print(size[n])
