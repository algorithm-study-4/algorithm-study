import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 1;

        // dp 리스트 채우기
        for (int i = 3; i <= n; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 1000000007;
        }

        System.out.println(arr[n]);
    }
}