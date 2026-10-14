#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;
#define endl '\n'
#define SIZE 1001

int N;
int parent[SIZE];
int graph[SIZE][SIZE];

void init_input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++)  {
            int a; cin >> a;
            graph[i][j] = a;
        }
    }
}

int findParent(int n) {
    if (parent[n] != n) parent[n] = findParent(parent[n]);
    return parent[n];
}

bool unionParent(int v1, int v2) {
    int v = findParent(v1);
    int w = findParent(v2);

    if (v == w) return false;

    parent[v] = w;

    return true;
}

void solve() {
    for (int i = 0; i < N; i++) parent[i] = i;

    for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
            if (!graph[i][j]) continue;
            if (!unionParent(i, j)) {
                cout << "WARNING" << endl;
                return;
            }
        }
    }
    cout << "STABLE" << endl;
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    init_input();
    solve();

    return 0;
}
