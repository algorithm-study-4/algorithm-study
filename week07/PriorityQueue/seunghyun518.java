import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 명령어 개수 n입력받기
        int n = Integer.parseInt(br.readLine());

        // Java의 Priority Queue 라이브러리 사용
        // Integer 타입의 값을 저장
        // 우선순위는 값이 큰 값부터 높은 우선순위
        // Java Priority Queue 라이브러리는 기본적으로 작은 값이 우선순위 높음
        // 그래서 Collections.reverseOrder() -> 역순(문제와 같이 큰 값 = 높은 우선순위)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // 명령어 개수만큼(= n번) 반복
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            // 명령어가 push가 들어오면
            if(cmd.equals("push")){
                int a = Integer.parseInt(st.nextToken());
                // priority queue에 추가
                pq.add(a);
            }

            // 명령어가 pop이 들어오면
            else if(cmd.equals("pop")){
                int b = Integer.parseInt(st.nextToken());
                // b개만큼 빼면서 출력할거니까 b번 반복
                for(int j = 0; j < b; j++){
                    // priority queue에서 빼서 출력
                    System.out.print(pq.poll());
                    System.out.print(" ");
                }
                // 마지막 개행
                System.out.println();
            }

            // 명령어가 add가 들어오면
            else{
                int c = Integer.parseInt(st.nextToken());
                // priority queue에서 우선순위가 가장 높은 수(가장 큰 수, 라이브러리가 자동으로 관리)에 입력값 더하기
                int val = pq.poll() + c;
                // 다시 queue에 넣기
                pq.add(val);
            }
        }
    }
}