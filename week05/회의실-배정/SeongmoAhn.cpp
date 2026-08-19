#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
#define endl '\n'

int N;
vector<pair<int, int>> v;

void init_input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        int a, b; cin >> a >> b;
        v.push_back({a, b});
    }
}

void solve() {
    sort(v.begin(), v.end(), greater<>());

    int cnt = 0;
    int start = 200000000;
    for (auto cur : v) {
        if (cur.second <= start) {
            cnt++;
            start = cur.first;
        }
    }
    cout << cnt;
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    init_input();
    solve();

    return 0;
}
