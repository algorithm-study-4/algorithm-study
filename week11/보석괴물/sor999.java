
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine().trim();

        Map<Long, Long> map = new HashMap<>();

        long sum = 0;

        long ans = 0;

        map.put(0L, 1L);

        for (int i = 0; i < n; i++) {
            int num = str.charAt(i) - '0';

            sum += num - 1;

            ans += map.getOrDefault(sum, 0L);

            map.put(sum, map.getOrDefault(sum, 0L) + 1);
        }

        System.out.println(ans);
    }
}
