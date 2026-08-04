import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // scanner 대신 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine().trim()); // 입력받을 문자열 개수
        
        StringBuilder sb = new StringBuilder(); // System.out.print 대신 StringBuilder로 한 번에 출력
        
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            
            int totalLength = line.length(); // 전체 칸 수
            int filledCount = 0;              // '#' (기름 채워진 칸) 개수
            
            // charAt으로 한 글자씩 순회 
            for (int j = 0; j < totalLength; j++) {
                if (line.charAt(j) == '#') {
                    filledCount++;
                }
            }
            
            // 소수점은 버리기
            int percent = (filledCount * 100) / totalLength;
            
            sb.append(percent).append("%\n"); // 정수를 문자열로 변환
        }
        
        System.out.print(sb);
    }
}