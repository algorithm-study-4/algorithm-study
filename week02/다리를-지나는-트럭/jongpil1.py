from collections import deque
def solution(bridge_length, weight, truck_weights):
    stack = deque(maxlen=bridge_length)
    truck = deque(truck_weights)
    for _ in range(bridge_length) :
        stack.append(0)
    count = 0
    i = 0
    while (stack or truck) :
        count += 1
        stack.popleft()

        if not truck :
            if sum(stack) == 0:
                break
            stack.append(0)
            continue

        if(sum(stack) + truck[0] <= weight) :
            stack.append(truck.popleft())
            i += 1
        else :
            stack.append(0)
       
    return count