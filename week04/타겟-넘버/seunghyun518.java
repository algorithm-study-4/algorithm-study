import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        Queue<int[]> q = new LinkedList<>();
        
        q.offer(new int[] {0,0});
        
        // BFS(인덱스 + 현재 값을 다음으로 넘겨줌)
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ind = cur[0];
            int val = cur[1];

            // 탈출 검사            
            if(ind == numbers.length){
                if(val == target){
                    answer += 1;
                }
            }
            else{
                q.offer(new int[] {ind + 1,val + numbers[ind]});
                q.offer(new int[] {ind + 1,val - numbers[ind]}); 
            }   
        }
        return answer;
    }
}