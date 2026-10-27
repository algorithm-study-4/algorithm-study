import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;

        // 지워진 블록이 있는가 -> 있으면 true = 또 지워질 블록이 있는지 검사
        boolean isClear = true;

        // String[]은 변경이 어려워서 char[][]로 변경
        char[][] map = new char[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = board[i].charAt(j);
            }
        }

        // 지워질 블록 표시 true면 지운다
        boolean[][] match = new boolean[m][n];

        // 더 이상 변경이 없을 때까지 반복
        while(isClear){
            isClear = false;
            match = new boolean[m][n];

            // 보드 전체 검사(매치가 있는지)
            for(int i = 0; i < m - 1; i++){
                for(int j = 0; j < n - 1; j++){

                    // 매치가 있는지 만들어둔 check함수 돌리기
                    boolean isMatched = check(map, i, j);

                    // 매치가 있다면 처리(지울 블록이다 표시(match[][]), 변경이 있었음 처리(isClear)
                    if(isMatched){
                        match[i][j] = true;
                        match[i + 1][j] = true;
                        match[i][j + 1] = true;
                        match[i + 1][j + 1] = true;
                        isClear = true;
                    }
                }
            }

            // match[][] 돌면서 정답 + 1, map[][]빈칸으로 변경
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(match[i][j]){
                        answer += 1;
                        map[i][j] = '.';
                    }
                }
            }

            // map을 돌면서 밑 칸이 빈 칸인지 검사(빈 칸이면 아래로 낙하)
            for(int i = m - 2; i >= 0; i--){
                for(int j = 0; j < n; j++){
                    int now = i;
                    while(now + 1 < m && map[now + 1][j] == '.'){
                        map[now + 1][j] = map[now][j];
                        map[now][j] = '.';
                        now += 1;
                    }
                }
            }
        }

        return answer;
    }

    // 매치가 있는지 검사하는 함수
    public boolean check(char[][] ch, int row, int col){
        char ch1 = ch[row][col];
        char ch2 = ch[row + 1][col];
        char ch3 = ch[row][col + 1];
        char ch4 = ch[row + 1][col + 1];

        if(ch1 == '.'){
            return false;
        }

        else if(ch1 == ch2 && ch2 == ch3 && ch3 == ch4){
            return true;
        }

        else{
            return false;
        }
    }
}