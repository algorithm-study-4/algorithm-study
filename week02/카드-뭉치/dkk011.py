# goal의 길이 만큼 실행 -> O(N)

def solution(cards1, cards2, goal):
    # 각 카드 뭉치의 현재 인덱스
    i, j = 0, 0
    
    for word in goal:
        
        # 현재 단어가 cards1의 다음 카드인 경우
        if i < len(cards1) and cards1[i] == word:
            i += 1
            
        # 현재 단어가 cards2의 다음 카드인 경우
        elif j < len(cards2) and cards2[j] == word:
            j += 1
            
        # 어느 카드 뭉치에서도 만들 수 없는 경우
        else:
            return "No"
        
    return "Yes"