import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;    // 행 크기
        int m = maps[0].length; // 열 크기

        // 상,하,좌,우 이동을 위한 방향벡터
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 시작점(0,0)을 큐에 넣고 시작
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 1. 맵 범위 안에 있고
                // 2. 벽이 아니며 (1이고)
                // 3. 처음 방문한 곳이라면
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx][ny] == 1) {
                    // 현재 칸까지의 거리 +1을 기록 (방문처리 겸용)
                    maps[nx][ny] = maps[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        // 우측 하단 목적지(n-1, m-1)의 값을 확인
        int answer = maps[n - 1][m - 1];
        // 만약 값이 1이라면(벽에 막혀 도달 못한 경우) -1 반환
        return answer > 1 ? answer : -1;
    }
}
