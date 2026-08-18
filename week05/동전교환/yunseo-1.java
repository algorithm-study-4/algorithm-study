import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int amount = Integer.parseInt(br.readLine().trim()); // 교환할 금액
        
        int[] coins = {500, 100, 50, 10}; // 비싼 동전부터 순서대로
        int count = 0;
        
        for (int coin : coins) {
            count += amount / coin; // 이 동전으로 몇 개 쓸 수 있는지 - 몫
            amount %= coin;         // 남은 금액 갱신 - 나머지
        }
        
        System.out.println(count);

    }
}