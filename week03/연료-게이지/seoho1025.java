import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class seoho1025 {
    public static void main (String args[])throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 기름 상태를 나타내는 N개의 문자열 입력 받기

        for(int i = 0; i < n; i++){
            String fual_gauge = br.readLine(); // #은 게이지가 차있음을 의미, _언더바는 아직 연료가 차지 않음
            //1. 값을 입력 받자마자 바로 계산
            int min_idx = 0; // 배열의 0번쨰 // 반복문이 돌 때마가 초기화가 필요
            int max_idx = fual_gauge.length() - 1; // 배열의 가장 마지막

            while(min_idx <= max_idx){
                int current_idx = (min_idx + max_idx) / 2; // 이진 배열이 중간을 찾는 구간

                if(fual_gauge.charAt(current_idx) == '#'){ //현재 중간 부분이 연료가 차있다면
                    min_idx = current_idx + 1; // 한칸더 욺직여서 연료의 유무를 확인
                }else{
                    max_idx = current_idx - 1;
                }
            }
           sb.append(min_idx * 100 / fual_gauge.length()).append("%").append('\n'); // 루프 종료 시, min_idx가 "#" 게이지
        }
        System.out.println(sb);
    }
}







