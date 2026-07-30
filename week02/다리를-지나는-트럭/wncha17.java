import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;

        // 다리 길이만큼 0으로 채워진 큐 생성
        Deque<Integer> bridge = new ArrayDeque<>();
        for (int i = 0; i < bridge_length; i++) {
            bridge.addLast(0);
        }

        // 대기 트럭 큐 생성
        Deque<Integer> waiting = new ArrayDeque<>();
        for (int w : truck_weights) {
            waiting.addLast(w);
        }

        int currWeight = 0; // 현재 다리 위 트럭들의 총 무게

        while (!waiting.isEmpty()) {
            time++;

            // 다리에서 나가는 트럭의 무게를 현재 무게에서 뺌
            currWeight -= bridge.pollFirst();

            // 새로운 트럭이 다리에 진입할 수 있는지 확인
            if (currWeight + waiting.peekFirst() <= weight) {
                int truck = waiting.pollFirst();
                bridge.addLast(truck);
                currWeight += truck;
            } else {
                // 진입 불가 시 0을 넣어 다리 길이 유지
                bridge.addLast(0);
            }
        }

        // 마지막 트럭이 다리에 올라간 순간 반복문이 종료되므로
        // 다리 길이(건너는 시간)만큼 더해줌
        return time + bridge_length;
    }
}