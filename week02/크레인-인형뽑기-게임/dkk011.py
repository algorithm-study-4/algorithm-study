# M = len(moves), N = len(board)
# O(M * N)

def solution(board, moves):
    answer = 0  # 사라진 인형 개수
    basket = []
    
    for m in moves:
        # 현재 열에서 가장 위에 있는 인형 찾기
        # i는 행
        for i in board:
            # 해당 열에 인형이 있는 경우
            if i[m-1] != 0:
                basket.append(i[m-1])
                i[m-1] = 0  # 빈칸으로 변경
                break       # 한 개만 집고 다음 move로
        
        # 바구니의 마지막 인형 두 개가 같은 경우
        if len(basket) >= 2 and basket[-1] == basket[-2]:
            basket.pop()
            basket.pop()
            answer += 2     # 사라진 인형 개수 증가
            
    return answer