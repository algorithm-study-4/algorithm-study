num = int(input())

memo = {
    1: 1,
    2: 1
}

for i in range(1, num+1):

    now_fibo = 0

    if i in memo:
        continue

    elif i not in memo:
        now_fibo = memo[i-1] + memo[i-2]
        memo[i] = now_fibo

answer = memo[num]
        

answer = answer % 1000000007
print(answer)


# 재귀함수 사용 시 메모리, 시간 제한 걸리는 문제 발생
# for 문으로 memo 채워가면서 해결해야 함