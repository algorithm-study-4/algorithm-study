class Solution {
    int[] board;
    int answer = 0;

    public int solution(int n) {
        // 인덱스를 행, 배열의 값을 열로 사용하는 1차원 배열
        board = new int[n];

        // 0번째 행부터 퀸 배치를 시작
        dfs(0, n);

        return answer;
    }

    /*
     * 특정 행에 퀸을 배치하는 백트래킹 메서드
     * @param row 현재 퀸을 놓으려는 행 번호
     * @param n 전체 체스판의 크기 및 놓아야 할 퀸의 개수
     */
    private void dfs(int row, int n) {
        // 모든 행에 퀸을 성공적으로 배치했다면 경우의 수 1 증가
        if (row == n) {
            answer++;
            return;
        }

        // 현재 행의 0열부터 n-1열까지 퀸을 하나씩 놓아봄
        for (int col = 0; col < n; col++) {
            board[row] = col; // 현재 행의 col 열에 퀸을 배치해본다.

            // 방금 놓은 퀸의 위치가 안전한지 검사
            if (isValid(row)) {
                // 안전하다면 다음 행에 퀸을 놓기 위해 재귀 호출
                dfs(row + 1, n);
            }
            // 안전하지 않다면 dfs를 호출하지 않고 다음 열로 넘어감 (가지치기/백트래킹)
        }
    }

    /*
     * 현재 놓은 퀸이 이전에 놓은 퀸들로부터 안전한지 확인하는 메서드
     * @param row 방금 퀸을 배치한 행 번호
     * @return 안전하면 true, 공격받는 위치면 false
     */
    private boolean isValid(int row) {
        // 0번째 행부터 바로 직전 행(row - 1)까지 검사
        for (int i = 0; i < row; i++) {
            // 1. 같은 열에 퀸이 있는지 검사
            if (board[i] == board[row]) {
                return false;
            }

            // 2. 대각선에 퀸이 있는지 검사
            // 행의 차이(절댓값)와 열의 차이가 같다면 같은 대각선 상에 있는 것
            if (Math.abs(row - i) == Math.abs(board[row] - board[i])) {
                return false;
            }
        }

        // 같은 열, 대각선 모두 통과했다면 안전한 자리
        return true;
    }
}
