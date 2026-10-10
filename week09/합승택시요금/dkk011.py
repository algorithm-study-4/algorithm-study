"""
O(N^2):
거리 배열 초기화

O(M):
택시 요금 정보를 거리 배열에 저장

O(N^3):
플로이드-워셜 알고리즘으로 모든 정점 쌍의 최단 거리 계산

O(N):
합승 종료 지점을 바꿔 가며 총 택시 요금 계산

전체 시간 복잡도는 O(N^3)
"""

def solution(n, s, a, b, fares):
    INF = float('inf')

    # dist[i][j]는 i번 지점에서 j번 지점까지의 최저 택시 요금
    dist = [[INF] * (n + 1) for _ in range(n + 1)]
    answer = INF

    # 자기 자신까지의 요금은 0
    for i in range(1, n + 1):
        dist[i][i] = 0

    # 택시 요금은 양방향이므로 양쪽 거리에 모두 저장
    for c, d, f in fares:
        dist[c][d] = f
        dist[d][c] = f

    # k번 지점을 거쳐 가는 요금이 더 적으면 최저 요금 갱신
    for k in range(1, n + 1):
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])

    # 합승을 종료할 지점을 1번부터 n번까지 확인            
    for k in range(1, n + 1):
        # s에서 k까지 합승한 뒤, k에서 a와 b가 각각 이동
        total = dist[s][k] + dist[k][a] + dist[k][b]

        # 가능한 경로 중 총 택시 요금이 가장 적은 값 선택
        answer = min(answer, total)

    return answer