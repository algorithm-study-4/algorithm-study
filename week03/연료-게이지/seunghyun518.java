import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] strArr = new String[n];

        for(int i = 0; i < n; i ++){
            strArr[i] = br.readLine();

            int start = 0;
            int end = strArr[i].length();
            int mid;

            while(start < end){
                mid = (start + end) / 2;
                if(strArr[i].charAt(mid) == '#'){
                    start = mid + 1;
                }
                else{
                    end = mid - 1;
                }
            }
            
            int fuel = start;
            System.out.println((fuel * 100) / strArr[i].length() + "%");
        }
    }
}