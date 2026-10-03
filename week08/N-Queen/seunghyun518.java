class Solution {
    int[][] attacked;
    int n;
    int answer = 0;

    public int solution(int n) {
        attacked = new int[n][n];
        this.n = n;
        dfs(0);
        return answer;
    }

    void dfs(int row){

        // 모든 퀸을 놓았으면
        if(row == n){
            answer++;
            return;
        }

        for(int i = 0; i < n; i++){
            // 퀸을 놓을 수 있으면
            if(attacked[row][i] == 0){
                // 놓을 퀸의 공격 칸 처리
                for(int r = row + 1; r < n; r++){
                    int k = r - row;

                    // 해당 행 공격
                    attacked[r][i] += 1;

                    // 대각선 공격(왼쪽, 오른쪽)
                    if(i - k >= 0) attacked[r][i-k] += 1;
                    if(i + k < n) attacked[r][i + k] += 1;
                }

                // 재귀함수
                dfs(row + 1);

                // 백트래킹
                for(int r = row + 1; r < n; r++){
                    int k = r - row;
                    attacked[r][i] -= 1;
                    if(i - k >= 0) attacked[r][i-k] -= 1;
                    if(i + k < n) attacked[r][i + k] -= 1;
                }
            }
        }
    }
}