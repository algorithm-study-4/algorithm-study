import java.io.*;
import java.util.*;

public class Main{

    // 전역변수 선언
    static int answer = 0, c, b;
    static int[] cal;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        c = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        String[] arr = br.readLine().split(" ");
        cal = new int[b];
        int idx = 0;
        for(String s : arr){
            cal[idx++] = Integer.parseInt(s);
        }

        // visited 초기화
        visited = new boolean[b];

        // 재귀함수 시작
        dfs(0);

        System.out.println(answer);
    }

    // 재귀함수
    static void dfs(int num){
        // 정답
        answer = Math.max(num, answer);

        for(int i = 0; i < b; i++){
            // 방문하지 않았고 && 다음 음식을 먹어도 칼로리가 초과하지 않으면
            if(!visited[i] && num + cal[i] <= c){
                visited[i] = true;
                dfs(num + cal[i]);
                visited[i] = false;
            }
        }
    }
}