
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int [] arr = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<Long, Long> map = new HashMap<>();

        long ans = 0;

        long [] nums = new long[n + 1];

        for(int i = 1; i <= n; i++){
            nums[i] = nums[i - 1] + arr[i - 1];
        }

        for(long num : nums){

            ans += map.getOrDefault(num, 0L);

            map.put(num, map.getOrDefault(num, 0L) + 1);
        }

        System.out.println(ans);

    }
}
