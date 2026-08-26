import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String args[])throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 지폐의 금액
        int K = Integer.parseInt(br.readLine()); // 동전의 가지 수

        int[]p_list = new int[K]; // 동전의 금액 배열
        int[]n_list = new int[K]; // 동전의 개수 배열
        int[]dp = new int[T + 1]; // 메모라이제이션(매모라이제이션에 담기는 것은 문제에서 구하고자 하는 것 : j원을 만들고자 하는 가지수)

        dp[0] = 1; // 메모라이제이션 (0원을 만드는 방법은 1가지 방법 => 미리선언)

        for(int i = 0; i < K; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st2.nextToken()); // 동전의 금액
            int n = Integer.parseInt(st2.nextToken()); // 동전의 개수

            p_list[i] = p; // 동전의 금액 구성이 어떻게 형성되어있는지 담는 배열
            n_list[i] = n; // 동전의 개수가 현재 어떻게 들어오고 있는지 확인하기 위한 배열
        }
        for(int i = 0; i < K; i++){ //동전 종류를 하나하나씩 보면서 살핌
            int coin = p_list[i];
            int cnt = n_list[i];
            for(int j = T; j >= 1; j--){ //큰 금액 부터 내려가면서 살피기 같은 동전 종류가 겹치지 않게 역순
                for(int k = 1; k <= cnt; k++){ // 해당 동전이 다 쓰였는지 확인이 필요(수량 제한이 있기에 확인이 필수)
                    if(j - coin * k < 0)break; //음수가 나오면 안되므로 음수가 나왔을 경우 break
                    dp[j] += dp[j - coin * k]; // 해당 문제는 모든 경우의 수를 도출 해야하기 때문에 누적으로 처리
                }
            }
        }
        System.out.println(dp[T]);
    }
}