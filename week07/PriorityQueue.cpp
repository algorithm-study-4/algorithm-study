#include <iostream>
#include <queue>
#include <string>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    if (!(cin >> n)) return 0;

    priority_queue<int> pq;

    for (int i = 0; i < n; ++i) {
        string cmd;
        cin >> cmd;

        if (cmd == "push") {
            int a;
            cin >> a;
            pq.push(a);
        } else if (cmd == "pop") {
            int b;
            cin >> b;
            for (int j = 0; j < b; ++j) {
                cout << pq.top() << (j + 1 == b ? "" : " ");
                pq.pop();
            }
            cout << "\n";
        } else if (cmd == "add") {
            int c;
            cin >> c;
            if (!pq.empty()) {
                int topVal = pq.top();
                pq.pop();
                pq.push(topVal + c);
            }
        }
    }

    return 0;
}