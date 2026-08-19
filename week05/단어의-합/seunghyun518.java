import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] weight = new int[26];
        String[] arr = new String[n];
        int answer = 0;
        int num = 9;

        for(int i = 0; i < n; i++){
            arr[i] = br.readLine();
        }

        for(int i = 0; i < arr.length; i++){
            int w = 1;
            for(int j = arr[i].length()-1; j >= 0; j--){
                weight[arr[i].charAt(j)-'A'] += w;
                w *= 10;
            }
        }

        Arrays.sort(weight);

        for(int i = 25; i > 0; i--){
            answer += weight[i] * num;
            num--;
        }
        System.out.println(answer);
    }
}

// 9 ~ 0 까지 할당, 맨 앞에 나오면 + 둘 다 맨 앞에 있으면 -> 그 다음 검사 -> 불가능
/* ABC => 100A + 10B + C, DEF => 100D + 10E + F
 -> A = D 가중치, B = E 가중치, C = F 가중치*/