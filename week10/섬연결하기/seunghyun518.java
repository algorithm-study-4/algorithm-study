import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;

        // 이어져 있는지 확인할 배열
        boolean[] visited = new boolean[n];

        // 비용이 적은 순으로 꺼내기 위한 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        // 시작점 삽입
        pq.add(new int[] {0, 0});

        // 더이상 갈 수 없을 때까지 반복
        while(!pq.isEmpty()){
            int[] now = pq.poll();

            // 갈 곳이 연결이 안되어 있다면
            if(!visited[now[0]]){

                // 비용 추가
                answer += now[1];

                // 연결되었다 처리
                visited[now[0]] = true;

                // 새로 연결한 곳에서 연결할 수 있는 곳 큐에 넣기 
                for(int[] i : costs){
                    if(i[0] == now[0] && !visited[i[1]]){
                        pq.add(new int[] {i[1], i[2]});
                    }
                    else if(i[1] == now[0] && !visited[i[0]]){
                        pq.add(new int[] {i[0], i[2]});
                    }
                }
            }

            // 이미 연결되어 있으면 넘긴다(cycle이 생기지 않도록)
            else{
                continue;
            }
        }

        return answer;
    }
}