import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시의 수
        int M = Integer.parseInt(st.nextToken()); // 고속도로의 수
        int K = Integer.parseInt(st.nextToken()); // 해의 수(통행료가 인상되는 횟수)

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st2.nextToken()); // 출발 도시
        int B = Integer.parseInt(st2.nextToken()); // 도착 도시

        ArrayList<int[]>[] city_list = new ArrayList[N + 1]; // 도시 번호가 1부터 시작하므로 배열의 크기는 N + 1 한 값으로 선언

        //각 도시마다 연결된 도시와 통행료를 담기 위해서 빈 리스트를 하나씩 만들기 위한 반복문(예를 들어서 1번 도시와 연결된 도시가 어디인지, 통행료가 얼마인지 확인할 수 있음)
        for (int i = 0; i < city_list.length; i++) {
            city_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st3 = new StringTokenizer(br.readLine()); // 값이 한줄마다 달라져야 하므로 반복문 안에 같이 선언

            int f = Integer.parseInt(st3.nextToken()); // 도로가 연결하는 도시 번호
            int t = Integer.parseInt(st3.nextToken()); // 도로가 연결하는 도시 번호
            int c = Integer.parseInt(st3.nextToken()); // 도로의 통행료

            city_list[f].add(new int[]{t, c}); // f 도시의 리스트에 (t 도시, 통행료)를 담아주고
            city_list[t].add(new int[]{f, c}); // 양방향이므로 t 도시의 리스트에도 (f 도시, 통행료)를 담아줌
        }

        int up_price = 0; // 지금까지 오른 금액의 합(매년 오른 금액이 계속 쌓이므로 누적해서 더해줌)

        // 첫 해(year = 0)부터 K년 후까지 매년 다익스트라를 새로 돌려서 최소 통행료를 구함
        for (int year = 0; year <= K; year++) {
            if (year > 0) { // 첫 해는 인상 전이므로 1년 후부터 오른 금액을 읽어서 더해줌
                up_price += Integer.parseInt(br.readLine().trim());
            }

            int[] dist = new int[N + 1]; // A 도시에서 각 도시까지의 최소 통행료를 저장할 배열 = 해마다 통행료가 달라지므로 매년 새로 만듦
            Arrays.fill(dist, Integer.MAX_VALUE); // 더 작은 통행료가 나오면 값이 변경 되야 하므로 모든 값들을 표시해둠
            dist[A] = 0; // 출발 도시는 0으로

            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]); // 도로마다 통행료가 다르기에 우선순위 큐를 사용
            pq.offer(new int[]{0, A}); // 통행료, 도시 번호(출발점은 A 도시)

            while (!pq.isEmpty()) {
                int[] min_num = pq.poll(); // 지금까지 낸 통행료가 가장 작은 도시를 꺼냄
                int dist_count = min_num[0]; // 0번 칸은 지금까지 낸 통행료
                int city_num = min_num[1]; // 1번 칸은 도시 번호

                if (dist_count > dist[city_num]) { // 이미 더 작은 통행료로 기록이 되어 있는 도시면 이전에 넣어둔 큰 통행료는 버림
                    continue;
                }

                for (int[] next : city_list[city_num]) { // 지금 꺼낸 도시와 연결된 도시들을 하나씩 살피기
                    int next_city = next[0]; // 연결된 도시 번호
                    int price = next[1] + up_price; // 원래 통행료에 지금까지 오른 금액을 더한 올해 통행료

                    if (dist_count + price < dist[next_city]) { // 지금 도시를 거쳐서 가는게 더 쌀 경우 더 작은 통행료로 변경
                        dist[next_city] = dist_count + price;
                        pq.offer(new int[]{dist[next_city], next_city}); // 갱신된 통행료로 다시 큐에 넣어줌
                    }
                }
            }

            System.out.println(dist[B]); // 올해 A에서 B까지의 최소 통행료 출력
        }
    }
}