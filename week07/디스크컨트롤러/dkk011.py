# O(N log N)
import heapq

def solution(jobs):
    if not jobs:
        return 0

    # 요청 시점 기준 정렬 (역순으로 정렬하여 pop()을 O(1)에 수행)
    jobs.sort(key=lambda x: x[0], reverse=True)
    
    total_count = len(jobs)
    total_time = 0
    current_time = 0
    pq = []

    while jobs or pq:
        # 현재 시점 이전에 들어온 모든 작업을 대기 큐(우선순위 큐)에 추가
        while jobs and jobs[-1][0] <= current_time:
            req, dur = jobs.pop()
            heapq.heappush(pq, (dur, req))

        if pq:
            # 수행 시간이 가장 짧은 작업 처리
            dur, req = heapq.heappop(pq)
            current_time += dur
            total_time += current_time - req
        else:
            # 대기 큐가 비어 있으면 다음 작업 요청 시점으로 시간 점프
            current_time = jobs[-1][0]

    return total_time // total_count