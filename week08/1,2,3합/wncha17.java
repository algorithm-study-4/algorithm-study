import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Main {
    static int N;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 정수 N 입력 (1 <= N <= 30)
        N = Integer.parseInt(br.readLine());

        // 합계 0부터 백트래킹 탐색 시작
        backtrack(0);

        // 결과 출력
        System.out.println(count);
    }

    static void backtrack(int sum) {
        // 목표한 값 N과 같아지면 올바른 수식 하나를 완성한 것이므로 카운트 증가 후 종료
        if (sum == N) {
            count++;
            return;
        }

        // 현재 합이 N을 초과하면 유효하지 않으므로 더 이상 탐색하지 않고 가지치기(Pruning)
        if (sum > N) {
            return;
        }

        // 1, 2, 3을 각각 더해가며 다음 단계로 탐색을 진행 (재귀 호출)
        for (int i = 1; i <= 3; i++) {
            backtrack(sum + i);
        }
    }
}
