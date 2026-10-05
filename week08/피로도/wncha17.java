
// [피로도 - 백트래킹(DFS) 활용]
// 시간복잡도: O(N!) (N: 던전의 개수)
// 이유1: (순열 탐색 구조)
// 이유2: (N이 매우 작아 최악의 경우 연산 횟수가 약 4만번밖에 안됨)
// 이유3: (가지치기(Pruning) 영향)

class Solution {
    // 탐험한 최대 던전 수를 저장할 전역 변수
    int maxCount = 0;
    // 던전 방문 여부를 체크할 배열
    boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        // 던전의 개수만큼 방문 배열 크기 할당
        visited = new boolean[dungeons.length];

        // DFS(백트래킹) 시작
        // 초기 상태: 현재 피로도 k, 방문한 던전 수 0
        dfs(k, dungeons, 0);

        return maxCount;
    }

    /*
     * 백트래킹을 통해 모든 던전 방문 순서를 탐색하는 메서드
     * @param fatigue 현재 남은 피로도
     * @param dungeons 던전 정보 배열
     * @param count 현재까지 탐험한 던전의 수
    */
    private void dfs(int fatigue, int[][] dungeons, int count) {
        // 매 탐색마다 최대 방문 횟수를 갱신
        maxCount = Math.max(maxCount, count);

        // 모든 던전을 순회하며 탐험 가능한지 확인
        for (int i = 0; i < dungeons.length; i++) {
            // 조건 1: 아직 방문하지 않은 던전인가?
            // 조건 2: 현재 피로도가 던전의 '최소 필요 피로도'보다 크거나 같은가?
            if (!visited[i] && fatigue >= dungeons[i][0]) {
                visited[i] = true; // 1. 방문 처리 (탐험 시작)

                // 2. 피로도를 차감하고, 방문 횟수를 1 증가시킨 뒤 다음 단계로 재귀 호출
                // dungeons[i][1]은 현재 던전의 '소모 피로도'
                dfs(fatigue - dungeons[i][1], dungeons, count + 1);

                visited[i] = false; // 3. 백트래킹: 다른 순서의 경우의 수를 찾기 위해 방문 상태 원상 복구
            }
        }
    }
}

// 예시를 통한 시뮬레이션
// [초기 피로도] 80
// [던전 정보] [[80, 20], [50, 40], [30, 10]]

// 1. dfs(80, dungeons, 0) 호출
// -> 0번 던전 검사(i=0):
//    visited[0] = true / 남은 피로도: 60
// (재귀) 2. dfs(60, dungeons, 1) 호출
// -> 1번 던전 검사(i=1):
//    visited[1] = true / 남은 피로도: 20
// (재귀) 3. dfs(20, dungeons, 2) 호출
// -> 2번 던전 검사(i=2):
//    if문 못 들어감(입장 불가) / maxCount=2
// (백트래킹) 2번 단계로 돌아감
// -> visited[1] = false
// -> 2번 던전 검사(i=2):
//    visited[2] = true / 남은 피로도: 50
// (재귀) 3. dfs(50, dungeons, 2)
// -> 1번 던전 검사(i=1):
//    visited[1] = true / 남은 피로도: 10
// -> dfs(10, dungeons, 2) 호출 => maxCount=3

// 전체 흐름:
// "들어갔다가(재귀) -> 안 되면(또는 끝나면) 다시 나와서(백트래킹) -> 다른 곳을 들어간다"
