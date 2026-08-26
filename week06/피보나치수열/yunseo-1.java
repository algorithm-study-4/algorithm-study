import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        
        final int MOD = 1_000_000_007; 
       
        long[] dp = new long[n + 1];
       
        if (n >= 1) dp[1] = 1;
        if (n >= 2) dp[2] = 1;
      
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD; 
        }
        
        System.out.println(dp[n]);
    }
}