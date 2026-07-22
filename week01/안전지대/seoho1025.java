//1. 프로그래머스ver
class Solution {
    public int solution(int[][] board) {
        int safeZone = 0;
        boolean[][] danger = new boolean[board.length][board[0].length];
        
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board.length; c++){
                if(board[r][c] == 1){
                    for(int dr = -1; dr <= 1; dr++){
                        for(int dc = -1; dc <= 1; dc++){
                            int nr = r + dr;
                            int nc = c + dc;
                            if(nr >= 0 && nr < board.length && nc >= 0&& nc < board.length){
                                danger[nr][nc] = true;
                            }
                        }
                    }
                    
                }
            }
        }

        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board.length; c++){
                if(!danger[r][c])safeZone++;
            }
        }
        return safeZone;
    }   
}   

// 2. 이클립스 버전
// import java.io.BufferedReader;
// import java.io.InputStreamReader;

// public class bangyeonji {
//     public static void main(String[] args) throws Exception {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         // 입력: 첫 줄 n, 그다음 n줄 지도
//         int n = Integer.parseInt(br.readLine());
//         int[][] board = new int[n][n];

//         for (int r = 0; r < n; r++) {
//             String[] temp = br.readLine().split(" ");
//             for (int c = 0; c < n; c++) {
//                 board[r][c] = Integer.parseInt(temp[c]);
//             }
//         }

//         int safeZone = 0;
//         boolean[][] danger = new boolean[n][n];

//         // 1. 전체 배열 돌기
//         for (int r = 0; r < n; r++) {
//             for (int c = 0; c < n; c++) {
//                 // 2. 폭탄 위치를 만났을 경우
//                 if (board[r][c] == 1) {
//                     // 3. 폭탄 주변 칠하기
//                     for (int dr = -1; dr <= 1; dr++) {
//                         for (int dc = -1; dc <= 1; dc++) {
//                             int nr = r + dr;
//                             int nc = c + dc;
//                             // 4. 경계 체크
//                             if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
//                                 danger[nr][nc] = true;
//                             }
//                         }
//                     }
//                 }
//             }
//         }

//         // 5. 안전지대 카운트
//         for (int r = 0; r < n; r++) {
//             for (int c = 0; c < n; c++) {
//                 if (!danger[r][c]) safeZone++;
//             }
//         }

//         System.out.print(safeZone);
//     }
// }