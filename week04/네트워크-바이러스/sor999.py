"""
O(n + m)

방법 1-1. dfs - 반환 값 이용
"""
import sys
from collections import defaultdict

input = sys.stdin.readline

n = int(input())
m = int(input())
g = defaultdict(list)

for _ in range(m):
    a, b = map(int, input().split())
    g[a].append(b)
    g[b].append(a)

visited = [False] * (n + 1)

def dfs(curr):
    visited[curr] = True
    cnt = 1

    for nxt in g[curr]:
        if visited[nxt]:
            continue
        cnt += dfs(nxt)

    return cnt

print(dfs(1) - 1)


"""
O(n + m)

방법 1-2. dfs - 전역 변수 이용
"""
import sys
from collections import defaultdict

input = sys.stdin.readline

n = int(input())
m = int(input())
g = defaultdict(list)

for _ in range(m):
    a, b = map(int, input().split())
    g[a].append(b)
    g[b].append(a)

visited = [False] * (n + 1)

cnt = 0

def dfs(curr):
    global cnt

    visited[curr] = True
    cnt += 1

    for nxt in g[curr]:
        if visited[nxt]:
            continue
        dfs(nxt)
dfs(1)

print(cnt - 1)


"""
O(n + m)

방법 2. bfs
"""
import sys
from collections import defaultdict, deque

input = sys.stdin.readline

n = int(input())
m = int(input())
g = defaultdict(list)

for _ in range(m):
    a, b = map(int, input().split())
    g[a].append(b)
    g[b].append(a)

visited = [False] * (n + 1)

q = deque()

q.append(1)
visited[1] = True

cnt = 0

while q:
    curr = q.popleft()

    for nxt in g[curr]:
        if visited[nxt]:
            continue
        visited[nxt] = True
        q.append(nxt)

        cnt += 1

print(cnt)

"""
O((n + m)logn)

방법 3. union-find
"""
import sys

input = sys.stdin.readline

n = int(input())
m = int(input())

root = [i for i in range(n + 1)]

def union(a, b):
    a = find(a)
    b = find(b)

    if a != b:
        root[a] = b

def find(a):
    if root[a] == a:
        return a

    root[a] = find(root[a]) # 경로 압축

    return root[a]

for _ in range(m):
    a, b = map(int, input().split())

    union(a, b)

std = find(root[1])

cnt = 0
for i in range(2, n + 1):
    if find(root[i]) == std:
        cnt += 1

print(cnt)

