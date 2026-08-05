#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;
#define endl '\n'
#define SIZE

int N, K;
vector<int> v;

void init_input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        int n; cin >> n;
        v.push_back(n);
    }
    sort(v.begin(), v.end());

    cin >> K;
}

void solve() {
    while (K--) {
        int n; cin >> n;
        int flag = 1;

        int low = 0, high = v.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (v[mid] == n) {
                cout << 'O';
                flag = 0;
                break;
            } else if (v[mid] < n) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (flag) cout << 'X';
    }
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    init_input();
    solve();

    return 0;
}
