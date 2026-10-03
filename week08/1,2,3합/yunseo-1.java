import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution {
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        dfs(n);

        System.out.println(count);
    }

    // remain: 아직 만들어야 하는 남은 숫자
    private static void dfs(int remain) {
        // 기저 조건: 딱 0이 되면 합이 N인 방법 하나 완성
        if (remain == 0) {
            count++;
            return;
        }

        // 1, 2, 3을 각각 선택해보기 (매번 1부터 시작)
        for (int i = 1; i <= 3; i++) {
            // 남은 숫자보다 큰 수를 쓰면 음수가 되므로 건너뜀
            if (remain - i < 0) continue;

            dfs(remain - i); // i를 선택하고 남은 숫자로 재귀

        }
    }
}