import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        List<Integer> temp = new ArrayList<>();
        int[][] visited = new int[maps.length][maps[0].length()];
        Queue<int[]> q = new LinkedList<>();
        int[] dw = {0, 0, 1, -1};
        int[] dh = {1, -1, 0, 0};
        
        // 모든 섬에 대해 BFS 실행
        for(int i = 0; i < maps.length; i++){
            for(int j = 0; j < maps[0].length(); j++){
                int answer = 0;
                if(visited[i][j] == 0 && maps[i].charAt(j) != 'X'){ // 근데 가 본적이 있거나 섬이 아니라면 안한다
                    answer += maps[i].charAt(j) - '0';
                    int[] cur = {i, j};
                    
                    // BFS
                    q.offer(cur);
                    visited[i][j] = 1;                    
                    
                    while(!q.isEmpty()){
                        cur = q.poll();
                        for(int k = 0; k < 4; k++){
                            int nextw = cur[0] + dw[k];
                            int nexth = cur[1] + dh[k];
                            
                            if(nextw >= 0 && nextw < maps.length && nexth >= 0 && nexth < maps[0].length()){
                                if(visited[nextw][nexth] == 0 && maps[nextw].charAt(nexth) != 'X'){
                                   q.offer(new int[]{nextw, nexth});
                                    visited[nextw][nexth] = 1;
                                    answer += maps[nextw].charAt(nexth) - '0';
                                }    
                            }
                        }
                    }
                    temp.add(answer);
                }
            }
        }
        if(temp.isEmpty()){
            return new int[]{-1};
        }
        
        int [] answers = new int [temp.size()];
        for(int i = 0; i < temp.size(); i++){
            answers[i] = temp.get(i);
        }
        
        Arrays.sort(answers);
        
        return answers;
    }
}