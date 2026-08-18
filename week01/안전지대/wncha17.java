class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        // 위험 지역을 표시할 2차원 배열
        boolean[][] isDanger = new boolean[n][n];

        // 8방향 탐색을 위한 상대 좌표
        int[] dx = {0, 0, 1, 1, 1, 0, -1, -1, -1};
        int[] dy = {0, 1, 1, 0, -1, -1, -1, 0, 1};

        // 1. board를 순회하며 지뢰(1) 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // 지뢰 발견 시 (지뢰 + 8방향) 위험 지역 설정
                if (board[i][j] == 1) {
                    for (int d = 0; d < 9; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        // 보드 범위 넘어가지 않는지 확인
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                            isDanger[nx][ny] = true;
                        }
                    }
                }
            }
        }

        // 2. 위험 지역이 아닌(false) 안전한 지역의 개수 세기
        int safeCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isDanger[i][j]) {
                    safeCount++;
                }
            }
        }

        return safeCount;
    }
}
