import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class seoho1025 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // n개의 배열을 값
        int[] list = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) { //반복문을 통해서 사용자에게 랜덤으로 값을 받기
            list[i] = Integer.parseInt(st.nextToken()); // 띄어쓰기가 존재함으로 StringTokenizer을 사용
        }
        Arrays.sort(list);// 이진 탐색은 정렬이 되어있지 않으면 성립이 불가

        int k = Integer.parseInt(br.readLine()); // k가 주어짐
        int[] list2 = new int[k];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            list2[i] = Integer.parseInt(st2.nextToken()); // k의 배열의 숫자들이 n의 배열에 속해있는지 여부를 확인해야함
        }

        StringBuilder sb = new StringBuilder(); // 출력을 모았다가 마지막에 한 번에 찍기 위함
        for (int j = 0; j < list2.length; j++) {
            if (contains(list, list2[j])) { // 존재 여부만 판단은 메서드에 맡김
                sb.append("O");
            } else {
                sb.append("X");
            }
        }
        System.out.println(sb);
    }

    static boolean contains(int[] arr, int target) {
        int min_num = 0;                  // 탐색 구간의 시작 칸 번호
        int max_num = arr.length - 1;     // 탐색 구간의 끝 칸 번호

        while (min_num <= max_num) {      // 살펴볼 구간이 남아있는 동안
            int current_guess = (min_num + max_num) / 2; // 매번 다시 계산해야 함

            if (arr[current_guess] == target) {
                return true;              // 찾았으면 즉시 종료
            } else if (arr[current_guess] < target) {
                min_num = current_guess + 1;  // 왼쪽 절반 버림
            } else {
                max_num = current_guess - 1;  // 오른쪽 절반 버림
            }
        }
        return false;                     // 구간을 다 좁혔는데 못 찾음
    }
}