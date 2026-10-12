import java.util.*;

// 다익스트라 알고리즘 풀이
class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            else return a[1] - b[1];
        });

        List<int[]>[] graph = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] r: road){
            graph[r[0]].add(new int[] {r[2], r[1]});
            graph[r[1]].add(new int[] {r[2], r[0]});
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, 99999999);
        dist[1] = 0;

        int[] start = new int[] {0, 1};
        pq.add(start);

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int d = cur[0], now = cur[1];
            if(d > dist[now]) continue;

            for(int[] edge : graph[now]){
                int num = edge[0], next = edge[1];
                d = dist[now] + num;
                if(d < dist[next]){
                    dist[next] = d;
                    pq.add(new int[] {d, next});
                }
            }
        }

        for(int i : dist){
            if(i <= K){
                answer += 1;
            }
        }

        return answer;
    }
}

// 플로이드 워셜 알고리즘 풀이
//class Solution {
//    public int solution(int N, int[][] road, int K) {
//        int answer = 0;
//        int INF = 10000000;
//        int[][] floyd = new int[N][N];
//
//        // 2차원 배열 초기화
//        for(int i = 0; i < N; i++){
//            for(int j = 0; j < N; j++){
//                if(i != j){
//                    floyd[i][j] = INF;
//                }
//            }
//        }
//
//        // 다리의 비용 입력
//        for(int[] r : road){
//            int a = r[0] - 1;
//            int b = r[1] - 1;
//            floyd[a][b] = Math.min(floyd[a][b], r[2]);
//            floyd[b][a] = Math.min(floyd[b][a], r[2]);
//        }
//
//        for(int i = 0; i < N; i++){
//            for(int j = 0; j < N; j++){
//                for(int k = 0; k < N; k++){
//                    floyd[j][k] = Math.min(floyd[j][k], floyd[j][i] + floyd[i][k]);
//                }
//            }
//        }
//
//        for(int i = 0; i < N; i++){
//            if(floyd[0][i] <= K){
//                answer += 1;
//            }
//        }
//
//        return answer;
//    }
//}