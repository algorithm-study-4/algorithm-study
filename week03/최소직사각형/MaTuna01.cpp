#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> sizes) {
    int w = 0, h = 0;

    for (const auto& size : sizes) {
        // 명함의 두 변 중 더 긴 값은 w와 비교하고, 더 짧은 값은 h와 비교하여 갱신
        w = max(w, max(size[0], size[1]));
        h = max(h, min(size[0], size[1]));
    }

    return w * h;
}