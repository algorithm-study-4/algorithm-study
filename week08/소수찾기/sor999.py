def solution(numbers):
    num_set = set()
    nums = list(numbers)

    n = len(nums)
    arr = [0] * n
    visit = [False] * n

    def dfs(depth, length):
        if depth == length:
            num_set.add(int("".join(arr[:length])))
            return

        for i in range(n):
            if not visit[i]:
                visit[i] = True
                arr[depth] = nums[i]
                dfs(depth + 1, length)
                visit[i] = False

    for length in range(1, n+1):
        dfs(0, length)

    ans = 0

    for v in num_set:
        if v <= 1:
            continue
        is_prime = True
        i = 2

        while i * i <= v:
            if v % i == 0:
                is_prime = False
                break
            i += 1

        if is_prime:
            ans += 1

    return ans