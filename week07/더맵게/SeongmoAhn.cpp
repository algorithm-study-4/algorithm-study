#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <queue>
using namespace std;

int solution(vector<int> scoville, int K) {
    priority_queue<int> pq;
    for (auto s : scoville) {
        pq.push(-s);
    }

    int ans = 0;
    while (-pq.top() < K) {
        if (pq.size() < 2) {
            return -1;
        }
        int a = pq.top(); pq.pop();
        int b = pq.top(); pq.pop();
        pq.push(a + b * 2);
        ans++;
    }

    return ans;
}

int main(void) {
    vector<int> scoville = {1, 2, 3, 9, 10, 12};
int K = 7;

cout << solution(scoville, K) << endl;
}
