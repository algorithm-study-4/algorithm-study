import java.util.*;
import java.io.*;

/*
사전순으로 가장 빠른 문자열을 출력하라
-> 만약 같은 글자가 나온다면 앞, 뒤에서 전진하면서 다음 글자 비교
 */
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        // 투 포인터 앞에서부터 전진, 뒤에서부터 전진
        int left = 0, right = n - 1;
        int nleft, nright;

        // 문자열S 입력받기
        char[] c = new char[n];
        for(int i = 0 ; i < n; i++){
            c[i] = br.readLine().charAt(0);
        }

        // 문자열S를 전부 순회할 때까지 반복
        while(left <= right){

            // 만약 가운데에서 만났다면 추가하고 끝
            if(left == right){
                sb.append(c[left++]);
            }

            // 만약 같은 글자가 나왔다면
            else if(c[left] == c[right]){

                // 앞에서 전진, 뒤에서 후진
                nleft = left + 1;
                nright = right - 1;

                // 다른 글자가 나올 때까지
                while(nleft < nright && c[nleft] == c[nright]){
                    nleft = nleft + 1;
                    nright = nright - 1;
                }

                // 사전순으로 더 작은 글자 정답에 추가
                if(c[nleft] < c[nright]){
                    sb.append(c[left++]);
                }
                else{
                    sb.append(c[right--]);
                }
            }

            // 다른 글자가 나왔다면 사전순으로 더 작은 글자 정답에 추가
            else{
                if(c[left] < c[right]){
                    sb.append(c[left++]);
                }
                else{
                    sb.append(c[right--]);
                }
            }
        }

        // 80글자마다 줄바꿈 추가
        for (int i = 80; i < sb.length(); i += 81) {
            sb.insert(i, '\n');
        }
        System.out.println(sb);
    }
}