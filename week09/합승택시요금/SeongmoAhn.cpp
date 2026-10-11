#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;
#define SIZE 201
#define INF 1e8

int dist[SIZE][SIZE];

void init_input(int n, vector<vector<int>> &fares) {
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            dist[i][j] = (i == j) ? 0 : INF;
        }
    }

    for (int i = 0; i < fares.size(); i++) {
        int node1 = fares[i][0], node2 = fares[i][1], weight = fares[i][2];
        dist[node1][node2] = min(dist[node1][node2], weight);
        dist[node2][node1] = min(dist[node2][node1], weight);
    }
}

void floyd(int n) {
    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
}

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    init_input(n, fares);
    floyd(n);

    // s에서 k까지 합승 후, k에서 a, b로 각자 이동 (k == s이면 처음부터 따로)
    int ans = INF;
    for (int k = 1; k <= n; k++) {
        ans = min(ans, dist[s][k] + dist[k][a] + dist[k][b]);
    }
    return ans;
}

int main(void) {
    int n = 6;
    int s = 4;
    int a = 6;
    int b = 2;
    vector<vector<int>> fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};

    cout << solution(n, s, a, b, fares) << endl;
}
