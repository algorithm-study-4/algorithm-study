import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        int n = jobs.length;

        // [1단계] 작업들을 '요청 시각(s)' 기준으로 오름차순 정렬
        // -> 시뮬레이션 도중 '아직 확인 안 한 작업들' 중
        // 가장 앞쪽부터 순서대로만 확인하면 되므로
        // 포인터(idx) 하나로 효율적으로 관리 가능
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        // [2단계] 대기 큐(우선순위 큐) 정렬 기준
        // 1순위: 소요시간(a[1])이 짧은 것 우선
        // 2순위: 소요시간이 같다면, 요청시각(a[0])이 빠른 것 우선
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        // 현재 시각
        int currentTime = 0;
        // jobs 배열에서 '아직 대기 큐에 넣지 않은 작업'을 가리키는 포인터
        int idx = 0;
        // 모든 작업의 반환시간의 총합을 누적, long으로 오버플로우 방지
        long totalTurnaround = 0;
        // 지금까지 처리 완료한 작업 수
        int done = 0;

        // [3단계] 모든 작업을 완료할 때까지 시뮬레이션 반복
        while (done < n) {

            // 3-1. 현재 시각까지 '이미 요청된' 모든 작업을 대기 큐에 추가
            // -> [1단계]에서 정렬해두어서 O(1)
            while (idx < n && jobs[idx][0] <= currentTime) {
                pq.add(jobs[idx]);
                idx++;
            }

            // 3-2. 만약 대기 큐가 비어있다면 (처리할 작업이 없다면)
            // 다음 작업이 요청되는 시점까지 시간을 곧바로 건너뛰기
            if (pq.isEmpty()) {
                currentTime = jobs[idx][0];
                continue;
            }

            // 3-3. 대기 큐에서 우선순위가 가장 높은 작업(소요시간이 가장 짧은 것) 꺼내 처리
            int[] job = pq.poll();
            int start = job[0];
            int duration = job[1];

            // 작업 수행
            currentTime += duration;
            // 반환시간 = 종료시각 - 요청시각
            totalTurnaround += (currentTime - start);

            done++; // 작업 하나 완료
        }

        // [4단계] 모든 작업의 반환시간 평균의 정수 부분 반환
        return (int) (totalTurnaround / n);
    }
}