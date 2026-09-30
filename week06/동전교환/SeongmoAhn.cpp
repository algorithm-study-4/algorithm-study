#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
#define endl '\n'
#define SIZE 3001
#define INF 1000000

int T, N;
vector<int> coins;
int dp[SIZE];

void init_input() {
    cin >> T >> N;
    for (int i = 0; i < N; i++) {
        int a; cin >> a;
        coins.push_back(a);
    }
    for (int i = 1; i < SIZE; i++)
        dp[i] = INF;
}

void solve() {
    for (auto coin : coins) {
        for (int j = coin; j < SIZE; j++) {
            dp[j] = min(dp[j], dp[j - coin] + 1);
        }
    }

    if (dp[T] == INF) cout << "impossible";
    else cout << dp[T];
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    init_input();
    solve();

    return 0;
}
