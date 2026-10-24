import sys

# 입력 받기
data = sys.stdin.read().split()

n = int(data[0])
a = list(map(int, data[1:1+n]))

# 누적합
# S[k] = a[0] + a[1] + ... + a[k-1], S[0] = 0
# 구간 a[i...j]의 합 = S[j+1] - S[i]

# 딕셔너리 준비
# count[값] = 지금까지 그 누적합 값이 몇 번 나왔는지 체그한다

# 처음에 {0: 1}로 시작하는 이유: S[0] = 0이 이미 한 번 있다고 쳐야 맨 앞에서 시작하는 구간을 셀 수 있다
count = {0: 1}

s = 0 # 현재까지의 누적합
answer = 0 # 합이 0개인 구간의 개수

# 배열을 왼쪽부터 한 번만 훑기(O(n))

for x in a:
    s += x # 누적합 갱신: 현재 원소까지 더하기

    # 지금 누적합 s와 같은 값이 이전에 몇 번 나왔는지 확인하기
    answer += count.get(s, 0) # s가 딕셔너리에 없으면 0을 돌려준다
    
    count[s] = count.get(s, 0) + 1 # 현재 누적합을 기록에 추가하기

print(answer)


