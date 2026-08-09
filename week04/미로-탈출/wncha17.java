import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    static int n, m;
    static String[] maps;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(String[] mapsInput) {
        maps = mapsInput;
        n = maps.length;
        m = maps[0].length();

        int[] start = null;
        int[] lever = null;
        int[] exit = null;

        // S, L, E의 위치를 찾음
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = maps[i].charAt(j);
                if (c == 'S') start = new int[]{i, j};
                else if (c == 'L') lever = new int[]{i, j};
                else if (c == 'E') exit = new int[]{i, j};
            }
        }

        // 1단계: S에서 L까지 최단 거리
        int distToLever = bfs(start, lever[0], lever[1]);
        if (distToLever == -1) {
            return -1; // 레버에 도달할 수 없으면 탈출 불가능
        }

        // 2단계: L에서 E까지 최단 거리
        int distToExit = bfs(lever, exit[0], exit[1]);
        if (distToExit == -1) {
            return -1; // 레버를 당긴 후 출구에 도달할 수 없으면 탈출 불가능
        }

        return distToLever + distToExit;
    }

    // start에서 시작해 (targetX, targetY)까지의 최단 거리를 BFS로 구함 (도달 불가능하면 -1)
    static int bfs(int[] start, int targetX, int targetY) {
        int[][] dist = new int[n][m];
        for (int[] row : dist) {
            java.util.Arrays.fill(row, -1); // -1은 "아직 방문 안 함"을 의미
        }

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(start);
        dist[start[0]][start[1]] = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];

            if (x == targetX && y == targetY) {
                return dist[x][y]; // 목표 지점에 도달하면 즉시 반환
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m
                        && dist[nx][ny] == -1
                        && maps[nx].charAt(ny) != 'X') {
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return -1; // 큐가 빌 때까지 목표에 도달 못 했다면 -1 반환
    }
}
