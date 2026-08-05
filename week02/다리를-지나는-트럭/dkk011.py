# N = len(truck_weights), L = bridge_length
# 각 트럭은 deque에 한 번 추가되고 한 번 제거 -> O(N)
# 마지막 트럭이 bridge_length 만큼 이동하는 시간 -> O(L)
# O(N + L)

# deque는 양쪽 끝에서 데이터 추가, 제거 가능
# 앞쪽 -> 건넌 트럭 제거, 뒤쪽 -> 다음 트럭 추가

from collections import deque

def solution(bridge_length, weight, truck_weights):
    time = 0            # 현재까지 흐른 시간
    bridge = deque()    # (트럭 무게, 건너는 시간)
    current_weight = 0  # 현재 다리 위 무게
    idx = 0             # 트럭 인덱스

    # 남은 트럭이 있거나, 다리 위에 트럭이 남은 동안 반복
    while idx < len(truck_weights) or bridge:
        time += 1

        # 다리를 건넌 트럭 제거
        if bridge and bridge[0][1] == time:
            truck_weight, _ = bridge.popleft()
            current_weight -= truck_weight  # 다리 위 무게에서 제거
        
        # 남은 트럭이 있는 경우 다리에 올라갈 수 있는지 확인
        if idx < len(truck_weights):
            next_weight = truck_weights[idx]
            
            # (현재 다리 위 무게 + 다음 트럭 무게)가 제한 무게 이하인 경우
            if current_weight + next_weight <= weight:
                # (현재 시간 + 다리 길이) 만큼 지나면 건넘
                bridge.append((next_weight, time + bridge_length))
                current_weight += next_weight
                idx += 1    # 다음 트럭으로 이동

    return time