import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

class Solution {
    public int[] solution(String[] maps) {
        int n = maps.length; // 행 크기
        int m = maps[0].length(); // 열 크기

        boolean[][] visited = new boolean[n][m];
        List<Integer> islandSums = new ArrayList<>();

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 바다이거나 이미 방문한 칸이면 건너뜀
                if (maps[i].charAt(j) == 'X' || visited[i][j]) {
                    continue;
                }

                // 새로운 섬을 발견 -> BFS로 이 섬 전체를 탐색하며 합을 구함
                int sum = 0;
                Deque<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{i, j});
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    int x = curr[0];
                    int y = curr[1];

                    // 현재 칸의 숫자를 합에 더함
                    sum += maps[x].charAt(y) - '0';

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (nx >= 0 && nx < n && ny >= 0 && ny < m
                                && !visited[nx][ny]
                                && maps[nx].charAt(ny) != 'X') {
                            visited[nx][ny] = true;
                            queue.add(new int[]{nx, ny});
                        }
                    }
                }

                islandSums.add(sum);
            }
        }

        // 섬이 하나도 없는 경우
        if (islandSums.isEmpty()) {
            return new int[]{-1};
        }

        // 오름차순 정렬
        Collections.sort(islandSums);

        int[] answer = new int[islandSums.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = islandSums.get(i);
        }

        return answer;
    }
}
