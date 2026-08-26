import java.io.BufferedReader;
import java.io.InputStreamReader;

// [Bottom-Up / 반복문]
//작은 값부터 순서대로 채우므로, 필요한 값이 이미 있는 게 보장됨 → 존재 확인 불필요
public class Main {
    public static void main(String args[]) throws Exception{
        final int mod = 1_000_000_007;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 사용자한 때 정수 N을 받음
        long[] memo = new long[n + 1]; // DP가 기억할 메모라이징 배열 만들기
        //피보나치 수열의 1과 2 의 값은 1로 정해져 있음 = 메모라이징으로 계산이 불가함으로 값을 부여함
        memo[1] = 1;
        memo[2] = 1;

        for(int i = 3; i <= n; i++){ //수열의 1과 2의 값이 1로 정해져 있으므로 반복문의 i값은 3으로 설정
            memo[i] = (memo[i - 1] + memo[i - 2]) % mod; // 피보나치 수열의 정의 : 첫째, 둘 째 항이 1이고, 그 뒤의 모든 항은 바로 앞 두 항의 합을 합친 수열
        }
        System.out.println(memo[n]);
    }
}

// 피보나치 수열을 DP 로 구현 할 때 중요한 점
// [Bottom-Up / 반복문]
// [Top-Down / 재귀]
// 호출 순서를 알 수 없으므로 memo에 값이 있는지 확인 후 없으면 계산
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//public class Main{
//
//    static final int mod = 1_000_000_007;
//    static long[] memo;
//
//    public static void main(String args[])throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int n = Integer.parseInt(br.readLine()); // 시용자한테 값을 입력받기
//
//        memo = new long[n + 1]; // 메모라이징 배열 생성
//
//        // 피보나치 수열은 첫째, 둘째항은 1로 정해져 있고 다음 항부터 모든 항은 바로 앞 두 항의 합인 수열 이므로 미리 선언
//        memo[1] = 1;
//        memo[2] = 1;
//
//        System.out.println(fibo(n));
//    }
//    static long fibo(int n){
//        if(n <= 2) return  1;
//        if(memo[n] != 0) return memo[n];
//        return memo[n] = (fibo(n - 1) + fibo(n - 2)) % mod;
//    }
//}


//한번보면 좋을 것 같은 피보나치 코드
//class FiboRecursion{
//    private static int input = 100;
//    if(n == 1 || n == 2){
//        return 1;
//    }
//    return fiboRecursion(n - 1) + fiboRecursion(n - 2);
//}
//public static void main(String args[])throws Excepion{
//    input = 20;
//    System.out.println(fiboRecursion(input));
// }
//}