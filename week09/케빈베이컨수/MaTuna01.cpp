#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

// 닿지 않는 노드까지의 거리를 무한대로 설정
const int INF = 1e9;
int N, M;
vector<int> adj[105];

// start 노드에서 출발하여 다른 모든 노드까지의 최단 거리 합을 반환하는 BFS 함수
int getKevinBacon(int start) {
    vector<int> dist(N + 1, -1); // -1은 미방문 상태
    queue<int> q;

    dist[start] = 0;
    q.push(start);

    while (!q.empty()) {
        int u = q.front();
        q.pop();

        for (int v : adj[u]) {
            if (dist[v] == -1) { // 아직 방문하지 않은 노드
                dist[v] = dist[u] + 1;
                q.push(v);
            }
        }
    }

    // start 노드의 케빈 베이컨 수 합산
    int total_dist = 0;
    for (int i = 1; i <= N; ++i) {
        total_dist += dist[i];
    }
    return total_dist;
}

int main() {
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    if (!(cin >> N >> M)) return 0;

    // 인접 리스트로 무방향 간선 저장
    for (int i = 0; i < M; ++i) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    int min_bacon = INF;
    int answer_person = 1;

    // 1번 노드부터 N번 노드까지 각각 시작점으로 BFS 실행
    for (int i = 1; i <= N; ++i) {
        int bacon = getKevinBacon(i);
        if (bacon < min_bacon) {
            min_bacon = bacon;
            answer_person = i;
        }
    }

    cout << answer_person << "\n";

    return 0;
}