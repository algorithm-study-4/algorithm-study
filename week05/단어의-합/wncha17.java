import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        // 알파벳별 '자릿값 가중치'를 저장할 배열
        long[] weight = new long[26];

        for (int i = 0; i < n; i++) {
            String word = br.readLine().trim();
            int len = word.length();

            // 각 글자의 자릿수만큼 가중치를 누적
            long place = 1;
            for (int j = len - 1; j >= 0; j--) {
                int idx = word.charAt(j) - 'A';
                weight[idx] += place;
                place *= 10;
            }
        }

        // 가중치를 내림차순으로 정렬
        Arrays.sort(weight);

        long answer = 0;
        int digit = 9;

        // 가중치가 큰 알파벳부터 순서대로 가장 큰 숫자(9)를 배정
        for (int i = 25; i >= 0; i--) {
            if (weight[i] == 0) {
                break;
            }
            answer += weight[i] * digit;
            digit--;
        }

        System.out.println(answer);
    }
}
