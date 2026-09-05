#include <string>
#include <vector>
#include <queue>
using namespace std;

int solution(vector<int> scoville, int K) {
    priority_queue<long long, vector<long long>, greater<long long>>
        pq(scoville.begin(), scoville.end());

    int answer = 0;

    while (pq.top() < K) {
        if (pq.size() < 2) return -1;

        long long first = pq.top();  pq.pop();   // 가장 맵지 않은 음식
        long long second = pq.top(); pq.pop();   // 두 번째로 맵지 않은 음식

        pq.push(first + second * 2);
        answer++;
    }

    return answer;
}