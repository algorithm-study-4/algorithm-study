import java.util.*;
import java.io.*;

public class Main{

    // 다익스트라 함수에서 사용할 전역변수 선언(그래프, 출발지, 도착지, 도시의 수)
    static List<int[]>[] graph;
    static int a;
    static int b;
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        // 그래프 배열 만들기 배열안에 리스트안에 배열 형태
        graph = new ArrayList[n + 1];

        // 그래프 배열 안 리스트 만들기
        for(int i = 0; i < n + 1; i++){
            graph[i] = new ArrayList<>();
        }

        // 그래프 리스트 안 배열 채우기(도로의 정보)
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[node1].add(new int[]{node2, cost});
            graph[node2].add(new int[]{node1, cost});
        }

        // 매년 인상되는 통행료에 따른 정답 찾기(다익스트라 함수 호출)
        for(int i = 0; i < k; i++){
            System.out.println(dijkstra());
            int up = Integer.parseInt(br.readLine());
            for(List<int[]> l : graph){
                for(int[] arr : l){
                    arr[1] += up;
                }
            }
        }
        // 마지막 정답 출력
        System.out.println(dijkstra());
    }

    // 다익스트라 함수
    static int dijkstra(){
        // 최소 비용 도로부터 검사하기 위한 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[1] != b[1]) return a[1] - b[1];
            else return a[0] - b[0];
        });

        // 출발 도시 -> 각 도시까지의 비용을 저장하는 배열
        int[] dist = new int[n + 1];

        // 배열 초기화(맨 처음은 자기자신 제외하고 전부 INF)
        for(int i = 1; i < n + 1; i++){
            dist[i] = 100000000;
        }
        dist[a] = 0;

        // 출발지를 큐에 추가
        pq.add(new int[] {a, 0});

        // 검사 시작
        while(!pq.isEmpty()){
            // 현재 도시
            int[] cur = pq.poll();
            int d = cur[1], now = cur[0];
            // 큐에서 나온 현재 도시로 가는 비용이 지금까지 찾은 현재 도시로의 최소 비용보다 크다면 무시
            if(d > dist[now]) continue;

            // 현재 도시에 연결되어 있는 모든 도로 검사
            for(int[] edge : graph[now]) {
                // 현재 도시 -> 다음 도시
                int next = edge[0], cost = edge[1];

                // 현재 도시 -> 다음 도시 비용과 현재까지 다음 도시로의 최소 비용을 비교
                if (d + cost < dist[next]) {
                    dist[next] = d + cost;

                    // 현재 도시를 거쳐가는 비용이 더 작다면 큐에 추가
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }

        // 출발 도시 -> 도착 도시의 비용 return
        int answer = dist[b];
        return answer;
    }
}