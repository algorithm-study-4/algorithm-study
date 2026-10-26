import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[6][6];
        int[] next;
        int n = Integer.parseInt(br.readLine());

        for(int j = 0; j < n; j++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int location = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();

            // 첫 칸 농도 1로 색칠하기
            map[5][location] = 1;

            // 농도 변수
            int den = 2;

            // 위로 가는 반복문
            for(int i = 4; i >= 0; i--){

                // 방향이 R이라면
                if(direction.equals("R")){

                    // 다음 칸은 위 + 오른쪽으로 전진
                    next = new int[] {i, location + 1};

                    // 만약 맵을 벗어난다면 -> (튕겨나감 + 방향 전환)
                    if(next[1] >= 6){
                        next[1] = location - 1;
                        direction = "L";
                    }
                }

                // 방향이 L이라면
                else{

                    // 다음 칸은 위 + 왼쪽으로 전진
                    next = new int[] {i, location - 1};

                    // 맵을 벗어난다면 -> (튕겨나감 + 방향 전환)
                    if(next[1] < 0){
                        next[1] = location + 1;
                        direction = "R";
                    }
                }

                // 해당 칸이 물감이 없거나, 현재 농도보다 옅은 농도로 칠해져 있다면 -> 색칠
                if(map[next[0]][next[1]] == 0 || map[next[0]][next[1]] > den){
                    map[next[0]][next[1]] = den;
                }

                // 위치, 농도 갱신
                location = next[1];
                den += 1;
            }

            // 다시 아래로 가는 반복문
            for(int i = 1; i < 6; i++){

                // 방향이 R이면
                if(direction.equals("R")){

                    // 아래 + 오른쪽으로 전진
                    next = new int[] {i, location + 1};

                    // 맵을 벗어나면 -> 튕겨나감 + 방향 전환
                    if(next[1] >= 6){
                        next[1] = location - 1;
                        direction = "L";
                    }
                }

                // 방향이 L이면
                else{

                    // 아래 + 왼쪽으로 전진
                    next = new int[] {i, location - 1};

                    // 맵을 벗어나면 -> 튕겨나감 + 방향 전환
                    if(next[1] < 0){
                        next[1] = location + 1;
                        direction = "R";
                    }
                }

                // 색칠하기
                if(map[next[0]][next[1]] == 0 || map[next[0]][next[1]] > den){
                    map[next[0]][next[1]] = den;
                }

                // 위치, 물감 농도 갱신
                location = next[1];
                den += 1;
            }
        }

        // 정답 출력(배열에 0(칠해지지 않음)은 "_"로 바꿔서 출력
        for(int i = 0 ; i < 6; i++){
            for(int j = 0; j < 6; j++){
                System.out.print(map[i][j] == 0 ? "_" : map[i][j]);
            }
            System.out.println();
        }
    }
}