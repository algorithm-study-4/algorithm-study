#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;
#define endl '\n'
#define SIZE 

void getWeight(string &str, int alp[]) {
    int weight = 1;
    for (int i = str.size() - 1; i >= 0; i--) {
        alp[str[i] - 'A'] += weight;
        weight *= 10;
    }
}

void solve() {
    int N; cin >> N;
    int weight[26] = {0};
    while (N--) {
        string str; cin >> str;
        getWeight(str, weight);
    }

    sort(weight, weight + 26, greater<>());

    int ans = 0;
    int num = 9;
    for (int i = 0; i < 26; i++) {
        if (!weight[i]) break;

        ans += weight[i] * num--;
    }
    cout << ans;
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    solve();

    return 0;
}
