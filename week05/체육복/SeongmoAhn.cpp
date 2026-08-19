#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
    sort(lost.begin(), lost.end());

    // 분실: -1, 보유: 0, 여벌: 1
    int students[40] = {0};
    for (auto l : lost) students[l]--;
    for (auto r : reserve) students[r]++;

    for (auto l : lost) {
        if (students[l] != -1) continue;

        if (students[l - 1] == 1) {
            students[l - 1]--;
            students[l]++;
        } else if (students[l + 1] == 1) {
            students[l + 1]--;
            students[l]++;
        }
    }

    int cnt = 0;
    for (int i = 1; i <= n; i++) if (students[i] != -1) cnt++;
    return cnt;
}

int main(void) {
int n = 5;
vector<int> lost = {2, 4};
vector<int> reserve = {1, 3, 5};

cout << solution(n, lost, reserve) << endl;

    return 0;
}
