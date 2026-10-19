// 벽을 피해 통신 케이블을 설치하여 S와 모든 A 노드를 연결하는 최소 비용을 구하는 문제
// 알고리즘: bfs + mst
// 시간복잡도: O(zxy + z^2*logz), z = s + a

import java.io.*;
import java.util.*;

public class Main {
    // Union Find에서 각 노드의 부모를 저장하는 배열
    static private int [] root;

    // 전체 맵 정보 저장 (#: 벽, _: 빈칸, S: 시작 노드, A: 다른 노드)
    static private char [][] map;
    static private int x, y;

    // 상하좌우
    static private int [] dy = {-1, 1, 0, 0};
    static private int [] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스 입력
        int t = Integer.parseInt(br.readLine());


        // 테스트 케이스 만큼 반복
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            map = new char[y][x];

            // S와 A의 좌표 저장하는 리스트
            List<int[]> nodes = new ArrayList<>();

            for (int i = 0; i < y; i++) {
                String l = br.readLine();

                for (int j = 0; j < x; j++) {
                    map[i][j] = l.charAt(j);

                    // S 또는 A라면 통신망으로 연결해야 하는 노드
                    if (map[i][j] == 'S' || map[i][j] == 'A') {

                        // 해당 노드의 좌표 저장
                        nodes.add(new int[]{i, j});
                    }
                }
            }

            // S와 A를 합친 전체 노드 개수
            int n = nodes.size();



            // BFS로 계산한 노드 사이의 최단 거리를 간선으로 저장
            // 각 원소는 {노드 번호 a, 노드 번호 b, 비용 w}
            List<int[]> edges = new ArrayList<>();

            // 모든 노드에서 BFS를 실행하여 노드 사이의 최단 거리 계산
            for (int i = 0; i < n; i++) {
                int[][] dist = bfs(nodes.get(i));

                for (int j = i+1; j < n; j++) {
                    int[] node = nodes.get(j);

                    // i번 노드에서 j번 노드까지의 BFS 최단 거리
                    int w = dist[node[0]][node[1]];


                    // 두 노드와 연결 비용을 간선 리스트에 추가
                    edges.add(new int[]{i, j, w});
                }
            }

            // MST - 크루스칼

            // 가중치로 오름차순 정렬
            edges.sort((a, b) -> Integer.compare(a[2], b[2]));

            root = new int[n];

            // 각 노드의 부모를 자기 자신으로 초기화
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }

            // 케이블의 전체 설치 비용
            int ans = 0;

            // MST에 포함된 간선 개수
            int edgeCnt = 0;

            // 가중치가 작은 간선부터 하나씩 확인
            for (int[] edge : edges) {
                int a = edge[0];
                int b = edge[1];

                int w = edge[2];

                if (find(a) != find(b)) {
                    union(a, b);
                    ans += w;
                    edgeCnt++;

                    if (edgeCnt == n - 1) break;
                }
            }

            System.out.println(ans);
        }
    }

    static int[][] bfs(int[] start) {
        int[][] dist = new int[y][x];

        for (int i = 0; i < y; i++) {
            Arrays.fill(dist[i], -1);
        }

        Queue<int[]> q = new ArrayDeque<>();

        int sy = start[0];
        int sx = start[1];

        q.offer(new int[]{sy, sx});
        dist[sy][sx] = 0;


        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int cy = curr[0];
            int cx = curr[1];

            for (int d = 0; d < 4; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];


                // 이동 불가하면 탐색하지 않음.
                if (ny < 0 || nx < 0 || ny >= y || nx >= x) continue; // 맵 범위 벗어남
                if (map[ny][nx] == '#') continue; // 벽
                if (dist[ny][nx] != -1) continue; // 이미 방문된 칸 (이미 최단거리가 계산되어 있음)

                dist[ny][nx] = dist[cy][cx] + 1;

                q.offer(new int[]{ny, nx});
            }
        }

        return dist;
    }

    // a가 속한 집합의 루트를 반환
    static int find(int a) {
        if (root[a] == a) return a;

        return root[a] = find(root[a]);
    }

    // 두 노드가 속한 집합을 하나로 합침
    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        root[a] = b;
    }
}