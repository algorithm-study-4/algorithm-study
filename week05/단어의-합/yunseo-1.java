import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine().trim()); // 단어 개수
        
        // 각 알파벳(A~Z)의 가중치를 저장할 배열
        // weight[i] = i번째 알파벳(A=0, B=1, ...)이 차지하는 총 자릿값의 합
        long[] weight = new long[26];
        
        for (int i = 0; i < n; i++) {
            String word = br.readLine().trim();
            int len = word.length();
            
            // 각 글자의 자릿값 계산
            // 예: "ABC" 일때 A는 10^2=100자리, B는 10^1=10자리, C는 10^0=1자리
            for (int j = 0; j < len; j++) {
                char c = word.charAt(j);
                int placeValue = (int) Math.pow(10, len - j - 1); // 뒤에서부터 거듭제곱 계산
                weight[c - 'A'] += placeValue; // 같은 알파벳이 여러 단어에 등장하면 가중치 누적
            }
        }
        
        // 가중치를 내림차순 정렬
        // 실제로 등장하지 않은 알파벳은 가중치가 0이라 뒤로 밀림 (숫자 배정 안 해도 상관없음)
        Arrays.sort(weight); // 오름차순 정렬됨
        
        // 큰 가중치부터 9,8,7...순으로 숫자 배정하며 합산
        long answer = 0;
        int digit = 9; // 배정할 숫자 (9부터 시작해서 내려감)
        
        // weight는 오름차순 정렬이므로 뒤에서부터(가장 큰 값부터) 순회
        for (int i = 25; i >= 0 && weight[i] > 0; i--) {
            answer += weight[i] * digit; // (자릿값) × (배정 숫자)
            digit--; // 다음은 한 단계 작은 숫자 배정
        }
        
        System.out.println(answer);
    }
}