#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
#define endl '\n'
#define SIZE 
#define ll long long

int N, M;
vector<int> v;

void init_input() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        int a; cin >> a;
        v.push_back(a);
    }
}

void solve() {
    ll low = 0, high = *max_element(v.begin(), v.end());
    while (low <= high) {
        ll mid = (low + high) / 2;

        ll cnt = 0;
        for (auto t: v) {
            cnt += max(0LL, (ll)t - mid);
        }

        if (cnt < M) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }

    cout << high;
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    init_input();
    solve();

    return 0;
}
