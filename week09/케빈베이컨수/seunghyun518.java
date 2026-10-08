import java.util.*;
import java.io.*;

// floyd 알고리즘 풀이
//public class Main{
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
//        int m = Integer.parseInt(st.nextToken());
//        int[] answer = new int[n];
//        int[][] edge = new int[n][n];
//
//        // floyd 배열 초기화(자기 자신은 0으로 그 외는 매우 큰 수)
//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < n; j++){
//                if(i != j){
//                    edge[i][j] = 999999;
//                }
//            }
//        }
//
//        // 정답
//        int min = 0;
//
//        // 친구 관계 입력받기, 양방향이므로 반대도 추가
//        for(int i = 0; i < m; i++){
//            st = new StringTokenizer(br.readLine());
//            int a = Integer.parseInt(st.nextToken())-1;
//            int b = Integer.parseInt(st.nextToken())-1;
//            edge[a][b] = 1;
//            edge[b][a] = 1;
//        }
//
//        // floyd 알고리즘 k -> j의 최소 비용을 찾는다
//        // k -> j 와 k -> i -> j의 최소를 찾기(환승하는게 더 저렴하냐 아니냐)
//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < n; j++){
//                for(int k = 0; k < n; k++){
//                    edge[k][j] = Math.min(edge[k][j], edge[k][i] + edge[i][j]);
//                }
//            }
//        }
//
//        // 정답 찾기
//        for(int i =0; i<n; i++){
//            for(int j = 0; j<n; j++){
//                answer[i] += edge[i][j];
//            }
//        }
//        for(int i = 0; i < n; i++){
//            if(answer[i] < answer[min]){
//                min = i;
//            }
//        }
//
//        System.out.println(min + 1);
//    }
//}

// dijkstra 알고리즘 풀이
public class Main{
    static List<Integer>[] graph;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException{
        int answer = 0;
        int min = 1000000000;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];

        for(int i = 1; i < n + 1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        for(int i = 1; i < n + 1; i++){
            int temp = dijkstra(i);
            if(min > temp){
                min = temp;
                answer = i;
            }
        }

        System.out.println(answer);
    }

    static int dijkstra(int start){
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->{
            if(a[1] != b[1]) return a[1] - b[1];
            else return a[0] - b[0];
        });
        int[] dist = new int[n + 1];
        for(int i = 0; i < n + 1; i++){
            dist[i] = 1000000000;
        }
        dist[start] = 0;

        pq.add(new int[] {start, dist[start]});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int d = cur[1], now = cur[0];
            if(dist[now] < d) continue;

            for(int i : graph[now]){
                int next = i;
                if(dist[next] > d + 1){
                    dist[next] = d + 1;
                    pq.add(new int[] {next, dist[next]});
                }
            }
        }

        for(int i = 1; i < n + 1 ; i++){
            answer += dist[i];
        }

        return answer;
    }
}