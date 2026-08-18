#include <string>
#include <vector>

using namespace std;

// DFS 함수 정의
void dfs(int current, int n, vector<vector<int>>& computers, vector<bool>& visited) {
    // 현재 노드 방문 처리
    visited[current] = true;
    
    // 현재 노드와 연결된 다른 노드 탐색
    for (int i = 0; i < n; i++) {
        // 자기 자신이 아니면서, 연결되어 있고, 아직 방문하지 않은 노드라면
        if (current != i && computers[current][i] == 1 && !visited[i]) {
            dfs(i, n, computers, visited); // 재귀 호출
        }
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    vector<bool> visited(n, false); // 방문 여부를 체크할 배열
    
    // 모든 컴퓨터를 순회
    for (int i = 0; i < n; i++) {
        // 아직 방문하지 않은 컴퓨터를 만났다면 새로운 네트워크 발견
        if (!visited[i]) {
            dfs(i, n, computers, visited); // 해당 네트워크에 속한 모든 컴퓨터 방문 처리
            answer++; // 네트워크 개수 1 증가
        }
    }
    
    return answer;
}

// #include <string>
// #include <vector>
// #include <queue>

// using namespace std;

// // BFS 함수 정의
// void bfs(int start, int n, vector<vector<int>>& computers, vector<bool>& visited) {
//     queue<int> q;
//     q.push(start);
//     visited[start] = true; // 시작 노드 방문 처리
    
//     while (!q.empty()) {
//         int current = q.front();
//         q.pop();
        
//         // 현재 노드와 연결된 다른 노드 탐색
//         for (int i = 0; i < n; i++) {
//             // 자기 자신이 아니면서, 연결되어 있고, 아직 방문하지 않은 노드라면
//             if (current != i && computers[current][i] == 1 && !visited[i]) {
//                 visited[i] = true; // 큐에 넣을 때 방문 처리
//                 q.push(i);
//             }
//         }
//     }
// }

// int solution(int n, vector<vector<int>> computers) {
//     int answer = 0;
//     vector<bool> visited(n, false); // 방문 여부를 체크할 배열
    
//     // 모든 컴퓨터를 순회
//     for (int i = 0; i < n; i++) {
//         // 아직 방문하지 않은 컴퓨터를 만났다면 새로운 네트워크 발견
//         if (!visited[i]) {
//             bfs(i, n, computers, visited); // 해당 네트워크에 속한 모든 컴퓨터 방문 처리
//             answer++; // 네트워크 개수 1 증가
//         }
//     }
    
//     return answer;
// }