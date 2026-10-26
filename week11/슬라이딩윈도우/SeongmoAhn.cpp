#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
#define endl '\n'

int T, N, W, t;
vector<int> scores;

// 입력 코드 입니다. 무시하셔도 됩니다.
void init_input() {
    cin >> N >> W;
    scores.clear();
    for (int i = 0; i < N; i++) {
        int a; cin >> a;
        scores.push_back(a);
    }
}

void solve() {
    int ans, sum = 0;
    int l = 0, r = W - 1; // 슬라이딩 윈도우의 시작, 끝 값 저장

    // 슬라이딩 윈도우의 사이즈(W)에 맞게 초기 값 설정
    for (int i = 0; i < W; i++) sum += scores[i];
    ans = sum;

    // 슬라이딩 윈도우에서 빠지는 쪽(왼쪽)과 추가되는 쪽(오른쪽)을 sum 값에 갱신
    // ans와 비교해서 sum이 더 큰 경우 슬라이딩 윈도우의 시작, 끝, 점수합을 갱신
    for (int i = 0, j = W; j < N; i++, j++) {
        sum = sum - scores[i] + scores[j];
        if (sum > ans) {
            l = i + 1;
            r = j;
            ans = sum;
        }
    }

    cout << '#' << t << ' ' << l << ' ' << r << ' ' << ans << endl;
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    cin >> T;
    for (t = 1; t <= T; t++) {
        init_input();
        solve();
    }

    return 0;
}
