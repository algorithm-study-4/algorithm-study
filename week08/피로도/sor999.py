def solution(k, dungeons):
    ans = 0
    n = len(dungeons)
    visited = [False] * n

    def dfs(curr, depth):
        nonlocal ans
        ans = max(ans, depth)

        for i in range(n):
            if visited[i] or dungeons[i][0] > curr:
                continue

            visited[i] = True
            dfs(curr - dungeons[i][1], depth+1)
            visited[i] = False

    dfs(k, 0)

    return ans