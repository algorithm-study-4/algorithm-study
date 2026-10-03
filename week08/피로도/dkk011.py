# O(N × N!)
def solution(k, dungeons):
    # 각 던전 방문 여부 저장
    visited = [False] * len(dungeons)

    def dfs(k, count):
        max_count = count

        for i in range(len(dungeons)):
            required, cost = dungeons[i]

            # 현재 던전을 방문하지 않았고 피로도가 충분하면
            if not visited[i] and k >= required:
                visited[i] = True

                # 현재 던전 방문하고 남은 피로도로 다음 던전 탐색
                result = dfs(k - cost, count + 1)

                # 최대 방문 던전 수 갱신
                max_count = max(max_count, result)

                # 던전 방문 여부 복구
                visited[i] = False

        return max_count

    return dfs(k, 0)