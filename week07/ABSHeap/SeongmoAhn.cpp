#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <queue>
using namespace std;
#define endl '\n'

void solve() {
    int N; cin >> N;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
    while (N--) {
        int n; cin >> n;

        if (n) {
            pq.push({abs(n), n});
        } else {
            if (pq.size()) {
                auto cur = pq.top();
                pq.pop();
                cout << cur.second << endl;
            } else {
                cout << 0 << endl;
            }
        }
    }
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    solve();

    return 0;
}
