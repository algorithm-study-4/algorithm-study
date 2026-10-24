# 시간복잡도: O(N)  (N = discount 길이)
# 윈도우를 한 칸씩 옮기면서 빠지는 상품 1개, 들어오는 상품 1개만 갱신
# 딕셔너리 비교는 키가 최대 10개라 O(1)로 볼 수 있음

from collections import Counter

def solution(want, number, discount):
    want_cnt = dict(zip(want, number))  # 원하는 제품: 원하는 수량
    window = Counter(discount[:10])     # 첫 10일 동안 할인하는 제품별 개수

    answer = int(window == want_cnt)    # 첫날 가입 시 조건 만족 여부 (True = 1, False = 0)

    # 회원가입 날짜를 하루씩 뒤로 미루면서 10일 구간 이동
    for i in range(10, len(discount)):
        out_item = discount[i - 10]     # 구간에서 빠지는 제품
        window[out_item] -= 1

        # Counter는 개수가 0이 돼도 키를 자동으로 지우지 않음
        # want_cnt는 일반 dict라서 {'apple': 0, 'banana': 2}와 {'banana': 2}를 비교하면 다르다고 판단됨
        # 따라서 개수가 0이 된 제품은 직접 삭제해야 딕셔너리 비교가 정확함
        if window[out_item] == 0:
            del window[out_item]

        window[discount[i]] += 1        # 구간에 새로 들어오는 제품

        if window == want_cnt:          # 원하는 제품과 수량이 일치하면 회원가입 가능
            answer += 1

    return answer