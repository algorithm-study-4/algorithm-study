import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int[][] meetings = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken()); // 시작 시각
            meetings[i][1] = Integer.parseInt(st.nextToken()); // 종료 시긱
        }

        // 종료 시각을 기준으로 오름차순 정렬
        Arrays.sort(meetings, Comparator.comparingInt(m -> m[1]));

        int count = 0;
        int lastEndTime = 0; // 마지막으로 선택된 회의의 종료 시각

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];

            // 시작 시각이 이전 회의의 종료 시각 이후(같은 시각 포함)라면 진행 가능
            if (start >= lastEndTime) {
                count++;
                lastEndTime = end;
            }
        }

        System.out.println(count);
    }
}
