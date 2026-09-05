"""
[문제
일반 병동과 응급 병동이 있을 때 우선순위에 따라 진료를 받게 되는 환자의 이름을 출력한다.
이때 대기중인 환자가 없으면 EMPTY를 출력한다.

[풀이]
- 큐(deque)는 먼저 들어온 사람 처리, 우선순위큐(heapq)는 응급도 -> 나이 -> 입력순서 순으로 처리할 때 사용한다.
- 같은 사람이 두 자료구조에 동시에 들어가므로, 둘 중 한쪽에서 처리된 사람은 removed에 기록해 다른 쪽에서 다시 쓰지 않도록 처리한다.

[시간복잡도]
- 각 원소는 큐/힙에서 최대 한 번씩 제거되므로 전체 시간복잡도는 O(QlogQ) (Q = 진료 호출 수) 이다.

"""

from collections import deque
import heapq

query = int(input())

# A동: 접수 순서대로 진료하므로 큐 사용
q = deque()

# B동: 응급도, 나이, 접수 순서 기준 우선 진료이므로 우선순위 큐 사용
pq = []

# 이미 A동 또는 B동에서 진료받은 환자 기록
removed = set()

ans = []

for i in range(query):
    curr = input().split()

    # 환자 접수
    if curr[0] == '1':
        name = curr[1]
        age = int(curr[2])
        emergency = int(curr[3])

        # A동에서는 접수 순서대로 진료
        q.append(name)

        # B동 우선순위
        # 1. 응급도가 높은 환자
        # 2. 나이가 어린 환자
        # 3. 먼저 접수한 환자
        heapq.heappush(pq, (-emergency, age, i, name)) # 파이썬에서 heapq는 최소 힙이므로 최대힙을 만들고 싶으면 -로 넣는 방법을 사용. 따라서 응급도 emergency는 음수로 저장

    # 진료 호출
    else:
        command = curr[1]

        # 대기 중인 환자가 없는 경우를 판단하기 위함
        is_empty = True

        # A동 호출
        if command == 'A':

            # 이미 다른 병동에서 진료받은 환자는 건너뜀
            while q:
                name = q.popleft()

                if name not in removed:
                    removed.add(name)
                    ans.append(name)
                    is_empty = False
                    break

        # B동 호출
        else:

            # 우선순위가 가장 높은 환자부터 확인
            # 이미 다른 병동에서 진료받은 환자는 건너뜀
            while pq:
                _, _, _, name = heapq.heappop(pq)

                if name not in removed:
                    removed.add(name)
                    ans.append(name)
                    is_empty = False
                    break

        # 대기 중인 환자가 없는 경우
        if is_empty:
            ans.append("EMPTY")

print("\n".join(ans))