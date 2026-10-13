import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0; // K 시간 이하로 배달이 가능한 마을의 개수

        ArrayList<int[]>[] town_list = new ArrayList[N + 1]; // 마을 번호가 1부터 시작하므로 배열의 크기는 N + 1 한 값으로 선언

        //각 마을마다 연결된 마을과 걸리는 시간을 담기 위해서 빈 리스트를 하나씩 만들기 위한 반복문(예를 들어서 1번 마을과 연결된 마을이 어디인지, 몇 시간이 걸리는지 확인할 수 있음)
        for (int i = 0; i < town_list.length; i++) {
            town_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < road.length; i++) {
            int a = road[i][0]; // 도로가 연결하는 마을 번호
            int b = road[i][1]; // 도로가 연결하는 마을 번호
            int c = road[i][2]; // 도로를 지나는데 걸리는 시간

            town_list[a].add(new int[]{b, c}); // a 마을의 리스트에 (b 마을, 걸리는 시간)을 담아주고
            town_list[b].add(new int[]{a, c}); // 양방향이므로 b 마을의 리스트에도 (a 마을, 걸리는 시간)을 담아줌
        }

        int[] dist = new int[N + 1]; // 1번 마을에서 각 마을까지 걸리는 최소 시간을 저장할 배열
        Arrays.fill(dist, Integer.MAX_VALUE); // 더 짧은 시간이 나오면 더 빠른 시간으로 교체함으로 처음 값은 가장 큰 수로 채워줌

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]); // 도로마다 시간이 다르기 때문에 그냥 큐가 아니라 걸리는 시간이 가장 작은 값을 먼저 꺼내는 우선순위 큐를 사용
        pq.offer(new int[]{0, 1}); // 시간, 마을 번호(출발점은 1번 마을)

        while (!pq.isEmpty()) {
            int[] min_num = pq.poll(); // 지금까지 걸린 시간이 가장 작은 마을을 꺼냄
            int dist_count = min_num[0]; // 0번 칸은 걸린 시간
            int town_num = min_num[1]; // 1번 칸은 마을 번호

            if (dist_count > dist[town_num]) { // 이미 더 짧은 시간으로 기록이 되어 있는 마을이면
                continue; // 이전에 넣어둔 긴 시간은 버림
            }

            for (int[] next : town_list[town_num]) { // 지금 꺼낸 마을과 연결된 마을들을 하나씩 살피기
                int next_town = next[0]; // 연결된 마을 번호
                int time = next[1]; // 그 마을까지 가는 도로의 시간

                if (dist_count + time < dist[next_town]) { // 지금 마을을 거쳐서 가는게 더 짧을 경우
                    dist[next_town] = dist_count + time; // 더 짧은 시간으로 기록을 갱신해줌
                    pq.offer(new int[]{dist[next_town], next_town}); // 갱신된 시간으로 다시 큐에 넣어줌
                }
            }
        }

        // 1번 마을부터 N번 마을까지 걸리는 시간이 K 이하인 마을의 개수를 세기
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}