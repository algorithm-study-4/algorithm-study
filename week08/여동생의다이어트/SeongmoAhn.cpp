#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

int C, B;
vector<int> foods;
int ans;

// 입력값들 받는 코드라서 무시해도 됩니다
void init_input() {
    cin >> C >> B;
    for (int i = 0; i < B; i++) {
        int n; cin >> n;
        foods.push_back(n);
    }
}

void dfs(int idx, int sum) {
    // 섭취량 갱신
    ans = max(ans, sum);

    // 더 이상 검사할 음식이 없는 경우 끝
    if (idx == B) return;

    // 현재 인덱스 값을 전체 섭취량에 넣지 않고 다음 음식으로 넘어감
    dfs(idx + 1, sum);

    // 이번 음식을 추가했을 때 제한 칼로리(C)를 초과하는 경우는 다음 음식을 넣을지 말지 고민하는 것이 의미가 없기 때문에 여기서 끝냄
    if (sum + foods[idx] <= C)
        // 현재 인덱스 값을 전체 섭취량에 넣고 다음 음식으로 넘어감
        dfs(idx + 1, sum + foods[idx]);
}

void solve() {
    dfs(0, 0);
    cout << ans;
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    init_input();
    solve();

    return 0;
}
