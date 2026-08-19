"""
양옆
- 왼쪽부터 무조건 빌려주고, 안되면 오른쪽 빌려주자
- 여벌 체육복 가져옴 + 도난 -> 무조건 본인이 사용

O(n + klogk)
"""

def solution(n, lost, reserve):
    ans = 0
    
    students = [True] * (n + 2)
    
    lost_set = set(lost)
    reserve_set = set(reserve)
    for l in lost:
        if l not in reserve_set:
            students[l] = False
        
    reserve.sort()
    
    for r in reserve:
        if r not in lost_set:
            if not students[r - 1]:
                students[r - 1] = True
            elif not students[r + 1]:
                students[r + 1] = True

    for i in range(1, n + 1):
        if students[i]:
            ans += 1

	    return ans