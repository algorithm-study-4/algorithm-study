def solution(people, limit):
    answer = 0
    
    p = len(people)
    people = sorted(people)
    
    print(f"people : {people}")
    min = 0
    max = p-1
    
    # sorted 하고 min = 0, max = len(people) - 1 로 설정한 다음에
    # people[min] + people[max] >= limit 라면 -> people[max] 는 보내버리고
    # max 를 하나씩 줄여가면서 해보기
    
    while min <= max:
        if min == max:
            answer += 1
            break
        elif people[min] + people[max] > limit:
            max -= 1
            answer += 1
        else:
            min += 1
            max -= 1
            answer += 1
            
    return answer