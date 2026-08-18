import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[])throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 사용자에게 값을 입력받기
        int[] coinTypes = {500,100,50,10};
        int count = 0; // 최소 동전 갯수를 세는 변수

        for(int i = 0; i < 4; i++){
            count += n / coinTypes[i]; // 가장 큰 수부터 나눠주기
            n %= coinTypes[i];
        }
        System.out.println(count);
    }
}