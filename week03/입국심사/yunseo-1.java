class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        // 심사관 중 가장 느린 사람 기준으로 상한선을 잡음
        // (모든 사람을 한 심사관이 순서대로 처리한다면 걸리는 최대 시간)
        long left = 1;
        long right = 1_000_000_000L * n; // 최악의 경우: 가장 느린 심사관(최대 10억분) × n명
        
        while (left <= right) {
            long mid = left + (right - left) / 2; // 오버플로 방지용 계산 방식
            
            // mid분 동안 몇 명을 심사할 수 있는지 확인
            long count = 0;
            for (int time : times) {
                count += mid / time; // 이 심사관이 mid분 동안 처리 가능한 인원수
                
                // n명 이상 처리 가능하면 더 볼 필요 없음 (오버플로 방지 + 최적화)
                if (count >= n) break;
            }
            
            if (count >= n) {
                // mid분이면 충분히 n명을 처리할 수 있다
                // → 더 짧은 시간으로도 가능한지 확인해보자 (답의 후보로 저장)
                answer = mid;
                right = mid - 1;
            } else {
                // mid분으로는 부족하다 → 시간을 늘려야 함
                left = mid + 1;
            }
        }
        
        return answer;
    }
}