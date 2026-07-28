from collections import deque

def solution(bridge_length, weight, truck_weights):
    
    time = 0 # 시간 세기
   
    curr_weight = 0 # 현재 다리 위의 무게
    
    truck_weights = deque(truck_weights) # 내보내야 하는 차고지
    bridge = deque([0] * bridge_length) # 다리 만들기
    
    while (bridge): # 브릿지에 남아있을 동안 반복
        time += 1 # 시간 + 1초
        curr_weight -= bridge.popleft() # 현재 무게에서 브릿지 제일 왼쪽을 빼준다
        
        if (truck_weights): # 트럭이 남아있을 때
            if curr_weight + truck_weights[0] <= weight: # 현재 다리 무게 + 차고지 1번 주자 괜찮으면
                truck = truck_weights.popleft() # 1번 주자 내보내서 트럭에 담고
                bridge.append(truck) # 다리로 보내기
                curr_weight += truck # 현재 무게에 1번 주자 무게 더하기
            else: # 안 괜찮을 경우
                bridge.append(0) # 브릿지에 0 보내기
        
    return time # 브릿지에 남아있을 동안 반복되므로 전체 시간 합쳐지므로 그냥 return