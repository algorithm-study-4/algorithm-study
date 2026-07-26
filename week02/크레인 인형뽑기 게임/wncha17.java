import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int n = board.length;
        Stack<Integer> basket = new Stack<>();
        // 각 열에서 다음에 뽑을 행의 시작 위치를 추적
        int[] topRow = new int[n + 1];

        for (int move : moves) {
            int col = move - 1;
            for (int row = topRow[move]; row < n; row++) {
                if (board[row][col] != 0) {
                    int doll = board[row][col];
                    board[row][col] = 0; // 뽑아낸 위치 비우기
                    topRow[move] = row + 1; // 다음 탐색은 한 칸 아래부터

                    // 바구니(스택) 맨 위와 방금 뽑은 인형이 같은 모양이라면
                    if (!basket.isEmpty() && basket.peek() == doll) {
                        basket.pop();
                        answer += 2;
                    } else {
                        basket.push(doll);
                    }
                    break; // 해당 move에서 인형 하나 처리 완료
                }
            }
        }

        return answer;
    }
}