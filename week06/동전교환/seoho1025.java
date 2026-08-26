import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); // 물품의 가치
        int n = Integer.parseInt(st.nextToken()); // 동전의 수

        //해당 문제의 DP 핵심은 배열의 크기가 쪼갠 문제의 종류 수
        int[] memo = new int[T + 1]; // 메모라이징 배열선언(인덱스는 0으로 시작하기 때문에 +1을 해서 값을 전부 받을 수 있게 설정)
        Arrays.fill(memo, T + 1);// 최소수를 찾아야 하기떄문에 최대수로 선언하고 최소수를 갱신하는 방향성으로
        memo[0] = 0; // 0원은 동전 0개로 만들 수 있음 (계산이 필요 없는 기저 조건)

        int[] info = new int[n]; // 동전의 가치를 저장해줄 배열 선언

        StringTokenizer st2 = new StringTokenizer(br.readLine()); //StringTokenzier은 새로운 값을 받을 경우 재선언이 필요함
        for (int i = 0; i < n; i++) {
            info[i] = Integer.parseInt(st2.nextToken()); // 동전의 가치를 입력받음
        }

        for(int i = 1; i <= T; i++){
            for(int coin : info){
                if(coin > i) continue;
                if(memo[i - coin] == T + 1) continue;
                memo[i] = Math.min(memo[i], memo[i - coin] + 1); //memo[i - coin]이 전 단계의 코인 개수, + 1이 지금 코인 개수
            }
        }
        System.out.println(memo[T] == T + 1 ? "impossible": memo[T]); // 만일 T + 1는 도달이 불가능한 금액이므로 교환불가능이면 "impossible"로 출력 되도록 선언
    }
}