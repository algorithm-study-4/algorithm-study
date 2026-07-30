import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        // Linked를 만들지만 Queue처럼만 사용.
        Queue<Integer> bridge = new LinkedList<>();

        // 처음에는 다리가 비어있으므로 0으로 채운다.
        for (int i = 0; i < bridge_length; i++) { // 다리 길이만큼 반복
            bridge.offer(0);
        }

        int time = 0;
        int currentWeight = 0;
        int truckIndex = 0; // 배열x -> 다음 차례의 트럭이 몇번째인지 - 가리키는 거

        while (!bridge.isEmpty()) { // 다리 위 트럭이 모두 나갈 때까지

            time++;

            // 다리 맨 앞(도착한 트럭) 제거
            currentWeight -= bridge.poll();

            // 아직 대기 중인 트럭이 있다면
            if (truckIndex < truck_weights.length) {

                // 다음 트럭을 올릴 수 있는 경우
                if (currentWeight + truck_weights[truckIndex] <= weight) {
                    bridge.offer(truck_weights[truckIndex]); // 문제 없으면 다음 트럭 올림
                    currentWeight += truck_weights[truckIndex]; // 올렸으면 무게 증가
                    truckIndex++;
                } else {
                    // 못 올라가면 빈칸(0)을 넣어 시간을 흐르게 한다.
                    bridge.offer(0);
                }
            }
        }

        return time;
    }
}