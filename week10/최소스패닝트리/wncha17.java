import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    // parent[i] = i번 정점의 바로 위 노드 (parent[i] == i 이면 그룹의 대표)
    static int[] parent;
    // rank[i] = i가 대표인 트리의 대략적인 높이 (정점이 최대 1만 개라 랭크까지 사용)
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        // [1] 간선 목록 입력: edges[i] = {정점 A, 정점 B, 가중치 C}
        // 크루스칼은 "간선 목록"만 있으면 됨 (인접리스트 불필요)
        int[][] edges = new int[e][3];
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        // [2] 가중치 오름차순 정렬 -> 싼 간선부터 확인하기 위함
        // 가중치가 음수일 수 있으므로 뺄셈 대신 Integer.compare 사용
        Arrays.sort(edges, (x, y) -> Integer.compare(x[2], y[2]));

        // [3] Union-Find 초기화: 처음엔 모든 정점이 각자 하나의 그룹
        parent = new int[v + 1];
        rank = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        // [4] 싼 간선부터 하나씩 채택 여부 결정
        long total = 0;
        int used = 0;    // 지금까지 채택한 간선 수

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int cost = edge[2];

            // 두 정점이 이미 같은 그룹이면 -> 이 간선을 넣으면 순환이 생김 -> 버림
            // 다른 그룹이면 -> 채택하고 두 그룹을 하나로 합침
            if (union(a, b)) {
                total += cost;
                used++;

                // MST의 간선 수는 정확히 V-1개
                // 다 모았으면 남은 간선은 볼 필요 없음 (조기 종료)
                if (used == v - 1) {
                    break;
                }
            }
        }

        System.out.println(total);
    }

    // Find: x가 속한 그룹의 대표를 반환 (경로 압축)
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    // Union: a와 b의 그룹을 합침
    //   - 합치는 데 성공하면(원래 다른 그룹이었으면) true
    //   - 이미 같은 그룹이었으면 false (= 이 간선은 순환을 만듦)
    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return false;     // 이미 연결됨 -> 순환 발생 -> 채택 불가
        }

        // 낮은 트리를 높은 트리 밑에 붙여서 높이를 낮게 유지
        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }
        return true;
    }
}
