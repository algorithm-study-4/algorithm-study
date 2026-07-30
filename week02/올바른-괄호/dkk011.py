# s의 길이 만큼 실행 -> O(N)

def solution(s):
    stack = []  # "("를 저장할 스택
    
    for i in s:
        if i == "(":
            stack.append(i)     # "(" 추가
        else:                   # ")"가 나온 경우
            if not stack:       # 스택에 "("가 없으면 실패
                return False
            stack.pop()         # 스택에서 "(" 제거
    
    # 모두 확인 후 스택에 "("가 남으면 실패, 아니면 성공
    return not stack