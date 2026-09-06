# O(N log N)
import sys, heapq

input = sys.stdin.readline
N = int(input().rstrip())

# Python의 heapq는 최소 힙이므로
# 값의 부호를 바꿔 저장하면 최대 힙이 된다
max_queue = []

for _ in range(N):
    command, data = input().rstrip().split()
    data = int(data)

    # push A: 정수 A의 부호를 바꿔 최대 힙에 추가
    if command == "push":
        heapq.heappush(max_queue, -data)

    # pop B: B개만큼 최댓값을 꺼내 출력하고 개행
    if command == "pop":
        for _ in range(data):
            # 꺼낸 값에 마이너스를 붙여 원래 부호로 바꿔 출력
            print(-heapq.heappop(max_queue), end=" ")
        print()  # pop 완료 후 한 줄 개행

    # add C: 최댓값 1개 꺼내 C를 더한 뒤 힙에 추가
    if command == "add":
        temp = -heapq.heappop(max_queue)
        # 꺼낸 데이터에 C를 더하고 부호 바꿔서 힙에 추가
        heapq.heappush(max_queue, -(temp + data))