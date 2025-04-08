n = int(input())
a, b, c, d = [], [], [], []
for _ in range(n):
    x, y, z, r = map(int, input().split())
    a.append(x)
    b.append(y)
    c.append(z)
    d.append(r)

ab = dict()
for i in range(n):
    for j in range(n):
        aabb = a[i] + b[j]
        if aabb not in ab.keys():
            ab[aabb] = 0
        ab[aabb] += 1


cnt = 0
for i in range(n):
    for j in range(n):
        ccdd = c[i] + d[j]
        if -ccdd in ab.keys():
            cnt += ab[-ccdd]

print(cnt)
