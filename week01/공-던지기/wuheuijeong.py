def solution(numbers, k):
    
    answer = 0
    now = 0
    
    for i in range(k-1):
        now += 2
        
        if now >= len(numbers):
            now = now % len(numbers)
    
    answer = numbers[now]
    return answer