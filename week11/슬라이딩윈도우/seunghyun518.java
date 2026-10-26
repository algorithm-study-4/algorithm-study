import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
        int t = Integer.parseInt(br.readLine());
        int testcase = 0;

        while(testcase++ < t){
            st = new StringTokenizer(br.readLine());

            // 출력 형식 맞추기
            String answer = "#" + testcase;

            // 음수도 나올 수 있으므로
            int maxNum = -2000000000;
            int maxIdx = 0;

            int n = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // left부터 right(left + w)까지 arr[i] 값 더해서 최댓값 + 인덱스 찾기
            for(int left = 0; left <= arr.length - w; left++){
                int right = left + w;
                int answerNum = 0;
                for(int i = left; i < right; i++){
                    answerNum += arr[i];
                }

                if(answerNum > maxNum){
                    maxNum = answerNum;
                    maxIdx = left;
                }

            }

            // 출력 형식 맞추기
            answer += " " + maxIdx + " " + (maxIdx + w - 1) + " " + maxNum;
            System.out.println(answer);
        }
    }
}