n = int(input())
ans = 0

def dfs(nums, total):
    global ans

    if total >= n:
        if total == n:
            ans += 1
        return

    for i in range(1, 4):
        nums.append(i)
        dfs(nums, total + i)
        nums.pop()

dfs([], 0)

print(ans)