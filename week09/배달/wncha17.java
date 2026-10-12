
// 시간복잡도
// O(ElogE) (도로 수: E)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int solution(int N, int[][] road, int K) {

        // [1단계] 그래프 만들기 (인접리스트)
        // graph.get(u) 는 "u번 마을에서 갈 수 있는 도로 목록"
        // 각 도로는 int[]{도착 마을, 걸리는 시간} 형태로 저장
        // 예) graph.get(1) = [ {2, 1}, {4, 2} ]
        //     -> 1번 마을에서 2번 마을까지 1분, 4번 마을까지 2분
        //
        // 마을 번호가 1부터 시작하므로 크기를 N+1로 만들어서
        // 0번 칸은 쓰지 않고, 번호를 그대로 인덱스로 사용함
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>()); // 마을마다 빈 도로 목록 준비
        }

        // road 배열의 각 원소 r = {a, b, c}
        // r[0] = a (마을), r[1] = b (마을), r[2] = c (걸리는 시간)
        for (int[] r : road) {
            // 양방향 도로이므로 a->b, b->a 두 방향을 모두 등록해야 한다.
            graph.get(r[0]).add(new int[]{r[1], r[2]}); // a에서 b로 c분
            graph.get(r[1]).add(new int[]{r[0], r[2]}); // b에서 a로 c분
        }
        // 같은 두 마을 사이에 도로가 여러 개여도 문제없다.
        // 리스트에 둘 다 들어가고, 다익스트라가 알아서 짧은 쪽을 고른다.

        // [2단계] 최단 시간 배열 초기화
        // dist[i] = "1번 마을에서 i번 마을까지 지금까지 찾은 최단 시간"
        // 처음에는 아무 길도 모르니 전부 "무한대"로 둔다.
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 출발점인 1번 마을은 이동할 필요가 없으니 0
        dist[1] = 0;

        // [3단계] 우선순위큐 준비
        // 큐에 들어가는 원소: int[]{마을 번호, 그 마을까지 걸린 시간}
        // 정렬 기준: 걸린 시간(a[1])이 작은 것이 먼저 나옴 (오름차순)
        //    -> "가장 일찍 도착한 마을"부터 꺼내기 위함
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // 출발점(1번 마을, 시간 0)을 큐에 넣고 시작
        pq.add(new int[]{1, 0});

        // [4단계] 다익스트라 본체
        while (!pq.isEmpty()) {

            // 큐에서 "현재 가장 일찍 도착한 마을"을 꺼냄
            int[] cur = pq.poll();
            int node = cur[0]; // 지금 꺼낸 마을 번호
            int time = cur[1]; // 이 마을까지 걸린 시간

            // 이미 더 빠른 경로로 처리가 끝난 마을 (건너뜀)
            if (time > dist[node]) continue;

            // [이웃 마을 확인]
            // node에서 갈 수 있는 모든 도로를 하나씩 살펴본다.
            for (int[] next : graph.get(node)) {
                int nextNode = next[0];    // 도로가 연결하는 이웃 마을
                int roadTime = next[1];    // 그 도로를 지나는 데 걸리는 시간

                // node를 거쳐서 nextNode로 가는 총 시간
                //   = (1번에서 node까지 걸린 시간) + (node에서 nextNode까지 도로 시간)
                int newTime = time + roadTime;

                // 지금까지 알던 nextNode까지의 최단 시간보다 더 짧다면?
                if (newTime < dist[nextNode]) {
                    dist[nextNode] = newTime;              // 최단 시간 갱신
                    pq.add(new int[]{nextNode, newTime});  // 큐에 넣어서 이후 이 마을의 이웃들도 확인하게 함
                }
            }
        }

        // [5단계] K 시간 이하로 배달 가능한 마을 세기
        int count = 0;
        for (int i = 1; i <= N; i++) {
            // "K 이하"이므로 <= 이다. (K와 같은 시간도 가능)
            // 1번 마을은 dist[1] = 0 이라서 항상 포함된다.
            if (dist[i] <= K) count++;
        }
        return count;
    }
}
