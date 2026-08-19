import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;

        int n = Integer.parseInt(br.readLine());
        
        answer += n / 500;
        n = n % 500;

        answer += n / 100;
        n = n % 100;

        answer += n / 50;
        n = n % 50;

        answer += n / 10;

        System.out.println(answer);
    }
}
