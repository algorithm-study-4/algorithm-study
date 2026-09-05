"""
O(nlogn)
"""
import heapq
def solution(jobs):
    total = 0
    n = len(jobs)
    
    pq = []
    
    
    jobs.sort(key=lambda x: x[0])
    
    i = 0
    time = 0
    cnt = 0
    while cnt < n:
        while i < n and jobs[i][0] <= time:
            start, length = jobs[i]
            heapq.heappush(pq, (length, start, i))
            i += 1
        if pq:
            length, start, _ = heapq.heappop(pq)
            time += length 
            total += time - start
            cnt += 1
            
        else:
            time = jobs[i][0]

    return total // n