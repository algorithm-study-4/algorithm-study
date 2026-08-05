import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력
        int n = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            int total = line.length();
            int filled = 0;

            // 문자열을 한 번만 순회하며 '#' 개수를 셈
            for (int j = 0; j < total; j++) {
                if (line.charAt(j) == '#') {
                    filled++;
                }
            }

            int percent = (filled * 100) / total;
            sb.append(percent).append("%\n");
        }

        System.out.println(sb);
    }
}