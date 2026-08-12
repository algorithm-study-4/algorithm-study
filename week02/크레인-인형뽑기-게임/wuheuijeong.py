def solution(board, moves):
    answer = 0
    stack = [] # 인형보관함 만들기 - 빼낸 인형은 stack으로 쌓아서 bomb
    
    for i in range(len(moves)): # moves의 길이만큼 빼내기 반복
        
        # 해당하는 칸 number를 순회하다가 0이 아닌 걸 마주치면
        # stack 에 빼낸 숫자를 넣고 그 자리는 0으로 바꾼다
        
        # 제일 위에 있는 '0이 아닌 인형'을 빼야 함
        # 0이면 한 칸 더 가고, 0이 아닌 수가 나오면 break
        # 끝까지 갔는데 다 0이면 그냥 넘어감
            
        for r in range(len(board)): # 보드의 세로 길이만큼 반복한다
		        # board[현재 r][moves에서 빼내야하는 칸 index] 가 인형인 경우! (!= 0)
            if board[r][moves[i]-1] != 0:
                stack.append(board[r][moves[i]-1]) # 보관함에 인형을 넣는다!
                board[r][moves[i]-1] = 0 # 원래 있던 곳은 0으로 채워주기
                break # 인형 빼냈으니 더이상 안 가도 됨!
        
        # 보관함에서의 인형 Bomb 처리
        # 길이가 2 이상이고, 위 2개가 같을 때
        if len(stack) >= 2 and (stack[-2] == stack[-1]):
            stack.pop()
            stack.pop() # 2개가 같은 인형이므로 bomb 해서 사라진다!~
            answer += 2 # 그리고 찾은 인형 2개를 + 해준다
    
    return answer