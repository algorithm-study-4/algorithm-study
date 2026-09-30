#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <queue>
using namespace std;

// jobs<요청 시각, 작업시간>
int solution(vector<vector<int>> jobs) {
    // 요청 시각 기준으로 정렬
    priority_queue<pair<int, pair<int, int>>> job_pq; // <요청시각, 작업시간, 작업번호(인덱스)>
    for (int i = 0; i < jobs.size(); i++) {
        auto cur = jobs[i];
        job_pq.push({-cur[0], {-cur[1], i}});
    }

    // 작업시간이 짧은 것, 요청 시각이 빠른 것, 작업 번호가 작은것 순서
    priority_queue<pair<int, pair<int, int>>> pq; // <작업시간, 요청시각, 작업번호(인덱스)>
    int time = 0;
    bool working = false;
    int idx, working_time, ask_time;
    int start;
    int sum = 0;

    while (1) {
        while (!job_pq.empty() && -job_pq.top().first == time) {
            auto cur = job_pq.top(); job_pq.pop();
            pq.push({cur.second.first, {cur.first, -cur.second.second}});
        }

        if (working) { // 작업 중
            if (time - start == working_time) {
                sum += time - ask_time;
                working = false;
            }
        } 

        if (!working) { // 작업 안하는 중
            if (!pq.empty()) {
                auto cur = pq.top(); pq.pop();
                working_time = -cur.first;
                ask_time = -cur.second.first;
                idx = -cur.second.second;
                working = true;
                start = time;
            }
        }

        if (job_pq.empty() && pq.empty() && !working) return sum / jobs.size();

        time++;
    }
}

int main(void) {
    vector<vector<int>> jobs = {{0, 3}, {1, 9}, {3, 5}};

cout << solution(jobs) << endl;
}
