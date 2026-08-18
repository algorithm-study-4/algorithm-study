"""
스택
O(n)
"""
def solution(number, k):
    
    n = len(number)
    
    stk = []
    
    cnt = 0
    
    for curr in number:
        
        while stk and stk[-1] < curr and cnt < k:
            stk.pop()
            cnt += 1
        stk.append(curr)
            
    return "".join(stk[0:n - k])