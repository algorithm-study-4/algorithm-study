
// [시간복잡도] - MST(Kruskal) 풀이
// O(ElogE)

import java.util.Arrays;

class Solution {

    // parent[i] = i번 섬의 바로 위 노드 (parent[i] == i 이면 그룹의 대표)
    int[] parent;

    public int solution(int n, int[][] costs) {

        // [1] 다리를 건설 비용 오름차순으로 정렬
        // costs[i] = {섬 A, 섬 B, 건설 비용}
        // 싼 다리부터 확인해야 "가장 싼 것부터 고르는" 그리디가 성립함
        Arrays.sort(costs, (x, y) -> Integer.compare(x[2], y[2]));

        // [2] Union-Find 초기화: 처음엔 모든 섬이 각자 따로 떨어져 있음
        // 섬 번호가 0 ~ n-1 이므로 크기 n으로 생성
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // [3] 싼 다리부터 하나씩 건설 여부 결정
        int answer = 0;   // 총 건설 비용
        int built = 0;    // 지금까지 건설한 다리 수

        for (int[] bridge : costs) {
            int a = bridge[0];
            int b = bridge[1];
            int cost = bridge[2];

            // 두 섬이 이미 (다른 다리들을 통해) 통행 가능하다면
            //   -> 이 다리는 필요 없음 (지으면 순환만 생기고 돈 낭비)
            // 아직 통행 불가능하다면
            //   -> 이 다리를 건설하고 두 섬의 그룹을 하나로 합침
            if (union(a, b)) {
                answer += cost;
                built++;

                // 섬 n개를 모두 연결하는 데 필요한 다리는 정확히 n-1개
                // 다 지었으면 남은 다리는 볼 필요 없음
                if (built == n - 1) {
                    break;
                }
            }
        }

        // 섬이 1개뿐이면(n = 1) 다리가 필요 없으므로 반복 없이 0이 반환됨
        return answer;
    }

    // Find: x가 속한 그룹의 대표를 반환 (경로 압축)
    int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    // Union: a와 b의 그룹을 합침
    //   - 원래 다른 그룹이었으면 합치고 true  -> 다리 건설
    //   - 이미 같은 그룹이었으면 false      -> 다리 건설 안 함
    boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return false;
        }
        parent[rootB] = rootA;   // 대표끼리 연결
        return true;
    }
}

// [예시 따라가기]
// 순서     다리        판단                건설 후 그룹        총 비용     건설 수
//  1      0-1(1)     다른 그룹 -> 건설     {0,1} {2} {3}     1         1
//  2      1-3(1)     다른 그룹 -> 건설     {0,1,3} {2}       2         2
//  3      0-2(2)     다른 그룹 -> 건설     {0,1,2,3}         4         3=n-1 -> 종료
//  4      1-2(5)     (확인 안 함)
//  5      2-3(8)     (확인 안 함)
