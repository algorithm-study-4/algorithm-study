import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    // Integer.MAX_VALUE를 쓰지 않는 이유:
    // 마지막에 distS[k] + distA[k] + distB[k]에서
    // 갈 수 없는 지점(k)이 있으면 MAX_VALUE를 더하게 되어 오버플로우.
    // 2000만은 세 개를 더해도 6000만이라 int 범위 내에서 안전.
    static final int INF = 20_000_000;

    // graph.get(u) = u번 지점에서 갈 수 있는 노선 목록, 각 노선은 {도착 지점, 요금}
    static List<List<int[]>> graph;
    static int n;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        Solution.n = n;

        // [1] 그래프 만들기 (인접리스트)
        // 지점 번호가 1부터 시작하므로 n+1 크기로 만들어 0번 칸은 비워둠
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // fare = {c, d, f} : c와 d 사이 요금이 f원
        // 요금은 방향과 상관없이 같으므로 양쪽에 모두 등록
        for (int[] fare : fares) {
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];
            graph.get(c).add(new int[]{d, f}); // c -> d
            graph.get(d).add(new int[]{c, f}); // d -> c
        }

        // [2] 세 지점에서 각각 다익스트라 실행
        // distS[k] = s에서 k까지 최소 요금 (같이 타는 구간)
        // distA[k] = a에서 k까지 최소 요금 (= k -> a)
        // distB[k] = b에서 k까지 최소 요금 (= k -> b)
        int[] distS = dijkstra(s);
        int[] distA = dijkstra(a);
        int[] distB = dijkstra(b);

        // [3] 갈라지는 지점 k를 1~n까지 전부 시도
        int answer = Integer.MAX_VALUE;
        for (int k = 1; k <= n; k++) {
            int total = distS[k] + distA[k] + distB[k];
            // k로 갈 수 없는 지점이면 INF가 더해져 total이 매우 커지므로 자연스럽게 탈락
            answer = Math.min(answer, total);
        }

        return answer;
    }

    // start 지점에서 다른 모든 지점까지의 최소 요금 배열을 구해 반환
    static int[] dijkstra(int start) {

        // -------- 기록장 준비 --------
        // dist[i] = start에서 i까지 "지금까지 찾은" 최소 요금
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);     // 처음엔 모두 "모름"
        dist[start] = 0;            // 출발 지점은 0원

        // -------- 대기줄 준비 --------
        // 쪽지 형태: {지점 번호, 그 지점까지의 요금}
        // 요금(배열의 1번 칸)이 가장 싼 쪽지가 항상 먼저 나오도록 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        pq.add(new int[]{start, 0});

        // -------- 가장 싼 지점부터 하나씩 확정 --------
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];   // 지금 꺼낸 지점
            int cost = cur[1];   // 그 지점까지 기록된 요금

            // 이미 더 싼 경로로 처리가 끝난 지점의 낡은 쪽지면 버리고 다음 쪽지로
            if (cost > dist[node]) continue;

            // node에서 갈 수 있는 모든 노선 확인
            for (int[] next : graph.get(node)) {
                int nextNode = next[0];     // 노선이 연결하는 이웃 지점
                int fare = next[1];         // 그 노선의 요금
                int newCost = cost + fare;  // node를 거쳐 nextNode로 가는 총 요금

                // 기존에 알던 요금보다 싸면 갱신하고, 대기줄에 새 쪽지 추가
                if (newCost < dist[nextNode]) {
                    dist[nextNode] = newCost;
                    pq.add(new int[]{nextNode, newCost});
                }
            }
        }

        // 모든 지점까지의 최소 요금이 담긴 배열 반환
        // (갈 수 없는 지점은 INF 그대로 남음)
        return dist;
    }
}
