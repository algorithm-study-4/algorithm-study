import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Main {
    static int C, B;
    static int[] calories;
    static int maxCalorie = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // C: 제한 칼로리, B: 음식의 개수
        C = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        calories = new int[B];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            calories[i] = Integer.parseInt(st.nextToken());
        }

        // DFS(백트래킹) 탐색 시작
        // 0번째 음식부터 탐색하며, 초기 누적 칼로리는 0
        dfs(0, 0);

        // 갱신된 최대 섭취 칼로리 출력
        System.out.println(maxCalorie);
    }

    /*
     * 부분집합을 구하기 위한 깊이 우선 탐색(DFS) 메서드
     * @param index 현재 탐색 중인 음식의 배열 인덱스
     * @param currentSum 현재까지 섭취하기로 한 음식들의 칼로리 합
    */
    static void dfs(int index, int currentSum) {
        // 1. 가지치기 (Pruning)
        // 현재까지 칼로리 합이 C를 초과하면 유효하지 않으므로 더 이상 탐색하지 않음
        if (currentSum > C) {
            return;
        }

        // 2. 최대 칼로리 갱신
        // currentSum은 항상 C 이하임이 위 조건문에서 보장됨
        maxCalorie = Math.max(maxCalorie, currentSum);

        // 3. 종료 조건
        // 모든 음식(B개)에 대해 "먹을지 말지" 결정을 마쳤다면 메서드 종료
        if (index == B) {
            return;
        }
    
        // 4. 다음 단계로 재귀 호출 (상태 트리 분기)
        // 경우의 수 A: 현재 인덱스의 음식을 '먹는' 경우 (칼로리를 더해줌)
        dfs(index + 1, currentSum + calories[index]);

        // 경우의 수 B: 현재 인덱스의 음식을 '먹지 않는' 경우 (칼로리를 더하지 않음)
        dfs(index + 1, currentSum);
    }
}
