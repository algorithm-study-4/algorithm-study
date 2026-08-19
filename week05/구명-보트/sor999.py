"""
O(nlogn)
- 최대 2명
- 무게 제한 Limit

사람 구출을 위해 필요한 구명보트의 최솟값
무거운 사람 + 가벼운 사람 -> 태우기
아니면 -> 무거운 사람만 태우기
"""
def solution(people, limit):
    ans = 0
    
    people.sort(reverse=True)
    
    n = len(people)
    
    s, e = 0, n - 1
    
    while s <= e:
        if people[s] + people[e] <= limit:
           e -= 1
        s += 1
        ans += 1
    return ans