def solution(s):
    answer = True
    stack = []
    
    if s[0] == ')': # input 첫 번째가 ) 괄호이면 답은 그냥 False
        answer = False
        
    else:
        for i in range(len(s)): # s의 길이만큼 반복
            stack.append(s[i]) # 스택에 s[i]번째 넣기
            
            # stack 의 길이가 2보다 커졌을 때 () 가 있는지 검사, pop
            if len(stack) >= 2 and stack[-2:] == ['(',')']:
                stack.pop()
                stack.pop()
        
        if len(stack) > 0:  # stack에 남아있는 길이가 0보다 크면 괄호가 다 안 닫힌 거
            answer = False # false를 리턴한다
            
    return answer