
/*
 * [시간복잡도]
 * 하루마다 넣기,빼기: HashMap 연산 O(1)
 * 하루마다 일치 확인: 원하는 제품 수 w(<= 10)번 비교 -> O(w)
 * 전체: O(n * w), n = discount 길이 <= 10만 -> 최대 약 100만 번
*/

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {

        final int DAYS = 10;    // 회원 자격 기간 = 윈도우 크기
        int answer = 0;

        // 현재 윈도우(연속 10일) 안에 있는 제품별 개수
        // 예) {"banana": 3, "apple": 2, ...}
        Map<String, Integer> window = new HashMap<>();

        for (int i = 0; i < discount.length; i++) {

            // (1) 오늘(i번째 날) 할인 제품을 윈도우에 추가
            // getOrDefault: 처음 보는 제품이면 0에서 시작
            window.put(discount[i], window.getOrDefault(discount[i], 0) + 1);

            // (2) 윈도우가 10일을 넘었다면, 가장 오래된 날을 윈도우에서 제거
            // i번째 날을 넣었을 때 윈도우는 [i-9, i]여야 하므로
            // 11일 전인 (i-10)번째 날은 빠져야 함
            if (i >= DAYS) {
                String old = discount[i - DAYS];
                window.put(old, window.get(old) - 1);
            }

            // (3) 윈도우가 꽉 찼다면(10일), 원하는 조건과 일치하는지 확인
            // i가 9 이상이 되어야 처음으로 10일치가 모임
            // 이때 윈도우는 (i-9)번째 날에 가입한 경우의 10일
            if (i >= DAYS - 1 && matches(window, want, number)) {
                answer++;
            }
        }

        return answer;
    }

    // 윈도우 안에 원하는 제품이 모두 원하는 개수만큼 있는지 확인
    private boolean matches(Map<String, Integer> window, String[] want, int[] number) {
        for (int j = 0; j < want.length; j++) {
            // 윈도우에 없는 제품이면 0개로 취급
            // numbers의 합이 10 = 윈도우 크기이므로,
            // 모든 제품이 "원하는 개수 이상"이면 곧 "정확히 일치"
            if (window.getOrDefault(want[j], 0) < number[j]) {
                return false;     // 하나라도 부족하면 이 날은 가입하지 않음
            }
        }
        return true;
    }
}
