import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 광물 개수
        int w = Integer.parseInt(st.nextToken()); // 인벤토리 길이
        
        int[] l = new int[n];
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            l[i] = Integer.parseInt(st.nextToken());
            h[i] = Integer.parseInt(st.nextToken());
        }
        
        // dp[j] = 길이 합을 정확히 j로 만들었을 때의 최대 무게
        // -1은 "그 길이를 정확히 만드는 방법이 아직 없음"을 의미
        int[] dp = new int[w + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0; // 길이 0 = 아무것도 안 놓음 = 무게 0으로 항상 가능 (기저 조건)
        
        for (int i = 0; i < n; i++) {
            int weight = l[i] * h[i]; // 이 광물의 무게 (어느 방향으로 놓든 동일)
            
            // l방향, h방향 두 옵션을 "같은 이전 상태" 기준으로 적용해야
            // 한 광물을 두 번(l로도, h로도) 쓰는 걸 방지할 수 있음
            int[] dpPrev = dp.clone();
            
            // 이 광물을 가로 방향으로 놓는 경우
            for (int j = l[i]; j <= w; j++) {
                if (dpPrev[j - l[i]] != -1) { // 이전 상태에서 (j - l[i]) 길이가 가능했다면
                    dp[j] = Math.max(dp[j], dpPrev[j - l[i]] + weight);
                }
            }
            
            // 옵션 2: 세로 방향
            for (int j = h[i]; j <= w; j++) {
                if (dpPrev[j - h[i]] != -1) {
                    dp[j] = Math.max(dp[j], dpPrev[j - h[i]] + weight);
                }
            }
            
        }
        
        // dp[w]가 -1이면 정확히 W를 채우는 조합이 없기 때문에 0 출력
        System.out.println(dp[w] == -1 ? 0 : dp[w]);
    }
}