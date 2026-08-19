import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int amount = Integer.parseInt(br.readLine().trim());

        // 큰 단위 동전부터 차례대로 사용 (그리디)
        int[] coins = {500, 100, 50, 10};

        int count = 0;

        for (int coin : coins) {
            count += amount / coin;
            amount %= coin;
        }

        System.out.println(count);
    }
}
