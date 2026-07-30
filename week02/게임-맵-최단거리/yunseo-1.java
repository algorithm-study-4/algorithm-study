import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static final int SIZE = 101; // 맵 최대 크기(n, m 최대 100)

    public int solution(int[][] maps) {
        int answer = 0; // 최단 거리를 담을 변수. 0이면 도착 못한 것으로 처리

        // BFS용 큐. int[] 하나에 {현재 행, 현재 열, 여기까지 온 칸 수}를 담아서 관리
        Queue<int[]> q = new LinkedList<>();

        // 방문 여부 체크 배열. 0 = 안 가봄, 1 = 가봤음
        int[][] visited = new int[SIZE][SIZE];

        // 상, 하, 좌, 우로 이동할 때의 행(y), 열(x) 변화량
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        int row = maps.length;     // 전체 행의 개수
        int col = maps[0].length;  // 전체 열의 개수

        // 시작 위치 (0,0)는 이미 방문했다고 표시하고, 칸 수 1로 큐에 넣음
        visited[0][0] = 1;
        q.add(new int[]{0, 0, 1});

        // 큐가 빌 때까지 반복 (BFS 핵심 로직)
        while (!q.isEmpty()) {
            int[] cur = q.poll(); // 큐 맨 앞(가장 먼저 들어온) 칸을 꺼냄
            int curY = cur[0];      // 현재 행 위치
            int curX = cur[1];      // 현재 열 위치
            int curCount = cur[2];  // 여기까지 오는 데 걸린 칸 수

            // 디버깅용 출력: 현재 어떤 칸을 몇 번째 칸으로 지나고 있는지 확인
            System.out.println("[" + curY + ", " + curX + "] 이동칸: " + curCount);

            // 목적지(우측 하단, row-1, col-1)에 도착했다면 바로 종료
            if (curY == row - 1 && curX == col - 1) {
                answer = curCount;
                break;
            }

            // 현재 위치에서 상하좌우 네 방향을 모두 확인
            for (int i = 0; i < 4; i++) {
                int nextY = curY + dy[i];       // 이동할 다음 행
                int nextX = curX + dx[i];       // 이동할 다음 열
                int nextCount = curCount + 1;   // 한 칸 이동했으니 칸 수 +1

                // 맵을 벗어나거나, 이미 방문한 칸이면 스킵
                if (nextY < 0 || nextX < 0 || nextY >= row || nextX >= col || visited[nextY][nextX] == 1) continue;
                // 벽(0)이면 스킵
                if (maps[nextY][nextX] == 0) continue;

                // 갈 수 있는 칸이면 방문 처리하고 큐에 추가
                visited[nextY][nextX] = 1;
                q.add(new int[]{nextY, nextX, nextCount});
            }
        }

        // answer가 0이면 한 번도 목적지에 도착 못 한 것 -> -1 반환
        // 0이 아니면 그 값이 최단 칸 수
        return answer == 0 ? -1 : answer;
    }
}