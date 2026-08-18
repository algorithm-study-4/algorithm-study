import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 첫 줄: 배열 크기 n
        int n = Integer.parseInt(br.readLine().trim());
        
        // 둘째 줄: n개의 배열 값
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        // 셋째 줄: 찾아야 하는 개수 k
        int k = Integer.parseInt(br.readLine().trim());
        
        // 넷째 줄: k개의 찾을 값
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int target = Integer.parseInt(st.nextToken());
            
            // target이 배열에 존재하면 "O", 아니면 "X"
            sb.append(binarySearch(arr, target) ? "O" : "X");
        }
        
        System.out.println(sb);
    }
    
    // 정렬된 배열에서 target이 존재하는지 O(log N)으로 확인
    private static boolean binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1; // 탐색 범위: [left, right] (양 끝 포함)
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (arr[mid] == target) {
                return true; // 찾았으면 바로 true 반환
            } else if (arr[mid] < target) {
                // target이 mid보다 크다 → 오른쪽 절반을 탐색
                left = mid + 1;
            } else {
                // target이 mid보다 작다 → 왼쪽 절반을 탐색
                right = mid - 1;
            }
        }
        
        // left > right가 될 때까지 못 찾았다면 배열에 없는 것
        return false;
    }
}