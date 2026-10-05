class Solution {
    // 전역 변수 선언
    int answer = -1;
    boolean[] visited;
    int count = 0;

    public int solution(int k, int[][] dungeons) {
        // 배열 초기화
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, count);
        return answer;
    }

    // 재귀함수
    void dfs(int k, int[][] dungeons, int count){
        answer = Math.max(answer, count);

        // 전부 돌기
        for(int i = 0; i < dungeons.length; i++){
            if(dungeons[i][0] <= k && !visited[i]){
                visited[i] = true;
                dfs(k-dungeons[i][1], dungeons, count + 1);
                visited[i] = false;
            }
        }
    }
}