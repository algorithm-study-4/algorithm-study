import java.util.*;
import java.io.*;

public class Main {

    // 맵 가로, 세로 크기
    static int X, Y;

    // 맵 정보
    static char[][] map;

    // 상, 하, 좌, 우 이동
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 출력을 한번에 모아서 하기 위한 StringBuilder
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 수
        int t = Integer.parseInt(br.readLine().trim());

        // 테스트 케이스만큼 반복
        for (int tc = 0; tc < t; tc++) {

            // 가로, 세로 크기 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());

            map = new char[Y][X];

            // S와 A들의 위치를 담을 리스트
            List<int[]> nodes = new ArrayList<>();

            // S 위치
            int[] s = null;

            // 맵 입력
            for (int i = 0; i < Y; i++) {
                String line = br.readLine();
                for (int j = 0; j < X; j++) {

                    // 줄이 짧게 들어오면 나머지는 벽으로 처리
                    char c = j < line.length() ? line.charAt(j) : '#';
                    map[i][j] = c;

                    // S면 위치 저장
                    if (c == 'S') s = new int[]{i, j};

                        // A면 노드 리스트에 추가
                    else if (c == 'A') nodes.add(new int[]{i, j});
                }
            }

            // S를 0번 노드로 맨 앞에 넣기
            nodes.add(0, s);

            // 전체 노드 수 (S + A들)
            int n = nodes.size();

            // 각 칸이 몇 번 노드인지 기록할 배열 (노드가 아니면 -1)
            int[][] id = new int[Y][X];
            for (int[] row : id) Arrays.fill(row, -1);

            // 노드 위치에 노드 번호 기록
            for (int k = 0; k < n; k++) {
                id[nodes.get(k)[0]][nodes.get(k)[1]] = k;
            }

            // 노드끼리의 거리를 담을 배열 (dist[a][b] = a에서 b까지 거리)
            int[][] dist = new int[n][n];

            // 노드마다 BFS 돌려서 다른 노드들까지 거리 구하기
            for (int k = 0; k < n; k++) {
                bfs(nodes.get(k), id, dist[k]);
            }

            // 프림으로 최소 비용 구해서 출력에 추가
            sb.append(prim(dist, n)).append('\n');
        }

        // 모아둔 결과 한번에 출력
        System.out.print(sb);
    }

    // start에서 출발해서 다른 노드들까지의 거리를 result에 채우는 BFS
    static void bfs(int[] start, int[][] id, int[] result) {

        // 각 칸까지의 거리 (-1이면 아직 방문 안 함)
        int[][] d = new int[Y][X];
        for (int[] row : d) Arrays.fill(row, -1);

        Queue<int[]> q = new ArrayDeque<>();

        // 시작점 삽입
        q.add(start);
        d[start[0]][start[1]] = 0;

        // 더이상 갈 수 없을 때까지 반복
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int cy = now[0], cx = now[1];

            // 지금 칸이 노드라면 거리 기록
            if (id[cy][cx] != -1) {
                result[id[cy][cx]] = d[cy][cx];
            }

            // 상, 하, 좌, 우 확인
            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                // 맵 밖이면 넘긴다
                if (ny < 0 || ny >= Y || nx < 0 || nx >= X) continue;

                // 벽이거나 이미 방문했으면 넘긴다
                if (map[ny][nx] == '#' || d[ny][nx] != -1) continue;

                // 거리 기록하고 큐에 넣기
                d[ny][nx] = d[cy][cx] + 1;
                q.add(new int[]{ny, nx});
            }
        }
    }

    // 노드 간 거리로 모든 노드를 잇는 최소 비용 구하기
    static int prim(int[][] dist, int n) {

        // 이어져 있는지 확인할 배열
        boolean[] inTree = new boolean[n];

        // 각 노드를 연결하는 데 드는 현재 최소 비용
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        // 시작점(S)은 비용 0
        minCost[0] = 0;
        int total = 0;

        // 노드 수만큼 하나씩 연결
        for (int step = 0; step < n; step++) {

            // 아직 연결 안 된 노드 중 가장 싸게 붙일 수 있는 노드 찾기
            int u = -1;
            for (int v = 0; v < n; v++) {
                if (!inTree[v] && (u == -1 || minCost[v] < minCost[u])) u = v;
            }

            // 연결되었다 처리
            inTree[u] = true;

            // 비용 추가
            total += minCost[u];

            // 새로 연결한 노드에서 바로 잇는 게 더 싸면 비용 갱신
            for (int v = 0; v < n; v++) {
                if (!inTree[v] && dist[u][v] < minCost[v]) {
                    minCost[v] = dist[u][v];
                }
            }
        }

        return total;
    }
}