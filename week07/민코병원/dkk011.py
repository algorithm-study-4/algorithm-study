# O(Q log Q) -> O(N log N)
import sys, heapq
from collections import deque

input = sys.stdin.readline

Q = int(input())

# A동 큐
a_queue = deque()

# B동 우선순위 큐
# 정렬 기준: (-응급도, 나이, 접수순서(i), 이름)
b_heap = []

# 현재 진료 대기 중인 환자들을 확인하는 집합
waiting = set()

for i in range(Q):
    command = input().split()
    
    # 환자 접수 쿼리
    if command[0] == "1":
        _, name, age, degree = command
        age = int(age)
        degree = int(degree)
        
        # A동 큐에 접수 순서대로 이름만 저장
        a_queue.append(name)
        
        # B동 힙에 응급도 내림차순, 나이 오름차순, 접수 순서 오름차순
        heapq.heappush(b_heap, (-degree, age, i, name))
        
        # 대기 환자 명단에 추가
        waiting.add(name)
        
    # 진료 호출 쿼리
    else:
        _, type = command
        
        # A동 호출
        if type == "A":
            # 이미 B동 등에서 진료를 받아 대기 목록에 없는 환자는 큐에서 제거
            while a_queue and a_queue[0] not in waiting:
                a_queue.popleft()
            
            # 대기 중인 환자가 없는 경우
            if not a_queue:
                print("EMPTY")
                continue
            
            # 가장 먼저 접수한 대기 환자 호출
            name = a_queue.popleft()
            waiting.remove(name)  # 대기 상태 해제
            print(name)
            
        # B동 호출
        else:
            # 이미 A동 등에서 진료를 받아 대기 목록에 없는 환자는 힙에서 제거
            while b_heap and b_heap[0][3] not in waiting:
                heapq.heappop(b_heap)
            
            # 대기 중인 환자가 없는 경우
            if not b_heap:
                print("EMPTY")
                continue
            
            # 우선순위가 가장 높은 대기 환자 호출
            _, _, _, name = heapq.heappop(b_heap)
            waiting.remove(name)  # 대기 상태 해제
            print(name)
