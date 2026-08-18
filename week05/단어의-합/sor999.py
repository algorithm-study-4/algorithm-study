"""
방법1. 완전탐색 -> 시간초과. 테스트케이스 9/11 통과
10(단어 개수) * 8(단어길이)  * 10!

O(k! * (k + n*l))

k!: 순열 개수 (리프 노드 수)
k: 리프에서 d 딕셔너리 만드는 비용
n * l: 리프에서 모든 단어 채점하는 비용
"""
import sys
input = sys.stdin.readline

n = int(input())

words = [input().strip() for _ in range(n)]


alpha_set = set()
for word in words:
    alpha_set.update(list(word))

alpha = list(alpha_set)

alpha_cnt = len(alpha)

nums = list(range(9 - alpha_cnt + 1, 10)) # 9 8 7 6 5 4

visited = [False] * alpha_cnt

arr = []
max_v = 0

def dfs(depth):
    global max_v
    if depth == alpha_cnt:
        d = {}
        total = 0
        for i in range(alpha_cnt):
            key = alpha[i]
            value = arr[i]

            d[key] = value

        for word in words:
            l = len(word)
            for j in range(l):
                total += d[word[j]] * (10 ** (l - j - 1))
        max_v = max(max_v, total)

        return

    for i in range(alpha_cnt):
        if visited[i]:
            continue
        visited[i] = True
        arr.append(nums[i])
        dfs(depth + 1)
        visited[i] = False
        arr.pop()

dfs(0)
print(max_v)

"""
방법2. 가중치 그리디
O(n * l)
"""
from collections import defaultdict
import sys
input = sys.stdin.readline

n = int(input())

words = [input().strip() for _ in range(n)]

max_v = 0

mul = defaultdict(int)

for word in words: # ABC
    l = len(word)
    for i in range(l):
        c = word[i]
        mul[c] += 10 ** (l - i - 1)

total = 0
num = 9

for v in sorted(mul.values(), reverse=True): # 가장 큰 수부터 사용
    total += num * v
    num -= 1

print(total)