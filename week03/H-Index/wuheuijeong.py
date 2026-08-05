def solution(citations):

    # 시간복잡도 줄이는 시도 필!
    
    # 1. 주어진 citations 배열 정렬하기
    # [0,1,3,5,6]
    
    for i in range(len(citations)):
        for j in range (i, len(citations)):
            if citations[i] > citations[j]:
                a = citations[i]
                citations[i] = citations[j]
                citations[j] = a
    
    # 2. H-index 찾기
    
    n = len(citations) # 전체 논문의 개수
    isHindex = False; # H-Index를 아직 못 찾았다
    
    for i in range(n): # citations 배열을 하나씩 돌아보기 시작
    
    # 만약 citations의 값이 >= 전체 개수 - index 개수
    
    # 전체 개수 - index 개수 = h회 이상 인용된 논문의 개수 (오름차순 되어있어서)
    # citations[i] = h (h회 인용) 이므로
    # h회 이상 논문의 개수가 h 보다 적거나 같은 경우가 되면! isHindex를 찾은 것! 
        if (citations[i] >= n - i):
            isHindex = True # True로 바꾸고
            Hindex = n - i # H-index를 지정한다, 남은 논문의 개수가 상한이 됨
            # citations = [10, 10, 10] 인 경우를 생각해보면
            # citations[i]가 아닌 논문 개수 3이 상한임 (모두 3회 이상 인용됨)
            break # 반복문 빠져나오기
    if isHindex == False: # isHindex 가 여전히 False 라면 (H-Index를 찾지 못함)
        Hindex = 0 # 그런 경우는 0이 H-index이다
    
        
    return Hindex
