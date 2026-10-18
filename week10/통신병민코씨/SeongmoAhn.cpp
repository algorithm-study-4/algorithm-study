#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <queue>
#include <tuple>
#include <cstring>
using namespace std;
#define endl '\n'
#define SIZE 55
#define NODE_SIZE 110
#define pii pair<int, int>

int T, R, C;
string graph[SIZE];
int dist[SIZE][SIZE]; // BFS 거리 계산용
vector<pii> nodes; // S, A 위치 저장
int id[SIZE][SIZE]; // node 번호를 만들어주기 위한 배열, 노드가 아니면 -1 저장
int W[NODE_SIZE][NODE_SIZE]; // 노드 간 최단 거리 저장
int dy[] = {-1, 1, 0, 0};
int dx[] = {0, 0, -1, 1};

// For Kruscal
int parent[NODE_SIZE];
vector<tuple<int, int, int>> graph_kruscal; // <가중치, 노드, 노드>

// For Prim
vector<pii> graph_prim[NODE_SIZE]; // <노드, 가중치>
priority_queue<pii> pq; // <-가중치, 노드>
bool visited[NODE_SIZE];

int findParent(int v) {
    if (parent[v] == v) return v;
    return parent[v] = findParent(parent[v]);
}

// 서로 다른 집합이면 합치고 true, 이미 같은 집합(사이클)이면 false
bool unionParent(int v1, int v2) {
    int v = findParent(v1);
    int w = findParent(v2);

    if (v == w) return false;
    parent[w] = v;
    return true;
}

// 가중치가 작은 간선부터 사이클이 생기지 않으면 선택
int kruscal() {
    int ans = 0;
    for (int i = 0; i < graph_kruscal.size(); i++) {
        if (unionParent(get<1>(graph_kruscal[i]), get<2>(graph_kruscal[i])))
            ans += get<0>(graph_kruscal[i]);
    }

    return ans;
}

// 0번 노드(S)에서 시작해서 연결된 노드 중 가장 가중치가 작은 간선을 계속 선택
int prim() {
    int ans = 0;

    pq.push({0, 0});

    while (!pq.empty()) {
        int curWeight = -pq.top().first;
        int curNode = pq.top().second;
        pq.pop();

        if (visited[curNode]) continue;

        visited[curNode] = true;
        ans += curWeight;

        for (int i = 0; i < graph_prim[curNode].size(); i++) {
            int nextNode = graph_prim[curNode][i].first;
            int nextWeight = graph_prim[curNode][i].second;

            if (visited[nextNode]) continue;
            pq.push({-nextWeight, nextNode});
        }
    }

    return ans;
}

void init_input() {
    // 테스트 케이스 초기화용 코드
    nodes.clear();
    memset(id, -1, sizeof(id));
    graph_kruscal.clear();
    memset(visited, false, sizeof(visited));
    for (int i = 0; i < NODE_SIZE; i++) {
        parent[i] = i;
        graph_prim[i].clear();
    }
    while (!pq.empty()) pq.pop();

    cin >> C >> R;
    for (int i = 0; i < R; i++) {
        cin >> graph[i];
        for (int j = 0; j < C; j++) {
            if (graph[i][j] == 'A' || graph[i][j] == 'S') {
                id[i][j] = nodes.size();
                nodes.push_back({i, j});
            }
        }
    }
}

// 간선 가중치 값이 없기 때문에 BFS로 가중치 값을 먼저 갱신함
void bfs(int start) {
    memset(dist, -1, sizeof(dist));

    queue<pii> q;
    q.push(nodes[start]);
    dist[nodes[start].first][nodes[start].second] = 0;
    while (!q.empty()) {
        auto [y, x] = q.front(); q.pop();
        if (id[y][x] != -1) W[start][id[y][x]] = dist[y][x];

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (nx < 0 || ny < 0 || ny >= R || nx >= C) continue;
            if (graph[ny][nx] == '#' || dist[ny][nx] != -1) continue;
            dist[ny][nx] = dist[y][x] + 1;
            q.push({ny, nx});
        }
    }
}

void solve() {
    // 가중치 값 저장
    for (int i = 0; i < nodes.size(); i++) bfs(i);

    // 모든 노드 쌍을 간선으로 등록 (i < j 만 등록해서 중복 방지)
    for (int i = 0; i < nodes.size(); i++) {
        for (int j = i + 1; j < nodes.size(); j++) {
            // For Kruscal
            graph_kruscal.push_back({W[i][j], i, j});

            // For Prim
            graph_prim[i].push_back({j, W[i][j]});
            graph_prim[j].push_back({i, W[i][j]});
        }
    }
    sort(graph_kruscal.begin(), graph_kruscal.end());

    int (*mst)() = kruscal;
    // int (*mst)() = prim;
    cout << mst() << endl;
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    cin >> T;
    while (T--) {
        init_input();
        solve();
    }

    return 0;
}
