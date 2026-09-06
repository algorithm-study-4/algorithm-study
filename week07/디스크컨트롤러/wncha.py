import heapq

def solution(jobs):
    n = len(jobs)
    
    # [1단계] 작업들을 '요청 시각(s)' 기준으로 오름차순 정렬
    # -> 시뮬레이션 도중 '아직 확인 안 한 작업들' 중
    # 가장 앞쪽부터 순서대로만 확인하면 되므로
    # 포인터(idx) 하나로 효율적으로 관리 가능
    jobs.sort(key=lambda job: job[0])

    # [2단계] 대기 큐(우선순위 큐) 선언
    heap = []

    # 현재 시각
    current_time = 0
    # jobs 배열에서 '아직 대기 큐에 넣지 않은 작업'을 가리키는 포인터
    idx = 0
    # 모든 작업의 반환시간의 총합을 누적, long으로 오버플로우 방지
    total_turnaround = 0
    # 지금까지 처리 완료한 작업 수
    done = 0
    
    # [3단계] 모든 작업을 완료할 때까지 시뮬레이션 반복
    while done < n:
        
        # 3-1. 현재 시각까지 '이미 요청된' 모든 작업을 대기 큐에 추가
        # -> [1단계]에서 정렬해두어서 O(1)
        while idx < n and jobs[idx][0] <= current_time:
            # heapq에는 [소요시간, 요청시각] 순서로 넣어서 소요시간 기준 최소 힙이 되도록 함
            heapq.heappush(heap, [jobs[idx][1], jobs[idx][0]])
            idx += 1
        
        # 3-2. 만약 대기 큐가 비어있다면 (처리할 작업이 없다면)
        # 다음 작업이 요청되는 시점까지 시간을 곧바로 건너뛰기
        if not heap:
            current_time = jobs[idx][0]
            continue
        
        # 3-3. 대기 큐에서 우선순위가 가장 높은 작업(소요시간이 가장 짧은 것) 꺼내 처리
        duration, start = heapq.heappop(heap)
        
        # 작업 수행
        current_time += duration
        
        # 반환시간 = 종료시각 - 요청시각
        total_turnaround += (current_time - start)
        
        done += 1  # 작업 하나 완료
    
    # 모든 작업의 반환시간 평균의 정수 부분 반환
    return total_turnaround // n