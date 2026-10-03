class Solution {
    static int maxCount;       // 지금까지 찾은 최대 탐험 던전 수
    static boolean[] visited;  // 던전별 방문 여부

    public int solution(int k, int[][] dungeons) {
        maxCount = 0;
        visited = new boolean[dungeons.length];

        dfs(k, dungeons, 0);

        return maxCount;
    }

    // fatigue: 현재 남은 피로도, count: 지금까지 탐험한 던전 수
    private void dfs(int fatigue, int[][] dungeons, int count) {
        // 어떤 경로든 도달한 시점마다 최댓값 갱신
        // 더 못 가는 상황에서도 그때까지의 count가 후보가 됨
        maxCount = Math.max(maxCount, count);

        for (int i = 0; i < dungeons.length; i++) {
            // 이미 간 던전은 스킵
            if (visited[i]) continue;

            // 최소 필요 피로도보다 현재 피로도가 부족하면 이 던전은 못감
            if (fatigue < dungeons[i][0]) continue;

            visited[i] = true;
            dfs(fatigue - dungeons[i][1], dungeons, count + 1); // 소모 피로도만큼 차감 후 다음 던전 탐색
            visited[i] = false; // 방문 처리 되돌리기
        }
    }
}
