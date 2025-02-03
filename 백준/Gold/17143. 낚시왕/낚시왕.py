import sys
input = sys.stdin.readline

def sol():
    R, C, M = map(int, input().split())
    sea = [[False for _ in range(C)] for _ in range(R)]
    sharks = []
    for _ in range(M):
        r, c, s, d, z = map(int, input().split())
        sea[r - 1][c - 1] = True
        sharks.append((r - 1, c - 1, s, d - 1, z))
    # 1:상 2:하 3:우 4:좌
    dr = [-1, 1, 0, 0]
    dc = [0, 0, 1, -1]
    
    total = 0
    for king in range(C):
        # 1-2. 낚시왕 이동 및 상어 잡기
        for i in range(R):
            if sea[i][king]:
                # print(f"Get {i},{king}")
                sea[i][king] = False
                for j in range(len(sharks)):
                    if i == sharks[j][0] and king == sharks[j][1]:
                        total += sharks[j][4]
                        sharks.remove(sharks[j])
                        break
                break
    
        # 3. 상어 이동
        sharks_dic = {}
        for i in range(len(sharks)):
            r, c, s, d, z = sharks[i]
            sea[r][c] = False
            nr = r + dr[d] * s
            nc = c + dc[d] * s
            if d == 0 or d == 1:
                if (nr // (R - 1)) % 2:
                    nr = R - 1 - nr % (R - 1)
                    d = (d + 1) % 2
                else:
                    nr = nr % (R - 1)
            if d == 2 or d==3:
                if (nc // (C - 1)) % 2:
                    nc = C - 1 - nc % (C - 1)
                    d = 2 + (d + 1) % 2
                else:
                    nc = nc % (C - 1)
            key = f"{nr},{nc}"
            if key not in sharks_dic.keys():
                sharks_dic[key] = []
            sharks_dic[key].append((nr, nc, s, d, z))
    
        # 4. 상어 중복 제거
        sharks.clear()
        for key, value in sharks_dic.items():
            if len(value) >= 2:
                big = sorted(value, key=lambda x: x[4])[-1]
                sharks.append(big)
                sea[big[0]][big[1]] = True
            else:
                sharks.append(value[0])
                sea[value[0][0]][value[0][1]] = True
    
    print(total)

sol()