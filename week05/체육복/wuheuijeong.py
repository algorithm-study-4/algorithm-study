def solution(n, lost, reserve):
    
    answer = 0
    can_attend = [1] * n # 기본 하나씩은 다 갖고있다고 하고
    
    # 체육복 도난당한 애들은 -1개
    for k in lost:
        can_attend[k-1] -= 1
        
    # 체육복 2개 갖고 있는 애들은 +1개
    for k in reserve:
        can_attend[k-1] += 1
        
    # 체육복 없는 애 앞/뒷번호가 여분 있을 경우 빌려주기 처리
    for i in range(n):
        if can_attend[i] == 0 and i >= 1 and can_attend[i-1] == 2:
            can_attend[i] += 1
            can_attend[i-1] -= 1
        elif can_attend[i] == 0 and i <= n-2 and can_attend[i+1] == 2:
            can_attend[i] += 1
            can_attend[i+1] -= 1
        else:
            pass
    
    
    # 총 True인 사람 수 세기
    for t in can_attend:
        if t >= 1:
            answer += 1  
        
    return answer