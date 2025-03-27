from itertools import combinations

N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]

chicken, house = [], []

for r in range(N):
    for c in range(N):
        if arr[r][c] == 1:
            house.append((r, c))
        elif arr[r][c] == 2:
            chicken.append((r, c))

chicken_dis = []
for hou in house:
    tmp = []
    for chic in chicken:
        tmp.append(abs(hou[0] - chic[0]) + abs(hou[1] - chic[1]))
    chicken_dis.append(tmp)

min_chic_dis = 1e9
for comb in combinations([i for i in range(len(chicken))], M):
    sum_chic = 0
    for i in range(len(house)):
        tmp_min = 1e9
        for j in comb:
            if chicken_dis[i][j] < tmp_min:
                tmp_min = chicken_dis[i][j]
        sum_chic += tmp_min
    if sum_chic < min_chic_dis:
        min_chic_dis = sum_chic

print(min_chic_dis)
