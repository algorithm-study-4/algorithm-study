import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int k = Integer.parseInt(br.readLine()); // 몇개의 숫자를 삭제 할 것인지 사용자들에게 값을 받음
        String number = br.readLine(); // 숫자를 입력받음
        int n = number.length(); // 문자열의 범위

        int start = 0;
        for(int i = n - k; i >= 1; i--){// 1. n - k는 문자열의 범위 - 제거해야하는 수 2. 범위를 설정해야하므로 1보다 커야하고
            int end = n - i;
            char maxString = 0; // 가장 큰 변수를 만듬
            int maxIndex = start; // 인덱스를 담을 변수 선언

            for(int j = start; j <= end; j++){
                char c = number.charAt(j);
                if(c > maxString){
                    maxString = c;
                    maxIndex = j;
                }
            }
            sb.append(maxString);
            start = maxIndex + 1;
        }
        System.out.println(sb);
    }
}

//class Solution {
//    public String solution(String number, int k) {
//        StringBuilder sb = new StringBuilder();
//        int n = number.length(); // 문자열의 범위
//
//        int start = 0;
//        for (int i = n - k; i >= 1; i--) { // i = 앞으로 채워야 할 자릿수
//            int end = n - i;
//            char maxString = 0; // 가장 큰 값을 담을 변수
//            int maxIndex = start; // 인덱스를 담을 변수
//
//            for (int j = start; j <= end; j++) {
//                char c = number.charAt(j);
//                if (c > maxString) {
//                    maxString = c;
//                    maxIndex = j;
//                }
//            }
//            sb.append(maxString);
//            start = maxIndex + 1;
//        }
//        return sb.toString();
//    }
//}