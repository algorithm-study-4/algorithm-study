import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        // java 우선순위 큐 라이브러리(기본 오름차순)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // priority queue에 데이터 넣기
        for(int i = 0; i < scoville.length; i++){
            pq.offer(scoville[i]);
        }
        
        // 조건(제일 안 매운 음식이 k보다 작으면 실행)
        while(pq.peek() < K){
            // 만약 음식이 하나 밖에 안남았다면 -> 더 이상 섞을 수 없음(-1 반환)
            if(pq.size() <= 1){
                return -1;
            }
            // 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2) 대로 실행
            int num1 = pq.poll();
            int num2 = pq.poll();
            pq.offer(num1 + (2 * num2));
            answer++;
        }
        
        // 몇 번 섞었는지 반환
        return answer;
    }
}