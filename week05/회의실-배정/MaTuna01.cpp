#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Meeting {
    int start;
    int end;
};

// 정렬 기준: 종료 시간 오름차순 -> 시작 시간 오름차순
bool compare(const Meeting& a, const Meeting& b) {
    if (a.end == b.end) {
        return a.start < b.start;
    }
    return a.end < b.end;
}

int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    if (!(cin >> N)) return 0;

    vector<Meeting> meetings(N);
    for (int i = 0; i < N; ++i) {
        cin >> meetings[i].start >> meetings[i].end;
    }

    // 종료 시간 기준으로 정렬
    sort(meetings.begin(), meetings.end(), compare);

    int count = 0;
    int last_end_time = 0;

    for (int i = 0; i < N; ++i) {
        // 현재 회의 시작 시간이 이전 회의 종료 시간 이상이면 배정 가능
        if (meetings[i].start >= last_end_time) {
            last_end_time = meetings[i].end;
            count++;
        }
    }

    cout << count << "\n";

    return 0;
}