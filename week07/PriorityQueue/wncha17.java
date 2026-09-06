import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        
        // 최대값을 우선적으로 꺼내는 PriorityQueue (역순 정렬)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int val = Integer.parseInt(st.nextToken());
            
            if (cmd.equals("push")) {
                pq.add(val);
            } else if (cmd.equals("pop")) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < val; j++) {
                    int top = pq.poll(); // 현재 최댓값을 꺼냄
                    if (j > 0) {
                        line.append(" ");
                    }
                    line.append(top);
                }
                sb.append(line).append("\n"); // pop 결과 출력 후 개행
            } else if (cmd.equals("add")) {
                int top = pq.poll(); // 데이터 하나를 꺼내서
                pq.add(top + val);   // C를 더한 뒤 다시 넣음
            }
        }
        
        System.out.print(sb);
    }
}