import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 1. 전체 학생 수
        int count = 0; // 체육복을 한 개 이상 가지고 있는 학생 수

        String[] a = br.readLine().split(" ");
        int[] losts = new int[a.length]; // 2. 체육복을 도난당한 학생
        for (int i = 0; i < losts.length; i++) {
            losts[i] = Integer.parseInt(a[i]);
        }

        String[] b = br.readLine().split(" ");
        int[] reserve = new int[b.length]; // 3. 여벌의 체육복을 가지고 온 학생
        for (int i = 0; i < reserve.length; i++) {
            reserve[i] = Integer.parseInt(b[i]);
        }

        int[] cloths = new int[n + 2]; // 양옆을 볼 수 있도록 여유 두 칸
        for (int i = 1; i <= n; i++) cloths[i] = 1;
        for (int j : losts) cloths[j]--;    // 도난당한 옷 체크
        for (int j : reserve) cloths[j]++;  // 여유분 옷 체크

        for (int i = 1; i <= n; i++) {
            if (cloths[i] == 0) {           // 체육복이 없는 학생을 발견
                if (cloths[i - 1] == 2) {   // 왼쪽부터 확인
                    cloths[i - 1]--;        // 빌려준 학생의 옷을 줄임
                    cloths[i]++;            // 빌려입은 학생의 옷을 늘림
                } else if (cloths[i + 1] == 2) {
                    cloths[i + 1]--;
                    cloths[i]++;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (cloths[i] >= 1) count++;
        }

        System.out.println(count);
    }
}


//class Solution {
//    public int solution(int n, int[] lost, int[] reserve) {
//        int answer = 0;
//
//        int[] cloths = new int[n + 2];
//        for(int i = 1; i <= n; i++)cloths[i] = 1; //가지고 있는 배열의 값을 전부 1로 세팅
//
//        for(int j : lost)cloths[j]--; // 옷이 없는 배열의 칸은 값을 제거 해주고
//        for(int j : reserve)cloths[j]++; // 옷의 여유가 있는 배열의 칸은 값을 넣어줌
//
//        for(int i = 1; i <= n; i++){
//            if(cloths[i] == 0){ // 현재 옷을 아무것도 가지지 못한 학생을 발견
//                if(cloths[i - 1] == 2){ // 옷을 아무것도 가지지 못한 학생 왼쪽부터 옷을 두벌 가지고 있는 학생을 발견했을 경우
//                    cloths[i - 1]--; // 왼쪽의 학생에게서 옷을 빌려옴
//                    cloths[i]++; // 옷을 가지지 못한 학생에서 옷이 생김
//                }else if(cloths[i + 1] == 2){
//                    cloths[i + 1]--; // 옷을 아무것도 가지지 못한 학생 오른쪽도 확인
//                    cloths[i]++;
//                }
//            }
//        }
//
//        for(int i = 1; i <= n; i++){
//            if(cloths[i] >= 1){
//                answer++;
//            }
//        }
//
//        return answer;
//    }
//}
