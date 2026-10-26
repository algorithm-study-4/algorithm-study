#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
using namespace std;
#define endl '\n'

// 시간 초과 나는 코드 O(N^2)
void solve1() {
    int N; cin >> N;
    long long sum[100001] = {0};
    for (int i = 1; i <= N; i++) {
        long long a; cin >> a;
        sum[i] = sum[i - 1] + a;
    }
    
    long long ans = 0;
    for (int i = 1; i <= N; i++) {
        for (int j = 0; j < i; j++) {
            if (sum[i] - sum[j] == 0) ans++;
        }
    }
    cout << ans;
}

// 시간 초과 안나는 코드 O(NlogN)
void solve2() {
    int N; cin >> N;
    map<long long, long long> cnt; // 해당 값이 몇 번 나왔는지를 저장
    cnt[0] = 1;
    long long sum = 0, ans = 0;
    for (int i = 1; i <= N; i++) {
        long long a; cin >> a;
        sum += a;
        ans += cnt[sum];
        cnt[sum]++;
    }

    cout << ans;
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    // solve1();
    solve2();

    return 0;
}
