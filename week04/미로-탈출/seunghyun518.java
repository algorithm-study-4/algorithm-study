import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int answer = 0;
        int[] dw = {1, -1, 0, 0};
        int[] dh = {0, 0, 1, -1};
        int[][] visited = new int[maps.length][maps[0].length()];
        int[] cur = new int[3];
        int[] start = new int[3];
        
        for(int i = 0; i < maps.length; i++){
            for(int j = 0; j< maps[0].length(); j++){
                if(maps[i].charAt(j) == 'S'){
                    start[0] = i;
                    start[1] = j;
                    start[2] = 0;
                }
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        visited[start[0]][start[1]] = 1;
        
        while(!q.isEmpty()){
            cur = q.poll();
            if(maps[cur[0]].charAt(cur[1]) == 'L'){
                break;
            }
            for(int i = 0; i < 4; i++){
                int nextw = cur[0] + dw[i];
                int nexth = cur[1] + dh[i];
                int nextd = cur[2] + 1;
                
                if(nextw >= 0 && nextw < maps.length && nexth >= 0 && nexth < maps[0].length()){
                    if(visited[nextw][nexth] == 0 && maps[nextw].charAt(nexth) != 'X'){
                        q.offer(new int[] {nextw, nexth, nextd});
                        visited[nextw][nexth] = 1;
                    }
                }
            }
        }
        
        if (maps[cur[0]].charAt(cur[1]) != 'L') {
           return -1;
        }
        
        q.clear();
        visited = new int[maps.length][maps[0].length()];
        q.offer(cur);
        
        while(!q.isEmpty()){
            cur = q.poll();
            answer += 1;
            if(maps[cur[0]].charAt(cur[1]) == 'E'){
                return cur[2];
            }
            for(int i = 0; i < 4; i++){
                int nextw = cur[0] + dw[i];
                int nexth = cur[1] + dh[i];
                int nextd = cur[2] + 1;
                
                if(nextw >= 0 && nextw < maps.length && nexth >= 0 && nexth < maps[0].length()){
                    if(visited[nextw][nexth] == 0 && maps[nextw].charAt(nexth) != 'X'){
                        q.offer(new int[] {nextw, nexth, nextd});
                        visited[nextw][nexth] = 1;
                    }
                }
            }
        }
        return -1;
    }
}

/*
s -> l / l -> e를 찾는다. 두 번의 bfs

1. 시작점이 (0,0)이 아닐 수 있다. (시작점을 찾아서 start로 두고 시작)
2. 모든 통로는 여러번 지날 수 있다. (두 번의 bfs사이에 visited 초기화)
3. s -> l을 못찾을 수 있고, l -> e를 못찾을 수 있다
*/