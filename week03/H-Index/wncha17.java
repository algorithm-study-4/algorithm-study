import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length;

        // 인용 횟수를 내림차순으로 정렬
        Integer[] sorted = new Integer[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = citations[i];
        }

        Arrays.sort(sorted, Collections.reverseOrder());

        int answer = 0;

        // h번 이상 인용된 논문이 h편 이상인지 확인
        for (int h = 1; h <= n; h++) {
            if (sorted[h - 1] >= h) {
                answer = h;
            }
        }

        return answer;
    }
}


// class Solution {
//     public int solution(int[] citations) {
//         int n = citations.length;

//         int[] count = new int[n + 1];

//         for (int c : citations) {
//             if (c >= n) {
//                 count[n]++; // n 이상은 전부 n번 인용된 것으로 취급
//             } else {
//                 count[c]++;
//             }
//         }

//         int total = 0;
//         for (int h = n; h >= 0; h--) {
//             total += count[h];

//             if (total >= h) {
//                 return h;
//             }
//         }

//         return 0;
//     }
// }