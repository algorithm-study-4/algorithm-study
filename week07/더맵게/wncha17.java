import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.add(s);
        }
        
        int count = 0;
        
        // 가장 맵지 않은 음식이 K 이상이 될 때까지 반복
        while (pq.peek() < K) {
            // 더 이상 섞을 음식이 2개 미만이면, 모든 음식을 K 이상으로 만들 수 없음
            if (pq.size() < 2) {
                return -1;
            }
            
            int first = pq.poll();  // 가장 맵지 않은 음식
            int second = pq.poll(); // 두 번째로 맵지 않은 음식
            
            int mixed = first + second * 2;
            pq.add(mixed);
            
            count++;
        }
        
        return count;
    }
}