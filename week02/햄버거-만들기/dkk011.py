# ingredient의 길이 만큼 실행 -> O(N)

def solution(ingredient):
    answer = 0  # 햄버거 포장 개수
    stack = []

    # 재료를 순서대로 스택에 쌓음
    for i in ingredient:
        stack.append(i)

        # 스택의 마지막 4개가 햄버거 재료 순서와 일치하면
        if stack[-4:] == [1, 2, 3, 1]:
            del stack[-4:]  # 사용한 재료 제거
            answer += 1     # 햄버거 포장 개수 증가

    return answer