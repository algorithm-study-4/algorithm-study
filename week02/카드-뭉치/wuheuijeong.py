def solution(cards1, cards2, goal):
    answer = ''
    
    num1 = 0 # cards1 담당 인덱스
    num2 = 0 # cards2 담당 인덱스
    makeGoal = [] # 뽑은 단어들 담을 리스트 만들기
    length = len(goal)
    
    for i in range(length): # goal의 길이만큼 반복하기
        
        # cards1 길이 안에서 goal[i]와 cards1[num1]이 같다면
        if num1 < len(cards1) and goal[i] == cards1[num1]: 
            makeGoal.append(cards1[num1]) # makeGoal 에 넣고
            num1 += 1 # cards1 담당 인덱스 +1
            
        # cards1 에서 없다면 -> cards2 길이 내 goal[i]와 cards2[num2] 확인
        elif num2 < len(cards2) and goal[i] == cards2[num2]:  
            makeGoal.append(cards2[num2]) # makeGoal에 cards2[num2] 넣기
            num2 += 1 # cards2 담당 인덱스 +1
            
        else: # cards1, cards2 에서도 찾지 못했다면 답은 없음
            answer = 'No'
            
    if  answer != 'No'and goal == makeGoal:
        answer = 'Yes'
        
    return answer