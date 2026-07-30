class Solution {
    public int solution(int[][] board, int[] moves) {
        
        int[] basket = new int[moves.length];
        int top = 0;
        int result = 0;
        
        for(int i = 0; i < moves.length; i++){
            int c = moves[i] - 1;
            int doll = 0;    
            for(int r = 0; r < board.length; r++){
                if(board[r][c] != 0){
                    doll = board[r][c];
                    board[r][c] = 0;
                    break;
                }
            }
           //바구니 
            if(doll != 0){
                if(top > 0 && basket[top - 1] == doll){
                    top--;
                    result += 2;
                }else{
                    basket[top] = doll;
                    top++;
                }
            }
        }
        return result;
    }
}