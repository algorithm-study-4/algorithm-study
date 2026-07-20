class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int answer = 0;
        int[] dw = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dh = {0, 1, 1, 1, 0, -1, -1, -1};
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 1){
                    for(int k = 0; k < 8; k++){
                        int nextw = i + dw[k];
                        int nexth = j + dh[k];
                        if(0 <= nextw && nextw < n && 0 <= nexth && nexth < n && board[nextw][nexth] == 0){
                            board[nextw][nexth] = 2;
                        }
                    }
                }
            }
        }
        
        for(int[] row : board){
            for(int i : row){
                if(i == 0){
                    answer++;
                }
            }
        }
        
        return answer;
    }
}