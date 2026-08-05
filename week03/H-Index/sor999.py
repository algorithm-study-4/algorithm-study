"""
1. 반복문
O(nlogn)
"""
def solution(citations):

    citations.sort()
    
    n = len(citations)
    
    for i in range(n):
        h = n - i # n편 중 h번 이상 개수
        min_c = citations[i] # 최소 인용 횟수
        
        if min_c >= h:
            return h
        
    return 0

"""
2. 이분탐색
O(nlogn)
"""
def solution(citations):
    citations.sort()
    n = len(citations)
    
    s, e = 0, n - 1
    ans = 0
    while s <= e:
        mid = (s + e) // 2
        
        h = n - mid # n편 중 h번 이상 개수
        min_c = citations[mid] # 최소 인용 횟수
        
        if min_c >= h:
            ans = max(h, ans)
            e = mid - 1
        else:
            s = mid + 1
    return ans