import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    // parent[i] = i번 노드의 "바로 위" 노드
    // parent[i] == i 이면, i가 그 그룹의 대표(반장)
    static int[] parent;

    // rank[i] = i가 대표인 트리의 대략적인 높이
    // 합칠 때 낮은 트리를 높은 트리 밑에 붙이기 위해 사용
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 노드 수
        int q = Integer.parseInt(st.nextToken()); // 명령 수

        // [1] 초기화: 처음에는 모든 노드가 혼자서 하나의 그룹
        // 아무것도 연결되지 않았으므로 모든 노드가 자기 자신의 반장
        parent = new int[n + 1];
        rank = new int[n + 1];        // 처음엔 모든 트리 높이가 0
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();

        // [2] 명령 처리
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (k == 0) {
                // [0, A, B] : 같은 그룹인지 확인
                // -> 두 노드의 반장이 같으면 같은 그룹
                if (find(a) == find(b)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            } else {
                // [1, A, B] : 두 노드가 속한 그룹을 하나로 합침
                union(a, b);
            }
        }

        System.out.print(sb);
    }

    // Find: x가 속한 그룹의 대표(반장)를 찾아 반환
    static int find(int x) {
        // 자기 자신을 가리키면 x가 반장
        if (parent[x] == x) {
            return x;
        }

        // [경로 압축]
        // 위로 올라가서 찾은 반장을 parent[x]에 바로 저장
        // -> 다음부터 x는 중간 노드를 거치지 않고 반장으로 직행
        // (재귀가 거꾸로 돌아오면서 지나온 모든 노드가 반장을 직접 가리키게 됨)
        parent[x] = find(parent[x]);
        return parent[x];
    }

    // Union: a가 속한 그룹과 b가 속한 그룹을 하나로 합침
    static void union(int a, int b) {
        // 노드끼리가 아니라 "반장끼리" 연결해야 하므로 먼저 반장을 찾음
        int rootA = find(a);
        int rootB = find(b);

        // 이미 같은 그룹이면 합칠 필요 없음
        if (rootA == rootB) {
            return;
        }

        // [랭크 기준 합치기]
        // 높이가 낮은 트리의 반장을, 높은 트리의 반장 밑으로 붙임
        // -> 전체 트리의 높이가 늘어나지 않음
        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;          // A 그룹이 B 그룹 밑으로
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;          // B 그룹이 A 그룹 밑으로
        } else {
            // 높이가 같으면 아무 쪽이나 붙이고, 반장이 된 쪽 높이를 1 올림
            parent[rootB] = rootA;
            rank[rootA]++;
        }
    }
}
