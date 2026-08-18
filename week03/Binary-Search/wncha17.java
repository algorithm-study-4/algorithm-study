import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // n 입력
        int n = Integer.parseInt(br.readLine().trim());

        // n개의 배열 값 입력
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 이분 탐색을 위해 배열을 오름차순 정렬
        Arrays.sort(arr);

        // k 입력
        int k = Integer.parseInt(br.readLine().trim());

        // k개의 찾을 수 입력
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(arr, target) ? "O" : "X");
        }
        
        System.out.println(sb.toString());
    }

    // 이분 탐색: target이 arr에 존재하면 true, 없으면 false
    static boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 오버플로우 방지

            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                left = mid + 1; // 오른쪽 절반을 탐색
            } else {
                right = mid - 1; // 왼쪽 절반을 탐색
            }
        }

        return false; // 끝까지 못 찾음
    }
}