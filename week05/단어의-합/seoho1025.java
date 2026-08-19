import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String args[])throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 단어의 개수
        int[] weight = new int[26]; // 알파벳 배열

        for(int i = 0; i < N; i++){
            String str = br.readLine(); // 단어가 주어짐
            for(int j = 0; j < str.length(); j++){
                int idx = str.charAt(j) - 'A'; // str에서 하나하나씩 char을 꺼내서 알파벳으로 전환
                int pow = str.length() - 1 - j; // 이 문자가 몇 번째 자리인지 확인
                weight[idx] += (int) Math.pow(10, pow); // 같은 알파벳이 나오면 자리값에 누적
            }
        }

        Arrays.sort(weight); // 오름차순 정렬 정렬
        int sum = 0; // 최종 합계
        int num = 9; // 배정할 숫자 가장 큰 수부터 배정하기 위해
        for(int i = 25; i >= 0 && num >= 0; i--){ // 오름차순으로 정렬 되었으므로 가장 높은 수부터
            sum += weight[i] * num; // 자리값이 큰 알파벳에 큰 수를 곱해서 더함
            num--; // 다음 알파벳에는 하나 작은 숫자를 배정
        }
        System.out.println(sum);
    }
}
// 숫자를 배정해서 최대 합을 만드는 것이 목표 가장 앞자리부터 가장 큰수를 배정(0 , 9)까지
// 첫째줄 + 아랫줄 = 합산값이 출력되어야함