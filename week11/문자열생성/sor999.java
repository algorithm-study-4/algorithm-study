/*
문자열 양 끝단에서 문자를 하나씩 선택해서 사전순으로 가장 빠른 문자열을 만들기

풀이:
1. s, e 포인터를 양 끝에 배치
2. 양 끝 문자 비교해 사전순으로 더 빠른 문자 선택
3. 같을 경우 안쪽 문자 비교
4. 왼쪽 선택 -> s++ / 오른쪽 선택 e--
5. 모든 문자를 선택할 때(s <= e 동안) 반복

알고리즘: 투포인터
시간복잡도: O(n^2)
*/
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문자열 길이 n
        int n = Integer.parseInt(br.readLine());

        // 입력받은 문자들을 저장
        char[] arr = new char[n];

        for (int i = 0; i < n; i++){
            arr[i] = br.readLine().charAt(0);
        }

        int s = 0; // 문자열 시작
        int e = n - 1; // 문자열 끝
        int cnt = 0; // 문자 개

        StringBuilder sb = new StringBuilder();

        while(s <= e){
            // 왼쪽 문자 비교
            int l = s;
            // 오른쪽 문자 비교
            int r = e;

            // 양 끝 문자가 같으면 안쪽 문자 비교
            while(l < r && arr[l] == arr[r]){
                l++;
                r--;
            }

            // 포인터 만남/교차 -> 왼쪽
            // 아니면 -> 사전순으로 빠른 문
            if(l >= r || arr[l] < arr[r]){
                // 왼쪽 끝 문자 추가
                sb.append(arr[s]);

                // 왼쪽 포인터 오른쪽으로 한 칸 이동
                s++;
            }else{
                // 오른쪽 끝 문자 추가
                sb.append(arr[e]);

                // 오른쪽 포인터 왼쪽으로 한 칸 이동
                e--;
            }

            // 문자 개수 증
            cnt++;

            // 문자 80자마다 줄바꿈
            if (cnt % 80 == 0) {
                sb.append("\n");
            }
        }

        // 결과 한 번에 출력
        System.out.println(sb);
    }
}
