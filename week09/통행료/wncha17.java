
// 시간복잡도
// O(MlogM)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int n;                       // 도시 수
    static List<List<int[]>> graph;     // graph.get(u) = {도착 도시, 원래 통행료} 목록

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // [1] 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());       // 도시 수
        int m = Integer.parseInt(st.nextToken());   // 도로 수
        int k = Integer.parseInt(st.nextToken());   // 인상이 일어나는 해의 수

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());   // 출발 도시
        int b = Integer.parseInt(st.nextToken());   // 도착 도시

        // 도시 번호가 1부터 시작하므로 n+1 크기로 만들어 0번 칸은 비워둠
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 도로 정보 입력: f와 t를 잇는 통행료 c짜리 도로
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 도로는 양방향이므로 양쪽에 모두 등록
            graph.get(f).add(new int[]{t, c});
            graph.get(t).add(new int[]{f, c});
        }

        StringBuilder sb = new StringBuilder();

        // [2] 첫 해: 인상 없음(extra = 0)
        int extra = 0;      // 지금까지 누적된 인상액
        sb.append(dijkstra(a, b, extra)).append('\n');

        // [3] 1년 후 ~ K년 후: 인상액을 누적하면서 매년 다시 계산
        for (int year = 1; year <= k; year++) {
            int p = Integer.parseInt(br.readLine().trim());
            extra += p;     // 인상은 누적된다
            sb.append(dijkstra(a, b, extra)).append('\n');
        }

        System.out.print(sb);
    }

    // 모든 도로의 통행료를 (원래 통행료 + extra)로 보고
    // start에서 end까지의 최소 통행료를 구하는 다익스트라
    static int dijkstra(int start, int end, int extra) {

        // dist[i] = start에서 i번 도시까지 지금까지 알아낸 최소 통행료
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);   // 처음엔 모두 "무한대"
        dist[start] = 0;                        // 출발 도시는 0원

        // 큐 원소: {도시 번호, 그 도시까지의 통행료}
        // 통행료(배열의 1번 칸)가 작은 것부터 꺼냄
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];  // 지금 꺼낸 도시
            int cost = cur[1];  // 그 도시까지 기록된 통행료

            // 이미 더 싼 경로로 처리된 도시의 낡은 기록이면 버림
            if (cost > dist[node]) continue;

            // [조기 종료]
            // 다익스트라에서 큐에서 꺼낸 순간 그 도시의 최소 비용은 "확정"됨
            // 우리가 궁금한 건 도착 도시 하나뿐이므로, 꺼내자마자 바로 답을 반환해도 됨
            if (node == end) return cost;

            // 이웃 도시 확인
            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                int toll = next[1] + extra;     // 올해의 실제 통행료 = 원래 통행료 + 누적 인상액
                int newCost = cost + toll;      // node를 거쳐 nextNode로 가는 총 비용

                if (newCost < dist[nextNode]) { // 더 싸게 가는 길을 발견하면 갱신
                    dist[nextNode] = newCost;
                    pq.add(new int[]{nextNode, newCost});
                }
            }
        }

        // 문제 조건상 항상 도달 가능하므로 여기까지 오지 않음
        return dist[end];
    }
}
