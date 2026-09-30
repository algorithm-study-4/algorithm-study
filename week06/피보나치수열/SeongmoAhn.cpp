#include <iostream>
using namespace std;
#define endl '\n'
#define SIZE 100001
#define MOD 1000000007

void solve() {
    int N; cin >> N;
    int dp[SIZE] = {0, 1, 1};
    for (int i = 3; i < SIZE; i++)
        dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
    
    cout << dp[N];
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    solve();

    return 0;
}
