import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//문제가 요구하는 것 : 연구원이 원하는 [Si ~ Ei] 강도에 들어가는 광물이 몇 개인지
//핵심 : 정렬하면 같은 값끼리 뭉쳐 있음 → 덩어리의 시작과 끝만 알면 뺄셈으로 개수가 나옴
//코드로 구현해야 할 것 : Si가 시작되는 위치, Ei가 끝난 다음 위치를 이진 탐색으로 찾고 빼기


public class seoho1025 {
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder(); // Q가 10만줄이라 println 반복하면 느림 → 모아서 한번에 출력

        int N = Integer.parseInt(st.nextToken()); // 광물의 개수
        int Q = Integer.parseInt(st.nextToken()); // 연구원 수

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int[] strength = new int[N]; // 상대 강도가 공백을 기준으로 주어짐

        //sort를 위해 배열의 값을 반복문으로 받음
        for(int i = 0; i < strength.length; i++){
            strength[i] = Integer.parseInt(st2.nextToken());
        }

        Arrays.sort(strength); // 배열의 크기 순대로 정렬 (이진 탐색의 전제조건)

        for(int i = 0; i < Q; i++){
            StringTokenizer st3 = new StringTokenizer(br.readLine()); // 줄마다 새로 선언 (한 줄씩 들어오니까)
            int Si = Integer.parseInt(st3.nextToken()); // 원하는 강도의 시작
            int Ei = Integer.parseInt(st3.nextToken()); // 원하는 강도의 끝

            // Si가 처음 나오는 위치 / Ei가 끝난 다음 위치를 각각 찾아서 빼기
            int start = lowerBound(strength, Si);
            int end = lowerBound(strength, Ei + 1); // Ei까지 포함하려면 Ei+1이 시작되는 곳을 봐야 함

            sb.append(end - start).append('\n');
        }

        System.out.print(sb);
    }

    // target 이상인 값이 "처음" 나오는 위치를 찾음
    static int lowerBound(int[] arr, long target){
        int min = 0;                 // 배열의 가장 첫번째
        int max = arr.length - 1;    // 배열의 가장 마지막
        int answer = arr.length;     // 못 찾으면(다 target보다 작으면) 배열 끝

        while(min <= max){
            int current_mid = (min + max) / 2; // 계속 변화하는 값이므로 반복문 안에 선언

            if(arr[current_mid] < target){
                min = current_mid + 1;   // 아직 작음 → mid 포함 왼쪽 전부 버리고 오른쪽으로
            }else{
                answer = current_mid;    // 여기가 답일 수도 있으니 일단 기록
                max = current_mid - 1;   // 근데 더 왼쪽에도 있을지 모르니 멈추지 않고 계속 좁힘
            }
        }
        return answer;
    }
}