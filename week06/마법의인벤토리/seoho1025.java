import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 광물의 개수 N개
        int W = Integer.parseInt(st.nextToken()); // 인벤토리 길이 W = 광물을 일렬로 놓은 길이

        int[] dp = new int[W + 1]; // 메모라이징을 선언 (길이가 W에 정확하게 맞췄을 때의 최대 길이를 담는 배열)
        Arrays.fill(dp, -1); // 갱신된 값(만들 수 있음)과 안 된 값(만들 수 없음)을 구분하기 위한 표시
        dp[0] = 0; // 아무것도 광물도 올려두지 않은 상태

        int[] weight = new int[N]; // 광물의 무게를 담는 배열(가로 * 세로 = 광물의 무게)
        int[] length = new int[N]; // 광물의 길이 배열
        int[] height = new int[N]; // 광물의 높이 배열
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st2.nextToken()); // 광물의 가로 : I
            int h = Integer.parseInt(st2.nextToken()); // 광물의 세로 : h
            length[i] = l; // 길이 저장
            weight[i] = l * h; // 무게 저장
            height[i] = h; // 높이 저장
        }
        // 문제의 포인트 : 광물들의 길이가 정확히 W가 될 때 길이와 무게를 함께봐야함(길이가 정확히 W가 될 때의 무게의 최대 값을 찾아야 하기 때문에)
        for (int i = 0; i < N; i++) { // 광물을 하나씩 꺼냄
            int l = length[i]; // 길이 배열에서 길이 값을 꺼냄
            int h = height[i]; // 높이 배열에서 높이 값을 꺼냄
            int w = weight[i]; // 무게 배열에서 무게 값을 꺼냄

            // 이 문제의 포인트는 광물을 눕히거나 세우는 것이 포인트
            // 1차원 DP에서만 중복되는 값을 피하기 위해서 가장 큰 값부터 작은 값 역순으로 내려오면서 확인
            for (int j = W; j >= Math.min(l, h); j--) {  // 더 짧은 쪽으로 살피면서 내려가야 지나치는 경우의 수가 없음
                //광물을 눕혀서 두는 경우
                if (j >= l && dp[j - l] != -1) {
                    dp[j] = Math.max(dp[j], dp[j - l] + w);
                }
                //광물을 세우는 경우
                if (j >= h && dp[j - h] != -1) {
                    dp[j] = Math.max(dp[j], dp[j - h] + w);
                }
            }
        }
        System.out.println(dp[W] == -1 ? 0 : dp[W]); // 정확하게 W돠는 조합이 없다면 0으로 출력 아니면 길이가 정확히 W가 되었을 때 최대값을 출력
    }
}

//일부 문제만 정답(오답)
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//import java.util.Arrays;
//
//public class Main{
//    public static void main(String args[]) throws Exception{
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken()); // 광물의 개수 N개
//        int W = Integer.parseInt(st.nextToken()); // 인벤토리 길이 W = 광물을 일렬로 놓은 길이
//
//        int[]dp =  new int[W + 1]; // 메모라이징을 선언 (광물의 정보를 받는 배열)
//        Arrays.fill(dp, -1); // 최대값 갱신을 위해서 배열에 전부 -1값으로 채워줌
//        dp[0] = 0; // 무게 0은 아무리해도 0이기 때문에 불필요한 값이므로 0으로 선언
//
//        int[]weight = new int[N]; // 광물의 무게를 담는 배열(가로 * 세로 = 광물의 무게)
//        int[]length = new int[N]; // 광물의 길이 배열
//        for (int i = 0; i < N; i++) {
//            StringTokenizer st2 = new StringTokenizer(br.readLine());
//            int l = Integer.parseInt(st2.nextToken()); // 광물의 가로 : I
//            int h = Integer.parseInt(st2.nextToken()); // 광물의 세로 : h
//            length[i] = l; // 길이 저장
//            weight[i] = l * h; // 무게 저장
//        }
//        // 길이와 무게를 함께봐야함
//        for(int i = 0; i < N; i++) { // 광물을 하나씩 꺼냄
//            for (int j = W; j >= length[i]; j--) {//
//                if (dp[j - length[i]] == -1) continue; // 이미 자리에서 제거된 값은 지나치기(이미 발판이 존재하지 않기에)
//                dp[j] = Math.max(dp[j], weight[i] + dp[j - length[i]]);// 발판의 무게 + 이번 광물의 무게 비교
//            }
//        }
//        System.out.println(dp[W] == -1 ? 0 : dp[W]); // 정확하게 W돠는 조합이 없다면 0으로 출력
//    }
//}
