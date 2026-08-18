# N = len(numbers)
# O(2^N)
# 숫자마다 더하거나 빼는 두 가지 선택이 있으므로 모든 경우의 수를 탐색하면 2^N 가지 발생
def solution(numbers, target):
    answer = 0
    
    # i = numbers 인덱스
    # total = 누적된 합
    def dfs(i, total):
        if i == len(numbers):       # 모든 숫자를 사용한 경우
            if total == target:     # 누적된 합이 target과 같으면
                return 1            # 방법은 한 가지
            else:
                return 0            # target과 다르므로 방법이 없음
            
        # 현재 숫자에 더하거나 빼는 경우를 각각 재귀 호출
        # 두 경우의 결과를 더하면 전체 경우의 수
        return dfs(i + 1, total + numbers[i]) + dfs(i + 1, total - numbers[i])
    
    return dfs(0, 0)