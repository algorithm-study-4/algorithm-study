"""
모든 명함을 수납할 수 있는 가장 작은 지갑의 크기

1. 두 길이 중 큰 게 왼쪽, 작은 게 오른쪽
2. 왼쪽의 최댓값 x 오른쪽의 최댓값

O(len(sizes))
"""
def solution(sizes):
    max_a, max_b = 0, 0
    
    for a, b in sizes:
        max_a = max(max_a, max(a, b))
        max_b = max(max_b, min(a, b))
        
    return max_a * max_b