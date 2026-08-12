import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int[] visited = new int[n];
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 0; i < n; i++){
            if(visited[i] == 1){
                continue;
            }
            
            q.offer(i);
            visited[i] = 1;
            
            while(!q.isEmpty()){
                int cur = q.poll();
                for(int j = 0; j < computers[cur].length; j++){
                    if(computers[cur][j] == 0 || visited[j] == 1){
                        continue;
                    }
                    
                    q.offer(j);
                    visited[j] = 1;
                }
            }
            answer += 1;
        }
        return answer;
    }
}