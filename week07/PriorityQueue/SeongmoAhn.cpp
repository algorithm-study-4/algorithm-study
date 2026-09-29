#include <iostream>
#include <algorithm>
#include <string>
#include <queue>
using namespace std;
#define endl '\n'

void solve() {
    int N; cin >> N;
    priority_queue<int> pq;
    while (N--) {
        string oper;
        int n;
        cin >> oper >> n;

        if (oper == "push") {
            pq.push(n);
        } else if (oper == "pop") {
            while (n--) {
                cout << pq.top() << ' ';
                pq.pop();
            }
            cout << endl;
        } else {
            int cur = pq.top();
            pq.pop();

            pq.push(cur + n);
        }
    }
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    freopen("input.txt", "r", stdin);
    solve();

    return 0;
}
