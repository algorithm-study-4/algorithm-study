#include<iostream>
#include<vector>
#include<queue>

using namespace std;

// vector<vector<int>> graph;
// vector<bool> infected;
// int infected_pc = 0;

// void dfs(int current) {
//     infected[current] = true;

//     for (int i = 0; i < graph[current].size(); i++){
//         int next = graph[current][i];
        
//         if (!infected[next]){
//             infected_pc ++;
//             dfs(next);
//         }
//     }
// }

// int main() {
//     ios_base::sync_with_stdio(false);
//     cin.tie(NULL);

//     int n, m;
//     cin >> n >> m;

//     graph.assign(n + 1, vector<int>());
//     infected.assign(n + 1, false);

//     for (int i = 0; i < m; i++){
//         int u, v;
//         cin >> u >> v;
//         graph[u].push_back(v);
//         graph[v].push_back(u);
//     }

//     dfs(1);

//     cout << infected_pc << "\n";

//     return 0;

// }

vector<vector<int>> graph;
vector<bool> infected;
int infected_pc = 0;


// BFS 탐색 함수
void bfs(int start) {
    queue<int> q;
    
    // 시작 노드 설정
    q.push(start);
    infected[start] = true;

    while (!q.empty()) {
        // 큐의 맨 앞 노드를 꺼냄
        int current = q.front();
        q.pop();

        // 현재 노드와 연결된 모든 노드를 확인
        for (int i = 0; i < graph[current].size(); i++) {
            int next = graph[current][i];
            
            // 아직 감염되지 않은(방문하지 않은) 컴퓨터라면
            if (!infected[next]) {
                infected[next] = true; // 감염 처리
                infected_pc++;         // 감염된 컴퓨터 수 증가
                q.push(next);          // 큐에 추가하여 다음 번에 탐색하도록 함
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;

    graph.assign(n + 1, vector<int>());
    infected.assign(n + 1, false);

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    bfs(1);

    cout << infected_pc << "\n";

    return 0;
}