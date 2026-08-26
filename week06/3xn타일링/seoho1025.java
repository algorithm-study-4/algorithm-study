import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    final int mod = 1000000007;
    public static void main(String args[])throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 가로 길이

        long[] dp = new long[n + 1];

        dp[0] = 1;

        for(int i = 2; i <= n; i += 2){
            dp[i] = (3 * dp[i - 2])% mod;
            for(int j = i - 4; j >= 0; j -= 2){
                dp[i] +=(2 * dp[j]) % mod;
            }
        }
        System.out.println(dp[n]);
    }
}