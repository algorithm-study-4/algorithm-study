"""
- 다리 건너는데 bridge_length초
- 대기 -> 다리로 가는데 1초

O(n)
"""
from collections import deque
def solution(bridge_length, weight, truck_weights):
    
    q = deque()
    size = 0
    time = 0
    
    n = len(truck_weights)
    truck_idx = 0
    
    while truck_idx < n or q:
       
        time += 1
        
        # 다리에서 꺼내기
        if q:
            prev_w, prev_time = q[0]
            
            if time - prev_time >= bridge_length:
                size -= prev_w
                q.popleft()
            
        # 다리에 넣기
        if truck_idx < n:
            curr_w = truck_weights[truck_idx]
 
            if size + curr_w <= weight:
                size += curr_w
                q.append((curr_w, time))
                truck_idx += 1
        elif q:
            time = q[0][1] + bridge_length - 1
         
        
    return time