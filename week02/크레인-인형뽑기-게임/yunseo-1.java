import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int n = board.length;
        // 각 열마다 스택으로 사용 (board는 위에서부터 저장되어 있으므로 인덱스 0이 맨 위)
        Deque<Integer>[] columns = new ArrayDeque[n];
        for (int i = 0; i < n; i++) {
            columns[i] = new ArrayDeque<>();
        }
        
        // board를 열 단위 스택으로 변환 (아래쪽이 스택의 바닥, 위쪽이 스택의 top)
        // board[0]이 맨 위 행이므로, 아래 행부터 넣어야 위에서부터 꺼낼 수 있음
        for (int row = n - 1; row >= 0; row--) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] != 0) {
                    columns[col].push(board[row][col]);
                }
            }
        }
        
        Deque<Integer> basket = new ArrayDeque<>();
        int popCount = 0;
        
        for (int move : moves) {
            int colIndex = move - 1;
            
            if (columns[colIndex].isEmpty()) {
                continue; // 인형이 없으면 아무 일도 안 일어남
            }
            
            int doll = columns[colIndex].pop();
            
            if (!basket.isEmpty() && basket.peek() == doll) {
                basket.pop(); // 같은 모양이면 터뜨림
                popCount += 2;
            } else {
                basket.push(doll);
            }
        }
        
        return popCount;
    }
}