import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<>();
        int totalTime = 0;
        int weightSum = 0;
        int pointer = 0;

        for (int i = 0; i < bridge_length; i++) {
            q.offer(0);
        }

        while (pointer < truck_weights.length || weightSum > 0) {
            totalTime++;
            weightSum -= q.poll();

            if (pointer < truck_weights.length
                    && weightSum + truck_weights[pointer] <= weight) {
                q.offer(truck_weights[pointer]);
                weightSum += truck_weights[pointer];
                pointer++;
            } else {
                q.offer(0);
            }
        }
        return totalTime;
    }
}