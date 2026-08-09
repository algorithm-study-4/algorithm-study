class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            // 아직 방문하지 않은 컴퓨터를 발견하면
            // 새로운 네트워크 하나 찾음
            if (!visited[i]) {
                dfs(computers, visited, i, n);
                answer++;
            }
        }

        return answer;
    }

    private void dfs(int[][] computers, boolean[] visited, int curr, int n) {
        visited[curr] = true;

        for (int next = 0; next < n; next++) {
            // curr와 next가 직접 연결되어 있고, 아직 방문하지 않았다면 탐색
            if (computers[curr][next] == 1 && !visited[next]) {
                dfs(computers, visited, next, n);
            }
        }
    }
}
