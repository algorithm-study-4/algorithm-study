def solution(numbers):
    digits = list(numbers) # "17"의 경우 ['1', '7']
    n = len(digits) # 숫자(종이)의 개수
    visited = [False] * n # 방문 여부 체크
    found = set() # 만들어진 최종 숫자 모아두는 set (중복 제거 위해 set)
    
    def dfs(path): # dfs 작성 - path: 지금까지 순서대로 골라서 들고 있는 종이 담아두는 리스트
        if path: # path가 비어있지 않으면? = 종이 1자이라도 고름 -> 이미 유효 숫자 하나 완성됨
            found.add(int(''.join(path))) # path에 담긴 숫자 순서대로 이어붙여서 문자열로 만들고 숫자로 변환, found 셋에 저장하기
            
        for i in range(n): # 0 ~ n-1번 종이까지 다음에 고를 종이로 하나씩 시도
            if not visited[i]: # i번 종이 안 썼으면
                visited[i] = True # True로 체크
                path.append(digits[i]) # path에 i번 종이 넣기
                
                dfs(path) # i번 고른 상태에서 다른 종이 하나 더 고르는 경우 모두 탐색
                path.pop() # 방금 넣은 종이 빼기
                visited[i] = False # i번 종이 안 쓴 상태로 되돌리기
                
    dfs([]) # 빈 바구니로 시작하기
    
    # 다 완료 후에 소수인 것만 골라내기
    def is_prime(num):
        
        if num < 2:
            return False # 0, 1은 탈락
        
        for i in range(2, int(num ** 0.5) + 1):
            if num % i == 0:
                return False
            
        return True
    
    answer = 0
    
    for num in found:
        if is_prime(num):
            answer += 1 # 소수일 경우 개수 세기
    return answer