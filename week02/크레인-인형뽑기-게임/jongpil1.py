from collections import deque
def solution(board, moves):
    stack = []
    answer = 0

    for move in moves :
        col = move - 1

        for row in range(len(board)) :
            if board[row][col] !=0 :
                doll = board[row][col]
                board[row][col] = 0

                if stack and stack[-1] == doll :
                    stack.pop()
                    answer += 2
                else :
                    stack.append(doll)

                break

    return answer