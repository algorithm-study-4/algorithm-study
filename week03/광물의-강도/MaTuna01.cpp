#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, q;
    if (!(cin >> n >> q)) return 0;

    vector<int> minerals(n);
    for (int i = 0; i < n; ++i) {
        cin >> minerals[i];
    }

    // 이분 탐색을 위해 광물의 강도를 오름차순으로 정렬
    sort(minerals.begin(), minerals.end());

    for (int i = 0; i < q; ++i) {
        int s, e;
        cin >> s >> e;

        // s 이상인 첫 번째 원소의 Iterator를 반환
        auto lower = lower_bound(minerals.begin(), minerals.end(), s);

        // e 초과인 첫 번째 원소의 Iterator를 반환
        auto upper = upper_bound(minerals.begin(), minerals.end(), e);

        // 두 Iterator의 차이가 범위 사이 광물의 수
        cout << distance(lower, upper) << "\n";
    }

    return 0;
}