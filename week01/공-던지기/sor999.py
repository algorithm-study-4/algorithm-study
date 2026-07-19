"""
1. 실제로 던지기, O(k)
2. 수학, O(1)
"""
# 1. 구현
def solution(numbers, k):
    n = len(numbers)
    idx = 0 
    for _ in range(k - 1):
        idx = (idx + 2) % n
        
    return numbers[idx]
"""
# 2. 수학
def solution(numbers, k):
    n = len(numbers)
    return numbers[2 * (k - 1) % n]
"""
