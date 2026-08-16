# answer에 최대 N개의 숫자가 저장될 수 있으므로 O(N)
def solution(number, k):
    answer = []

    for i in number:

        # 현재 숫자(i)가 스택의 마지막 숫자보다 클 때
        # 작은 숫자를 제거해야 더 큰 수를 만들 수 있음
        while answer and answer[-1] < i and k > 0:
            answer.pop()  # 스택의 마지막 숫자 제거
            k -= 1        # 제거해야 할 개수 1 감소

        # 현재 숫자를 스택에 추가
        answer.append(i)

    # 아직 제거해야 할 숫자가 남아 있으면 뒤쪽 숫자 제거
    if k > 0:
        answer = answer[:-k]

    # 리스트에 저장된 문자들을 하나의 문자열로 합쳐 반환
    return "".join(answer)
