from collections import deque
def solution(cards1, cards2, goal):
    card1 = deque(cards1)
    card2 = deque(cards2)

    for word in goal :
        if card1 and card1[0] == word :
            card1.popleft()
        elif card2 and card2[0] == word :
            card2.popleft()
        else :
            return "No"
            
    
    return "Yes"
    