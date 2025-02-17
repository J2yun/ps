N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]
dp = [[[0, 0, 0] for _ in range(N)] for _ in range(N)]
for i in range(1, N):
    if arr[0][i]:
        break
    dp[0][i][0] = 1

for r in range(1, N):
    for c in range(1, N):
        if arr[r][c] == 1:
            dp[r][c] = [0, 0, 0]
            continue
        # 우 방향
        dp[r][c][0] = dp[r][c - 1][0] + dp[r][c - 1][2]
        # 하 방향
        dp[r][c][1] = dp[r - 1][c][1] + dp[r - 1][c][2]
        # 우하 방향
        if arr[r - 1][c] == 0 and arr[r][c - 1] == 0:
            dp[r][c][2] = sum(dp[r - 1][c - 1])

print(sum(dp[N - 1][N - 1]))
