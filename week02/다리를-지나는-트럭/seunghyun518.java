import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int i = 0;
        Queue<int []> q = new LinkedList<>(); // 다리
        
        while(i < truck_weights.length){
            answer += 1; // 시간
            if(!q.isEmpty()){ // 다리 위에 트럭이 있다면
                int[] arr = q.peek(); 
                if(arr[1] == answer - bridge_length){ // 다리 위의 트럭이 나갈 시간이 됐다면
                    q.poll(); // 나감
                }
            }
            int sum = 0; // 다리 위 트럭의 총 무게
            for(int[] arr: q){
                sum += arr[0];
            }

            // 다리 위 트럭의 총 무게 + 다음 트럭의 무게 <= 다리가 견딜 수 있는 무게
            if(truck_weights[i] + sum <= weight){ 
                q.offer(new int[]{truck_weights[i], answer}); // 다음 트럭 다리 위 진입
                i++; // 트럭이 한 대 올라갔으니 다음 트럭으로
            }
        }

        return answer + bridge_length; // 마지막 트럭이 다리를 건너는 시간 더해주기
    }
}