class Solution {
    public long solution(int n, int[] times) {
        // 이분 탐색 범위 설정
        long left = 1; // 최소 시간 (가장 빠른 심사관이 1명 심사하는 시간)
        long right = (long) 1_000_000_000 * 1_000_000_000; // 최대 시간 (가장 느린 심사관이 n명을 모두 심사)

        long answer = right;

        while (left <= right) {
            long mid = left + (right - left) / 2; // 현재 확인할 시간

            // mid분 동안 모든 심사관이 심사할 수 있는 총 인원 수 계산
            long count = 0;
            for (int time : times) {
                count += mid / time;
                // 인원이 이미 n을 넘었으면 더 계산할 필요 없음 (오버플로우 방지 겸 최적화)
                if (count >= n) {
                    break;
                }
            }

            if (count >= n) {
                // mid분이면 n명을 모두 심사할 수 있음 -> 더 짧은 시간도 가능한지 확인
                answer = mid;
                right = mid - 1;
            } else {
                // mid분으로는 부족함 -> 더 긴 시간이 필요
                left = mid + 1;
            }
        }

        return answer;
    }
}