#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
#include <string>
using namespace std;
#define endl '\n'

// 시간 초과 코드
void solve1() {
    // 입력값 저장
    int n;
    string str;
    int prefix[100001] = {0};
    cin >> n >> str;
    // 첫 글자부터 부분합 저장 
    for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + (str[i] - '0');

    int ans = 0;
    for (int left = 0; left < n; left++) {
        for (int right = left + 1; right <= n; right++) {
            // left-right 구간합과 길이 계산
            int sum = prefix[right] - prefix[left];
            int len = right - left;

            if (sum == len) ans++;
        }
    }

    cout << ans;
}

// 시간 초과 해결 코드
void solve2() {
    // 입력값 저장
    int n;
    string str;
    cin >> n >> str;

    // sum == len 검사식
    // -> prefix[right] - prefix[left] == right - left
    // -> prefix[right] - right == prefix[left] - left
    // => key[i] = prefix[i] - i 로 계산해두면, key[left] == key[right]로 검사 하면 됨
    // 각 위치의 (부분합 - 위치) 값이 같은 두 위치의 쌍을 세면 된다
    map<int, int> cnt;
    cnt[0] = 1;     // 위치 0: 부분합 0 - 위치 0

    long long ans = 0;
    int sum = 0;
    for (int i = 1; i <= n; i++) {
        sum += str[i - 1] - '0';
        int key = sum - i;
        // 앞에서 같은 key가 나온 횟수만큼 조건을 만족하는 구간이 생김
        ans += cnt[key];
        cnt[key]++;
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
