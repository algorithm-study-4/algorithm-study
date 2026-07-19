"""
O(len(s1) + len(s2))
"""
def solution(s1, s2):
    answer = 0
    
    s = set(s1)
    
    for curr in s2:
        if curr in s:
            answer += 1
    
    return answer