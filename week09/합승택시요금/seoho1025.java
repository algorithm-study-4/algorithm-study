import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE; // 최저 택시요금 가장 작은 값을 찾아야 하기 때문에 처음 숫자들을 큰수들로 채움

        ArrayList<int[]>[] point_list = new ArrayList[n + 1]; // 지점 번호가 1부터 시작하므로 배열의 크기는 n + 1 한 값으로 선언

        //각 지점마다 연결된 지점과 택시요금을 담기 위해서 빈 리스트를 하나씩 만들기 위한 반복문(예를 들어서 4번 지점과 연결된 지점이 어디인지, 요금이 얼마인지 확인할 수 있음)
        for (int i = 0; i < point_list.length; i++) {
            point_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < fares.length; i++) {
            int c = fares[i][0]; // 연결된 지점 번호
            int d = fares[i][1]; // 연결된 지점 번호
            int f = fares[i][2]; // 두 지점 사이의 택시요금

            point_list[c].add(new int[]{d, f}); // c 지점의 리스트에 (d 지점, 요금)을 담아주고
            point_list[d].add(new int[]{c, f}); // 방향에 따라 요금이 달라지지 않으므로 d 지점의 리스트에도 (c 지점, 요금)을 담아줌
        }

        // 합승을 어디서 끝내는지가 문제의 포인트
        // 합승이 끝나는 지점을 k라고 하면 전체 요금 = (s → k) + (k → a) + (k → b)
        // 양방향이라 k → a 요금은 a → k 요금과 같으므로 s, a, b 세 지점에서 각각 다익스트라를 돌리면 됨
        int[] start_list = {s, a, b}; // 다익스트라를 돌릴 출발 지점들
        int[][] all_dist = new int[3][]; // 0번은 s 기준, 1번은 a 기준, 2번은 b 기준 최소 요금 배열

        for (int k = 0; k < 3; k++) {
            int start = start_list[k]; // 이번에 다익스트라를 돌릴 출발 지점

            int[] dist = new int[n + 1]; // start 지점에서 각 지점까지의 최소 요금을 저장할 배열
            Arrays.fill(dist, Integer.MAX_VALUE); // 더 작은 요금이 나오면 갱신하는 방식이므로 처음 값은 가장 큰 수로 채워줌
            dist[start] = 0; // 출발 지점은 0으로


            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((x, y) -> x[0] - y[0]); // 요금이 가장 작은 값을 먼저 꺼내는 우선순위 큐
            pq.offer(new int[]{0, start}); // 요금, 지점 번호

            while (!pq.isEmpty()) {
                int[] min_num = pq.poll(); // 지금까지 요금이 가장 작은 지점을 꺼냄
                int dist_count = min_num[0]; // 0번 칸은 지금까지의 요금
                int point_num = min_num[1]; // 1번 칸은 지점 번호

                if (dist_count > dist[point_num]) { // 이미 더 작은 요금으로 기록이 되어 있는 지점이면 이전에 넣어둔 큰 요금은 버림
                    continue;
                }

                for (int[] next : point_list[point_num]) { // 지금 꺼낸 지점과 연결된 지점들을 하나씩 살피기
                    int next_point = next[0]; // 연결된 지점 번호
                    int price = next[1]; // 그 지점까지 가는 요금

                    if (dist_count + price < dist[next_point]) { // 지금 지점을 거쳐서 가는게 더 쌀 경우 더 작은 요금으로 변경되게
                        dist[next_point] = dist_count + price;
                        pq.offer(new int[]{dist[next_point], next_point}); // 갱신된 요금으로 다시 큐에 넣어줌
                    }
                }
            }

            all_dist[k] = dist; //결과를 저장
        }

        // 모든 지점을 합승이 끝나는 지점 k로 하나씩 가정해보고 가장 작은 요금을 찾기
        for (int i = 1; i <= n; i++) {
            // 갈 수 없는 지점은 MAX_VALUE로 남아 있어서 더하면 int 범위를 넘어가므로 건너뛰기
            if (all_dist[0][i] == Integer.MAX_VALUE || all_dist[1][i] == Integer.MAX_VALUE || all_dist[2][i] == Integer.MAX_VALUE) {
                continue;
            }

            int total = all_dist[0][i] + all_dist[1][i] + all_dist[2][i]; // (s → i) + (i → a) + (i → b)

            if (total < answer) { // 더 작은 요금이 있으면 새롭게 갱신
                answer = total;
            }
        }

        return answer;
    }
}