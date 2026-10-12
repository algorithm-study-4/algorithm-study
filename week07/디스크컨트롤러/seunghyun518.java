import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0, time = 0, completed_jobs = 0, idx = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->{
            if(a[0] != b[0]) return a[0] - b[0];
            if(a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        while(completed_jobs != jobs.length){
            while(idx < jobs.length && time >= jobs[idx][0]){
                pq.add(new int[]{jobs[idx][1], jobs[idx][0], idx});
                idx++;
            }

            if(!pq.isEmpty()){
                int[] current_jobs = pq.poll();
                time += current_jobs[0];
                completed_jobs += 1;
                answer += time - current_jobs[1];
            }
            else{
                time += 1;
            }
        }

        return answer / jobs.length;
    }
}