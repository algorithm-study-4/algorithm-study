import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입
        StringBuilder sb = new StringBuilder();  // 출
        
        // 첫 줄: 광물 개수 N, 연구원 수 Q
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        
        // 두 번째 줄: N개의 광물 강도
        int[] minerals = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            minerals[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(minerals);
        
        // Q개의 쿼리 처리
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 시작 강도 Si
            int e = Integer.parseInt(st.nextToken()); // 끝 강도 Ei
            
            // s보다 작은 값들의 개수 (s가 시작되는 위치, lower bound)
            int lower = lowerBound(minerals, s);
            
            // e보다 큰 값이 시작되는 위치 (e까지 포함하는 upper bound)
            int upper = upperBound(minerals, e);
            
            // [S, E]에 포함되는 광물 개수 = upper - lower
            sb.append(upper - lower).append("\n");
        }
        
        System.out.print(sb);
    }
    
    // target 값이 처음 등장할 수 있는 위치(target보다 작은 값의 개수) 반환
    // 즉, target 이상인 첫 번째 인덱스
    private static int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left; // target보다 작은 원소의 개수 = target이 들어갈 위치
    }
    
    // target보다 큰 값이 처음 등장하는 위치 반환
    // 즉, target 초과인 첫 번째 인덱스 (target까지 포함한 개수를 구할 때 사용)
    private static int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left; // target 이하인 원소의 개수
    }
}