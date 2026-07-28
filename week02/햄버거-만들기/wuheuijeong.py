def solution(ingredient):
    answer = 0 
    stack = [] # 재고를 쌓아둘 리스트
    burger = [1, 2, 3, 1] # 햄버거 답지ㅎㅎ
    
    for i in range(len(ingredient)): # input 재료 길이만큼 반복한다
        stack.append(ingredient[i]) # 재고함에 하나씩 넣는다
        # 햄버거 4층이므로 4개 이상일 때 + stack의 마지막 4개가 햄버거 답지랑 같으면
        if len(stack) >= 4 and stack[-4:] == burger:
                answer += 1 # 하나 만들기 완성
                for j in range(4): # 쓰인 재료 4개 빼내기
                    stack.pop()
            
    return answer