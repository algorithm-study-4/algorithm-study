import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        String answer = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(arr);

        int k = Integer.parseInt(br.readLine());
        int[] num = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        for(int i: num){
            boolean isValid = false;
            int start = 0;
            int end = n - 1;
            int mid = (start + end) / 2;
            while(start <= end){
                if(arr[mid] == i){
                    isValid = true;
                    break;
                }
                else if(arr[mid] < i){
                    start = mid + 1;
                    mid = (start + end) / 2;
                }
                else{
                    end = mid - 1;
                    mid = (start + end) / 2;
                }
            }
            answer += isValid ? "O" : "X";
        }
        System.out.println(answer);
    }
}
