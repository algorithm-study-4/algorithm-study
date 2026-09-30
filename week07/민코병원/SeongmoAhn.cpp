#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <map>
#include <queue>
using namespace std;
#define endl '\n'

void solve() {
    int Q; cin >> Q;
    map<string, bool> visited;
    queue<string> q; // 일반병동용
    priority_queue<pair<int, pair<int, string>>> pq; // 응급병동용, <priority, age, name>

    while (Q--) {
        int type; cin >> type;
        if (type == 1) { //patient
            string name;
            int age, priority;
            cin >> name >> age >> priority;
            visited[name] = false;
            q.push(name);
            pq.push({priority, {-age, name}});
        } else { // doctor
            char normal; cin >> normal;
            if (normal == 'A') { // 일반병동
                while (!q.empty() && visited[q.front()]) q.pop();

                if (q.empty()) cout << "EMPTY" << endl;
                else {
                    cout << q.front() << endl;
                    visited[q.front()] = true;
                    q.pop();
                }
            } else { // 응급병동
                while (!pq.empty() && visited[pq.top().second.second]) pq.pop();

                if (pq.empty()) cout << "EMPTY" << endl;
                else {
                    auto cur = pq.top(); pq.pop();
                    visited[cur.second.second] = true;
                    cout << cur.second.second << endl;
                }
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
