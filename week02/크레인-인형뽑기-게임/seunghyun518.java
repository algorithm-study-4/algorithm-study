import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {

        int answer = 0;
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < moves.length; i++){ // 크레인의 이동
            for(int j = 0; j < board.length; j++){ // 뽑을 위치의 맨 위에서부터

                if(board[j][moves[i] - 1] != 0){ // 인형이 있다면
                    // 현재 뽑은 인형 != 바구니 맨 위 인형 or 바구니가 비어있으면
                    if((st.peek() != board[j][moves[i] -1) || st.isEmpty()){
                        st.push(board[j][moves[i] -1]); // 바구니에 인형을 넣음
                        board[j][moves[i]-1] = 0; // 방금 뽑은 인형의 위치 없애줌
                        break; // 다음 뽑기로 넘어감
                    }                    
                    else{ // 현재 뽑은 인형 = 바구니 맨 위 인형
                        board[j][moves[i]-1] = 0;
                        answer += 2;
                        st.pop();
                        break; // 다음 뽑기로 넘어감
                    }
                }
            }
        }
        
        return answer;
    }
}