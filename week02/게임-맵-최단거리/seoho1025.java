import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int[][] dist = new int[n][m];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0, 0});
        dist[0][0] = 1;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (maps[nr][nc] == 0) continue;
                if (dist[nr][nc] != 0) continue;

                dist[nr][nc] = dist[r][c] + 1;
                q.offer(new int[]{nr, nc});
            }
        }

        return dist[n-1][m-1] == 0 ? -1 : dist[n-1][m-1];
    }
}


// import java.util.*;

// class Solution {
//     public int solution(int[][] maps) {
//         int n = maps.length;
//         int m = maps[0].length;

//         int[] dr = {-1, 1, 0, 0};
//         int[] dc = {0, 0, -1, 1};

//         boolean[][] visited = new boolean[n][m];
//         Queue<int[]> q = new LinkedList<>();

//         q.offer(new int[]{0, 0, 1});
//         visited[0][0] = true;

//         while (!q.isEmpty()) {
//             int[] cur = q.poll();
//             int r = cur[0];
//             int c = cur[1];
//             int cdist = cur[2];

//             if (r == n - 1 && c == m - 1) {
//                 return cdist;
//             }

//             for (int i = 0; i < 4; i++) {
//                 int nr = r + dr[i];
//                 int nc = c + dc[i];

//                 if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
//                 if (maps[nr][nc] == 0 || visited[nr][nc]) continue;

//                 visited[nr][nc] = true;
//                 q.offer(new int[]{nr, nc, cdist + 1});
//             }
//         }
//         return -1;
//     }
// }