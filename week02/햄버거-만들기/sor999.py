"""
1 - 2 - 3 - 1 이면 cnt ++

O(n)

1 2 3 1
1. 스택에 넣기
2. 1 2 3 1 이면 pop
3. 반복

"""

def solution(ingredient):
    answer = 0
    stk = []
    
    for curr in ingredient:
        stk.append(curr)
        
        if stk[-1] == 1 and len(stk) >= 4:
            if stk[-4::] == [1, 2, 3, 1]:
                answer += 1
                for _ in range(4):
                    stk.pop()
                
            
            
    return answer