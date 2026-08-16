# O(N log N)
def solution(people, limit):
    people.sort()  # 몸무게를 오름차순으로 정렬, O(N log N)

    # 가장 가벼운 사람과 가장 무거운 사람을 가리키는 포인터
    start, end = 0, len(people) - 1

    count = 0  # 필요한 보트의 개수

    while start <= end:     # O(N)
        # 가장 가벼운 사람 + 가장 무거운 사람이
        # 제한 무게 이하라면 두 사람을 함께 태울 수 있음
        if people[start] + people[end] <= limit:
            start += 1  # 가장 가벼운 사람도 보트에 태웠으므로 다음 사람으로 이동

        # 가장 무거운 사람은 항상 이번 보트에 태움
        end -= 1

        # 보트 하나 사용
        count += 1

    return count