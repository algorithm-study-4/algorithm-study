import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][2];
        int[] dp = new int[w + 1];

        // 정확히 w만큼의 길이로 채워야하므로 초기값 = 도달 불가능한 -1로 세팅
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 모든 광물을 넣어볼거다
        for(int i = 0; i < n; i++){
            int l = arr[i][0];
            int h = arr[i][1];
            int weight = l * h;

            // dp 리스트 채우기
            for(int j = w; j >= 0; j--){
                if((j >= l) && dp[j-l] != -1){
                    dp[j] = Math.max(dp[j], dp[j-l] + weight);
                }
                if((j >= h) && dp[j-h] != -1){
                    dp[j] = Math.max(dp[j], dp[j-h] + weight);
                }
            }
        }
        System.out.println(Math.max(0, dp[w]));
    }
}


// 최댓값 -> inv[]에서 math max해서 값을 비교해서 넣는다
// 광물을 무한으로 사용할 수 없으므로, 바깥쪽 for문을 광물로 둬서 모든 광물을 순회하는 것으로 생각해야한다.
// -> 바깥쪽 반복문을 모두 순회 = 모든 광물을 넣어봄
/* dp 리스트를 채울 때 역방향으로 해야하는 이유
for(int j = w; j >= 0; j--){
내일 이시간에
 */
