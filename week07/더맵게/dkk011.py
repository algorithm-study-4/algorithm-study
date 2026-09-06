# O(N log N)
import heapq

def solution(scoville, K):
    answer = 0
    # 모든 음식의 스코빌 지수를 최소 힙으로 만들어서 오름차순 정렬 상태 유지
    heapq.heapify(scoville)
    
    # 음식을 섞으려면 최소 2개 이상 남아 있어야 하므로 루프 진행
    while len(scoville) > 1:
        # 가장 맵지 않은 음식과 두 번째로 맵지 않은 음식 추출
        f1 = heapq.heappop(scoville)
        f2 = heapq.heappop(scoville)
        
        # 가장 낮은 스코빌 지수가 이미 K 이상이면 모든 음식이 조건 만족
        if (f1 >= K):
            return answer
        
        new = f1 + f2 * 2
        
        # 새로 섞은 음식을 힙에 추가
        heapq.heappush(scoville, new)
        
        # 섞은 횟수 증가
        answer += 1
        
    # 음식이 1개 남았을 때 해당 음식이 K 이상이면 섞은 횟수 반환, 불가능하면 -1 반환
    return answer if scoville[0] >= K else -1
