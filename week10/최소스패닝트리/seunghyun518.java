//import java.util.*;
//import java.io.*;
//
// // Kruskal 알고리즘 풀이
//public class Main{
//    static int[] parent;
//    static int answer = 0;
//
//    // 팀장을 찾는 함수
//    static int find(int node){
//        if(parent[node] == node) return node;
//        return parent[node] = find(parent[node]);
//    }
//
//    // 팀을 합치는 함수 (+ 팀이 함쳐지면 합치는 비용(cost) answer에 더하기)
//    static void union(int node1, int node2, int cost){
//        node1 = find(node1);
//        node2 = find(node2);
//
//        if(node1 != node2){
//            parent[node2] = node1;
//            answer += cost;
//        }
//    }
//
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int v = Integer.parseInt(st.nextToken());
//        int e = Integer.parseInt(st.nextToken());
//
//        // 최소 비용을 greedy하게 고르기 위한 우선순위 큐
//        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
//            return a[0] - b[0];
//        });
//
//        // 팀 초기 설정
//        parent = new int[v + 1];
//        for(int i = 0; i < v + 1; i ++){
//            parent[i] = i;
//        }
//
//        for(int i = 0; i < e; i++){
//            st = new StringTokenizer(br.readLine());
//            int node1 = Integer.parseInt(st.nextToken());
//            int node2 = Integer.parseInt(st.nextToken());
//            int cost = Integer.parseInt(st.nextToken());
//
//            pq.add(new int[] {cost, node1, node2});
//        }
//
//        // 모든 갈 수 있는 간선을 검사
//        while(!pq.isEmpty()){
//            int[] now = pq.poll();
//
//            // 팀 합치기 시도(이미 같은 팀이라면 union 함수 내에서 걸러냄)
//            union(now[1], now[2], now[0]);
//        }
//
//        System.out.println(answer);
//    }
//}

// Prim 알고리즘 풀이

import java.util.*;
import java.io.*;

public class Main{
    // 간선 정보 저장
    static ArrayList<int[]>[] graph;

    // 이미 팀인지 검사
    static boolean[] visited;

    // 정답
    static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());


        // 배열 초기화
        graph = new ArrayList[v + 1];

        for(int i = 0; i <= v; i++){
            graph[i] = new ArrayList<>();
        }

        visited = new boolean[v + 1];

        // graph 간선 정보 입력받아서 채우기
        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[node1].add(new int[] {node2, cost});
            graph[node2].add(new int[] {node1, cost});
        }

        System.out.println(Prim(1));
    }

    // Prim 알고리즘
    // 시작점에서부터 간선의 비용이 가장 작은 것부터(greedy) 검사 -> visited = false이면(아직 팀이 아니면) pq에 추가
    static int Prim(int start){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        pq.add(new int[] {start, 0});

        while(!pq.isEmpty()){
            int[] now = pq.poll();

            if(!visited[now[0]]){
                // 방문했으니 팀이 됐다 처리
                visited[now[0]] = true;

                // 비용 더하기
                answer += now[1];

                for(int[] edge : graph[now[0]]){
                    int cost = edge[1], next = edge[0];
                    if(!visited[next]){
                        pq.add(new int[] {next, cost});
                    }
                }
            }
        }
        return answer;
    }
}