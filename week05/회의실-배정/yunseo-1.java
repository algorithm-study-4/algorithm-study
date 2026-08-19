import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine().trim()); // 회의 예약 개수
        
        // 각 회의를 [시작시간, 종료시간] 쌍으로 저장한다
        int[][] meetings = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
            meetings[i][1] = Integer.parseInt(st.nextToken()); // 종료 시간
        }
        
        // 종료 시간 기준으로 오름차순 정렬
        // 빨리 끝나는 회의부터 배정해야 남은 시간이 최대로 남기떄문에
        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);
        
        int count = 0;      // 진행 가능한 회의 개수
        int lastEnd = 0;    // 마지막으로 선택한 회의의 종료 시간 (기준점)
        
        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            
            // 이전 회의 종료와 동시에 다음 회의 시작 가능이기 떄문에
            // 시작시간이 마지막 종료시간과 같거나 이후면 겹치지 않는 것으로 생각한다
            if (start >= lastEnd) {
                count++;           // 이 회의는 선택
                lastEnd = end;      // 기준 시간을 이 회의의 종료 시간으로 갱신
            }
            // start < lastEnd 라면 겹치므로 그냥 넘어감 (선택 안 함)
        }
        
        System.out.println(count);
    }
}