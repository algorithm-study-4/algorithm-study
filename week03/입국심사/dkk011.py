"""
n: 입국심사를 기다리는 사람
m: 심사관 수 (len(times))
T: 가장 오래 걸리는 심사 시간 (max(times))
범위는 1부터 n * T, 이진 탐색이므로 O(log(nT))
이진 탐색 내부에서 times를 순회하므로 O(m)
O(m log(nT))
"""

def solution(n, times):
    # left: 가능한 최소 시간
    # right: 가장 오래 걸리는 심사관이 모든 사람을 심사하는 경우의 최대 시간
    left, right = 1, max(times) * n
    
    while left <= right:
        mid = (left + right) // 2

        count = 0               # mid 시간 동안 심사 가능한 사람 수
        for time in times:
            count += mid // time
    
        # n명 이상 심사할 수 있으면
        if count >= n:
            right = mid - 1     # 더 짧은 시간으로 가능한지 왼쪽 구간 탐색
        else:
            left = mid + 1      # 시간이 부족하므로 오른쪽 구간 탐색
    
    return left