import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine().trim()); // 지폐의 금액
        int k = Integer.parseInt(br.readLine().trim()); // 동전의 가지 수

        int[] p = new int[k]; // 동전의 금액
        int[] n = new int[k]; // 동전의 개수

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            p[i] = Integer.parseInt(st.nextToken());
            n[i] = Integer.parseInt(st.nextToken());
        }

        // dp[j] = 지금까지 처리한 동전 종류들로 정확히 j원을 만드는 방법의 수
        long[] dp = new long[t + 1];
        dp[0] = 1; // 0원을 만드는 방법은 "아무 동전도 안 쓴다"는 1가지

        for (int i = 0; i < k; i++) {
            int coin = p[i];
            int limit = n[i];
            long[] dpNew = new long[t + 1];

            for (int j = 0; j <= t; j++) {
                if (j < coin) {
                    // 이 동전을 하나도 쓸 수 없는 구간 (0개 사용만 가능)
                    dpNew[j] = dp[j];
                } else {
                    // dpNew[j-coin]까지의 누적합을 재사용하고,
                    // "동전을 (limit+1)개 이상 쓴 경우"만큼을 빼서 개수 제한을 반영
                    dpNew[j] = dp[j] + dpNew[j - coin];
                    int over = j - (long_multiply(limit + 1, coin));
                    if (over >= 0) {
                        dpNew[j] -= dp[over];
                    }
                }
            }

            dp = dpNew;
        }

        System.out.println(dp[t]);
    }

    // (limit+1) * coin 계산 시 int 오버플로우 방지를 위한 안전한 곱셈
    static int long_multiply(long a, long b) {
        long result = a * b;
        if (result > 100000) { // T의 최댓값(10000)보다 충분히 크면 어차피 음수 취급될 값이므로 캡을 씌움
            return Integer.MAX_VALUE / 2;
        }
        return (int) result;
    }
}
