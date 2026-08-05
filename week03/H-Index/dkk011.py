# O(n)
def solution(citations):
    n = len(citations)

    # count[i] = i번 인용된 논문의 개수
    # H-index는 최대 논문 개수(n)이므로 n+1 크기로 생성
    count = [0] * (n + 1)

    for c in citations:
        # 인용 횟수가 논문 개수보다 많아도 H-index에는 n 이상이 의미가 없음
        # 그래서 n 이상은 모두 count[n]에 모음
        if c >= n:
            count[n] += 1
        else:
            # c번 인용된 논문 개수 증가
            count[c] += 1

    # 현재까지 확인한 h번 이상 인용된 논문의 개수
    total = 0

    # 가장 큰 H-index 후보부터 확인
    for h in range(n, -1, -1):
        
        # h번 인용된 논문 개수 누적
        total += count[h]

        # h번 이상 인용된 논문이 h개 이상 있으면
        # 현재 h가 H-index
        if total >= h:
            return h