
// DFS 활용 풀이

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.StringTokenizer;

// public class Main {
//     static List<List<Integer>> graph;
//     static boolean[] visited;

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         int n = Integer.parseInt(br.readLine().trim());
//         int t = Integer.parseInt(br.readLine().trim());

//         // 인접 리스트 초기화 (컴퓨터 번호가 1부터 시작하므로 크기는 n+1)
//         graph = new ArrayList<>();
//         for (int i = 0; i <= n; i++) {
//             graph.add(new ArrayList<>());
//         }

//         // 간선 정보 입력 (양방향 연결)
//         for (int i = 0; i < t; i++) {
//             StringTokenizer st = new StringTokenizer(br.readLine());
//             int a = Integer.parseInt(st.nextToken());
//             int b = Integer.parseInt(st.nextToken());
//             graph.get(a).add(b);
//             graph.get(b).add(a);
//         }

//         visited = new boolean[n + 1];

//         // 1번 컴퓨터부터 DFS 시작
//         dfs(1);

//         // 1번 자신을 제외하고, 감염된(방문된) 컴퓨터 수를 셈
//         int count = 0;
//         for (int i = 2; i <= n; i++) {
//             if (visited[i]) {
//                 count++;
//             }
//         }

//         System.out.println(count);
//     }

//     static void dfs(int node) {
//         visited[node] = true; // 현재 노트 방문 처리

//         for (int next : graph.get(node)) {
//             if (!visited[next]) {
//                 dfs(next); // 아직 방문 안 한 이웃이면 재귀적으로 더 깊이 탐색
//             }
//         }
//     }
// }


/////////////////////////////////////////////////////////////////////


// BFS 활용 풀이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int t = Integer.parseInt(br.readLine().trim());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        boolean[] visited = new boolean[n + 1];
        Deque<Integer> queue = new ArrayDeque<>();

        // 1번 컴퓨터를 큐에 넣고 시작
        queue.add(1);
        visited[1] = true;

        int count = 0; // 감염된(1번 제외) 컴퓨터 수

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next : graph.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    count++;
                    queue.add(next);
                }
            }
        }

        System.out.println(count);
    }
}