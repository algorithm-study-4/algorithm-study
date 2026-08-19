# O(N log N)
def solution(n, lost, reserve):
    # 여벌 체육복이 있지만 도난당하지 않은 학생
    rset = set(reserve) - set(lost)     # O(N)

    # 체육복을 잃어버렸지만 여벌 체육복도 없는 학생
    lset = set(lost) - set(reserve)     # O(N)

    for r in sorted(rset):      # 정렬: O(N log N), 순회: O(N)

        # 바로 앞 번호 학생이 체육복을 잃어버렸다면
        if r - 1 in lset:
            # 체육복을 빌려주고, 해당 학생을 빌릴 필요가 없는 학생으로 제거
            lset.remove(r - 1)

        # 바로 뒤 번호 학생 확인
        elif r + 1 in lset:
            # 뒤 학생에게 체육복을 빌려줌
            lset.remove(r + 1)

    # 전체 학생 수에서 체육복을 빌리지 못한 학생 수를 뺌
    return n - len(lset)