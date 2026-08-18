import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[])throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int limit = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = st.countTokens();          // 토큰 개수 = 사람 수
        int[] people = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(people); // 오름차순 정렬로 정렬
        int min = 0; // 가장 작은 몸무게 값
        int max = people.length - 1; // 가장 높은 몸무게 값
        int count = 0; // 보트 수

        while(min <= max){
            if(limit >= people[min] + people[max]){ //가장 무거운 사람과 가장 가벼운 사람의 합의 봐야함
                count++; // 보트 개수는 카운트
                min++; // 다음 가벼운 사람으로 이동
                max--; // 다음으로 덜 무거운 사람으로 이동
            }else{
                count++;// 모든 사람을 태워야 하므로 보트 개수는 카운트
                max--; // 무거운 사람이 혼자 보트를 타고 떠났으므로 빼주기
            }
        }
        System.out.println(count);

    }
}
// 문제의 포인트
// 가장 무거운 사람은 어떻게 할 것인가
// 가장 가벼운 사람과 가장 무거운 사람을 매칭 해보고 안되면 보내주기
//Arrays Sort 를 사용하면 어떨까?
// 한번 탑승가능인원 최대 2명