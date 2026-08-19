import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[])throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); //1. 회의실 스케쥴의 개수
        int[][] meeting = new int[n][2];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()); // 한 줄에 다른 값을 각각 받아야 함으로
            meeting[i][0] = Integer.parseInt(st.nextToken()); // 스케쥴 시작 시간 : 사용자들에게 값 받기 = a
            meeting[i][1] = Integer.parseInt(st.nextToken()); // 스케쥴 종료 시간 : 사용자들에게 값 받기 = b
        }
        //정렬 = 오름차순을 정리를 해줌
        Arrays.sort(meeting, (meeting1, meeting2) -> {
            if(meeting1[1] != meeting2[1])return meeting1[1] - meeting2[1]; // 종료시간이 서로 다르면 종료시간 오름차슌
                return meeting1[0] - meeting2[0]; // 종료시간이 같으면 시작 시간 오름차순
        });

        //겹치지 않는 회의를 고르고 고른 갯수를 세는 과정
        int count = 0; //겹치지 않은 회의들을 카운트
        int last_meeting = Integer.MIN_VALUE; // 앞 회의가 회의실을 비운 시각
        for(int i = 0; i < n; i++){
            if(last_meeting <= meeting[i][0]){
                count++;
                last_meeting = meeting[i][1]; // 마지막 회의 시간을 현재 회의 시간 마무리되는 시간으로 적립
            }
        }
        System.out.println(count);
    }
}

// 목적: 겹치지 않게 골라서 최대 몇 개를 진행할 수 있는지 세기
// 1단계 - 정렬: 종료 시간 오름차순, 같으면 시작 시간 오름차순
//         (빨리 끝날수록 뒤에 남는 시간이 많아져서 유리)
//Array.sort를 사용하여 오름차순을 정리 => 작은값 순서대로 정리해주는 것이 중요
// 2단계 - 순회: 앞에서부터 보면서 직전에 고른 회의의 종료 시간 이후에
//         시작하는 회의만 골라 카운트하고, 종료 시간을 갱신
