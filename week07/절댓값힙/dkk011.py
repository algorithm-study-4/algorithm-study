'''
O(N log N)
힙 삽입, 삭제 연산 모두 O(log N)
N개 쿼리에 대해 실행하므로 전체 시간 복잡도는 O(N log N)
100,000개의 쿼리를 제한 시간 안에 처리 가능하다
'''

import sys, heapq

# N이 최대 100,000이므로 빠른 입출력을 위해 사용
input = sys.stdin.readline

# 쿼리의 개수 N 입력
N = int(input().rstrip())

# 절대값 힙으로 사용할 리스트 선언
abs_heap = []

for _ in range(N):
    X = int(input().rstrip())
    
    if X != 0:
        # 튜플을 활용한 우선순위 정렬
        # Python의 heapq는 최소 힙 기반이므로 튜플 (A, B) 삽입 시
        # 1순위: 첫 번째 원소(A = abs(X))를 기준으로 오름차순 정렬 (절댓값이 작은 순)
        # 2순위: 두 번째 원소(B = X)를 기준으로 오름차순 정렬 (음수가 양수보다 작으므로 음수 우선)
        heapq.heappush(abs_heap, (abs(X), X))
    else:
        # X가 0일 때 삭제 및 출력
        if len(abs_heap) == 0:
            # 힙이 비어있으면 문제 조건에 따라 0 출력
            print(0)
        else:
            # heappop은 힙에서 우선순위가 가장 높은 (abs(X), X) 튜플을 꺼내 반환
            # 인덱스 [1]을 통해 원래 값 X만 출력
            print(heapq.heappop(abs_heap)[1])
