
// 시간복잡도
// (플로이드-워셜): O(N*N*N)
// (케빈 베이컨 수 계산): O(N*N)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 충분히 큰 값을 "연결 안 됨(무한대)"으로 사용 (더할 때 오버플로우가 나지 않도록 MAX_VALUE는 피함)
        final int INF = 1_000_000;

        // dist[i][j] = i번 사람과 j번 사람 사이의 최단 거리
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // 자기 자신까지의 거리는 0
        }

        // 친구 관계 입력 (양방향이므로 양쪽 모두 거리 1로 설정)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        // 플로이드-워셜: k번 사람을 "경유지"로 사용했을 때 더 짧아지는 경로가 있는지 모든 쌍(i, j)에 대해 확인
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 각 사람의 케빈 베이컨 수(다른 모든 사람까지의 거리 합)를 구하고, 가장 작은 사람을 찾음
        int minSum = Integer.MAX_VALUE;
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                sum += dist[i][j];
            }
            // 엄격히 작을 때만 갱신 -> 동점이면 번호가 더 작은(먼저 확인한) 사람이 유지됨
            if (sum < minSum) {
                minSum = sum;

            }
        }

        System.out.println(answer);
    }
}
