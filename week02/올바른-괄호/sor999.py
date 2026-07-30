"""
) 이면, peek ( 확인해서 pop

만약 모든 게 끝나고 스택에 값이 있으면 오류

O(n), n = len(s)
"""
def solution(s):
    
    stk = []
    for curr in s:
        if stk and stk[-1] == '(' and curr == ')':
            stk.pop()
        else:
            stk.append(curr)
    if stk:
        return False
    
    return True