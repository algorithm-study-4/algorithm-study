import java.util.*;
import java.io.*;

public class Main{
    static int answer;
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 정답의 개수
        answer = 0;
        n = Integer.parseInt(br.readLine());

        dfs(0);

        System.out.println(answer);
    }

    // 재귀 함수
    static void dfs(int sum){
        // 종료 조건 1(sum == target인 경우)
        if(sum == n){
            answer++;
            return;
        }

        // 종료 조건 2(sum > target인 경우)
        if(sum > n){
            return;
        }

        // 종료 조건에 걸리지 않았다면 1 ~ 3을 더하는 재귀 함수
        for(int i = 1; i < 4; i++){
            dfs(sum + i);
        }
    }
}