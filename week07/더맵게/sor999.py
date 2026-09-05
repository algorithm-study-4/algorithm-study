"""
O(len(scoville))
"""
import heapq
def solution(scoville, K):
    cnt = 0
    
    pq = scoville
    
    heapq.heapify(pq)
    
    while pq[0] < K:
        if len(pq) < 2:
            return -1
        
        a = heapq.heappop(pq)  
        b = heapq.heappop(pq)
        
        new_v = a + b * 2
        
        heapq.heappush(pq, new_v)
        
        cnt += 1
    
    return cnt