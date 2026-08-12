# O(n)
def solution(sizes):
    max_w = 0
    max_h = 0
    
    for w, h in sizes:
        # 명함을 회전할 수 있으므로
        # 항상 w는 긴 쪽, h는 짧은 쪽이 되도록
        w, h = max(w, h), min(w, h)
        max_w = max(max_w, w)
        max_h = max(max_h, h)
        
    return max_w * max_h

"""
# O(n) + O(n) → O(n)
def solution(sizes):
    return max(max(i) for i in sizes) * max(min(i) for i in sizes)
"""