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
        int w = Integer.parseInt(st.nextToken());

        int[] l = new int[n];
        int[] h = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            l[i] = Integer.parseInt(st.nextToken());
            h[i] = Integer.parseInt(st.nextToken());
        }

        // dp[j] = 길이 합이 정확히 j일 때 얻을 수 있는 최대 무게 (불가능하면 -1)
        int[] dp = new int[w + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0; // 길이 0을 만드는 데 필요한 무게는 0 (아무 광물도 안 씀)

        for (int i = 0; i < n; i++) {
            int weight = l[i] * h[i]; // 방향과 상관없이 무게는 동일

            // 0/1 배낭(각 광물은 한 번만 사용) 방식이므로 j를 큰 값부터 감소시키며 갱신
            for (int j = w; j >= 0; j--) {
                int best = dp[j];

                // 가로(l)를 눕혀서 놓는 경우
                if (j >= l[i] && dp[j - l[i]] != -1) {
                    best = Math.max(best, dp[j - l[i]] + weight);
                }
                // 세로(h)를 눕혀서 놓는 경우
                if (j >= h[i] && dp[j - h[i]] != -1) {
                    best = Math.max(best, dp[j - h[i]] + weight);
                }

                dp[j] = best;
            }
        }

        System.out.println(dp[w] == -1 ? 0 : dp[w]);
    }
}
