// prefix[]선언 -> 맨 처음부터 i까지의 합(누적 합)
// -> prefix을 이용하여 prefix[i + 3] - prefix[i] = i ~ i + 3 까지의 합이 됨
// 연속구간이 0이 되는 경우
// 1. prefix[i] = 0이 될 때(0번째 수 ~ i번째 수까지의 합이 0)
// 2. prefix[i] - prefix[j] = 0이 될 때(i번째 수 ~ j번째 수의 합이 0이 될 때)
// -> prefix[i] = prefix[j]가 된다.
// 결론: prefix[i]가 2번 이상 나온 경우 + prefix[i] = 0인 경우.

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        // n 입력받기
        int n = Integer.parseInt(br.readLine());
        long answer = 0;

        // 입력받기
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        // int[] arr에 채워넣기
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // prefix선언(누적합)
        int[] prefix = new int[n];
        prefix[0] = arr[0];

        for(int i = 1; i < n; i++){
            prefix[i] = prefix[i - 1] + arr[i];
        }

        // prefix값을 정리할 hashmap
        HashMap<Integer, Integer> hm = new HashMap<>();

        // hashmap 채우기(prefix배열 순회)
        for(int i = 0; i < n; i++){
            int mapValue = hm.getOrDefault(prefix[i], 0);
            hm.put(prefix[i], mapValue + 1);
        }

        // 1번 경우(prefix[i] = 0)
        for(int i : hm.keySet()){
            if(i == 0){
                answer += hm.get(i);
            }
        }

        // 2번 경우(prefix[i] = prefix[j])
        for(int i : hm.values()){
            if(i == 1){
                continue;
            }
            else{
                for(int j = 0; j < i; j++){
                    answer += j;
                }
            }
        }

        System.out.println(answer);
    }
}

// 그냥 반복문으로 연속구간의 합이 0인 곳을 찾으면 시간초과
// 테스트 케이스의 answer의 값이 21억을 넘어가므로 long으로 선언