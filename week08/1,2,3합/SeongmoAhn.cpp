#include <iostream>
using namespace std;

int N;
int cnt;

int dfs(int n) {
    if (n == N) return 1;
    if (n > N) return 0;

    int result = 0;
    for (int i = 1; i <= 3; i++)
        result += dfs(n + i);
    return result;
}

void solve() {
    cin >> N;
    cnt = dfs(0);
    cout << cnt;
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    solve();

    return 0;
}
