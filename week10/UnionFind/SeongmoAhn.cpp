#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;
#define endl '\n'
#define SIZE 1001

int N, M;
int parent[SIZE];

int findParent(int u) {
    if (parent[u] == u) return u;
    return parent[u] = findParent(parent[u]);
}

bool unionParent(int v1, int v2) {
    int u = findParent(v1);
    int v = findParent(v2);

    if (u == v) return false;
    else {
        parent[u] = v;
        return true;
    }
}

void solve() {
    cin >> N >> M;

    for (int i = 1; i <= N; i++) parent[i] = i;

    while (M--) {
        int print, a, b;
        cin >> print >> a >> b;
        if (print) { // 노드 입력
            unionParent(a, b);
        } else { // 출력
            if (findParent(a) == findParent(b)) cout << "YES" << endl;
            else cout << "NO" << endl;
        }
    }
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    solve();

    return 0;
}
