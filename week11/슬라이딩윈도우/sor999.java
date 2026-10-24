
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            long sum = 0;

            for (int j = 0; j < w; j++) {
                sum += arr[j];
            }

            long max = sum;

            int s = 0;
            int e = w - 1;

            int ansS = s;
            int ansE = e;

            while(e < n - 1){
                sum -= arr[s];
                s++;
                e++;

                sum += arr[e];

                if (sum > max) {
                    max = sum;
                    ansS = s;
                    ansE = e;
                }

            }

            sb.append("#").append(i).append(" ")
                    .append(ansS).append(" ")
                    .append(ansE).append(" ")
                    .append(max).append("\n");
        }

        System.out.print(sb);
    }
}
