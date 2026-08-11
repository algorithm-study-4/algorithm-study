# BFS 코드

from collections import deque

N = int(input())
T = int(input())

# 입력 받기 - 따로 가공하지 않고 있는 그대로 받기
arr = []
for i in range(T):
  arr.append(list(map(int, input().split())))

start = 1

# BFS 구현하기

def bfs(arr, start):
  visited = [start]
  q = deque([start])

  
  while q:
    idx = q.popleft()
    
    for j in arr:
      if j[0] == idx and j[1] not in visited:
        visited.append(j[1])
        q.append(j[1])
      elif j[1] == idx and j[0] not in visited:
        visited.append(j[0])
        q.append(j[0])
  
  return visited


print(len(bfs(arr, start)) - 1)





# # DFS 코드

# N = int(input())
# T = int(input())

# arr = []
# for i in range(T):
#   arr.append(list(map(int, input().split())))

# start = 1

# def dfs(arr, start):
#   visited = [start]
#   stack = [start]

#   while stack:
#     idx = stack.pop()

#     for j in arr:
#       if j[0] == idx and j[1] not in visited:
#         visited.append(j[1])
#         stack.append(j[1])
#       elif j[1] == idx and j[0] not in visited:
#         visited.append(j[0])
#         stack.append(j[0])
    
#   return visited

# print(len(dfs(arr, start)) - 1)