import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(arr);

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int min = Integer.parseInt(st.nextToken());
            int max = Integer.parseInt(st.nextToken());

            int start = 0;
            int end = arr.length;
            int mid;

            while(start != end){
                mid = (start + end) / 2;

                if(arr[mid] >= min){
                    end = mid;
                }
                else{
                    start = mid + 1;
                }

            }
            int minIndex = start;

            start = 0;
            end = arr.length;

            while(start != end){
                mid = (start + end) / 2;

                if(arr[mid] > max){
                    end = mid;
                }
                else {
                    start = mid + 1;
                }
            }
            int maxIndex = start;

            System.out.println(maxIndex - minIndex);
        }
    }
}
