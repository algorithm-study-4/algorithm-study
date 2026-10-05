# O(N! × √M)
# N: 숫자의 개수, M: 만들어진 숫자의 크기
def solution(numbers):
    # set 사용해서 중복 없이 소수 저장
    answer = set()

    # 각 숫자 사용 여부 저장
    visited = [False] * len(numbers)

    def dfs(current):
        if current:
            num = int(current)

            # 소수인지 확인
            if is_prime(num):
                answer.add(num)

        # 현재 DFS 깊이에서 같은 숫자 중복 선택 방지
        used = set()

        for i in range(len(numbers)):
            if visited[i]:
                continue

            if numbers[i] in used:
                continue

            used.add(numbers[i])
            visited[i] = True

            # 현재 숫자 뒤에 선택한 숫자 붙여서 다음 탐색
            dfs(current + numbers[i])

            # 숫자 다시 사용할 수 있게 복구
            visited[i] = False

    dfs("")
    
    return len(answer)

# 소수 판별
def is_prime(n):
    if n < 2:
        return False
    
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            return False
        
    return True