#include <iostream>
#include <queue>
#include <vector>
#include <cstdlib>
using namespace std;

// 절댓값 힙: 절댓값이 작은 값이 top으로 오도록 비교 연산자 정의
struct Cmp {
    bool operator()(int a, int b) const {

        if (abs(a) != abs(b)) return abs(a) > abs(b);
        return a > b;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    priority_queue<int, vector<int>, Cmp> pq;

    while (N--) {
        int X;
        cin >> X;

        if (X != 0) {
            pq.push(X);
        } else {
            if (pq.empty()) {
                // 꺼낼게 없으면 0 출력
                cout << "0\n";
            } else {
                cout << pq.top() << '\n';
                pq.pop();
            }
        }
    }
    return 0;
}