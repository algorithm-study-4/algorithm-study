import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>(); // 다리 위에 있는 트럭들의 무게
        int time = 0;
        int currentWeight = 0; // 다리 위 트럭들의 총 무게
        int index = 0; // 다음에 올릴 트럭의 인덱스
        int n = truck_weights.length;
        
        // 다리를 bridge_length 길이만큼 0(빈 칸)으로 채워서 시작
        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }
        
        while (index < n) {
            time++;
            
            // 맨 앞 트럭이 다리를 다 건넜으므로 제거
            currentWeight -= bridge.poll();
            
            // 다음 트럭을 올릴 수 있는지 확인
            if (currentWeight + truck_weights[index] <= weight) {
                bridge.add(truck_weights[index]);
                currentWeight += truck_weights[index];
                index++;
            } else {
                bridge.add(0); // 못 올리면 빈 칸을 채움
            }
        }
        
        // 다리 위에 남아있는 트럭들이 모두 건널 때까지 시간 추가
        time += bridge_length;
        
        return time;
    }
}