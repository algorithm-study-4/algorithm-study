import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        int[] dw = {1, -1, 0, 0};
        int[] dh = {0, 0, 1, -1};
        int[][] visited = new int[maps.length][maps[0].length];
        int mapsizew = maps.length;
        int mapsizeh = maps[0].length;
        Queue<int[]> q = new LinkedList<>();
        int count = 1;
        
        visited[0][0] = 1;
        q.offer(new int[]{0, 0, count});
        while(!q.isEmpty()){
            int[] arr = q.poll();
            if(arr[0] == mapsizew - 1 && arr[1] == mapsizeh - 1){
                answer = arr[2];
            }
            
            for(int i = 0; i < 4 ; i++){
                int nextw = arr[0] + dw[i];
                int nexth = arr[1] + dh[i];
                int nextc = arr[2] + 1;
                if(0 <= nextw && nextw < mapsizew && 0 <= nexth && nexth < mapsizeh && visited[nextw][nexth] == 0 && maps[nextw][nexth] == 1){
                    visited[nextw][nexth] = 1;
                    q.offer(new int[]{nextw, nexth, nextc});
                }
            }
        }
        
        return answer == 0 ? -1 : answer;
    }
}