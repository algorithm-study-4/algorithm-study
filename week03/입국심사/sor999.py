"""
- 심사관 심사 시간
- 심사대는 1명 씩만 심사
- 가장 앞에 서있는 사람을 차례대로 심사
모든 사람이 심사 받는데 걸리는 시간을 최소로

1. 완전탐색
- 모든 초에 대해 심사가 가능한지 살펴본다
- O(전체 시간 * 심사관 수) = O(min_time * n * len(times)) = O(10^18 * 10^5) = 10^23 -> 불가 

2. 이분탐색
- 파라메트릭 서치
- '모든 사람이 심사 받는데 걸리는 시간'을 yes/no로 결정.
- xxxooo 형태
- O(log(min_time * n)* len(times))
"""
def solution(n, times):
    min_time = min(times)
    s, e = 1, min_time * n # 최소 시간, 가장 빠른 심사관이 다 처리
    
    def is_valid(std_time):
        people = 0
        for time in times:
            people += std_time // time # std_time 시간 동안, 현재 심사관은 몇명의 사람을 처리할 수 있는지?
            if people >= n: # n명 모두 처리할 수 있다면
                return True
        return False
    
    while s <= e:
        mid = (s + e) // 2
        if is_valid(mid):
            e = mid - 1
        else:
            s = mid + 1
   
    return s