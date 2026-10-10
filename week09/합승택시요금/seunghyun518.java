import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {

        // dist 2차원 배열 초기화
        int answer = 1000000000;
        int[][] dist = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j){
                    dist[i][j] = 100000000;
                }
            }
        }

        // 연결된 도로 채우기
        for(int[] road : fares){
            dist[road[0] - 1][road[1] - 1] = road[2];
            dist[road[1] - 1][road[0] - 1] = road[2];
        }

        // floyd 알고리즘(k -> j와 k -> i -> j를 비교)
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    dist[k][j] = Math.min(dist[k][j], dist[k][i] + dist[i][j]);
                }
            }
        }

        // 최솟값(정답) 찾기
        for(int i = 0; i < n; i++){
            answer = Math.min(answer, dist[s-1][i] + dist[i][a-1] + dist[i][b-1]);
        }

        return answer;
    }
}