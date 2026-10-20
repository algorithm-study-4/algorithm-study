import java.util.*;
import java.io.*;

/*
구간의 합 == 구간의 길이
 -> prefix[i] - prefix[j] == i - j 일때 answer++
 -> prefix[i] - i == prefix[j] - j

합이 0이 되는 연속구간 세기(prefix[i] == prefix[j])의 변형
 -> HashMap의 key를 prefix[i] -> prefix[i] - i로 바꾸면 같은 방식으로 풀이 가능
*/

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // n입력받기
        int n = Integer.parseInt(br.readLine());

        long answer = 0;
        int[] arr = new int[n];
        int[] prefix = new int[n + 1];
        String s = br.readLine();

        HashMap<Integer, Integer> hm = new HashMap<>();

        // 입력 int배열로 정리하기
        for(int i = 0; i < s.length(); i++){
            arr[i] = s.charAt(i) - '0';
        }

        // prefix(누적합) 채워넣기(prefix[0] = 0, prefix[n] = 0 ~ n인덱스 까지의 합)
        for(int i = 1 ; i <= n; i++){
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }

        // 해시맵에 prefix[i] - i = key, 나온 횟수 = value로 넣기
        for(int i = 0; i <= n; i++){
            int count = hm.getOrDefault(prefix[i] - i, 0);
            hm.put(prefix[i] - i, count + 1);
        }

        // 정답 개수 세기
        for(int i : hm.values()){
            for(int j = i - 1; j > 0; j--){
                answer += j;
            }
        }

        System.out.println(answer);
    }
}

// answer는 long으로 선언