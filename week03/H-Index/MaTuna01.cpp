#include <string>
#include <vector>

using namespace std;

//h-index를 만족하는지 확인하는 함수
bool isValid(const vector<int>& citations, int h) {
    int count = 0;
    for (int citation : citations) {
        if (citation >= h) {
            count++;
        }
    }
    return count >= h;
}

int solution(vector<int> citations) {
    int left = 0;
    int right = 10000;
    int answer = 0;

    while (left <= right) {
        // (left + right) / 2 와 같지만, int 오버플로우를 방지하는 안전한 작성법
        int mid = left + (right - left) / 2;

        if (isValid(citations, mid)) {
            // mid가 조건을 만족한다면? -> 정답을 기록하고, 더 큰 h가 가능한지 우측 탐색
            answer = mid;
            left = mid + 1;
        }
        else {
            // mid가 조건을 만족하지 못한다면? -> mid보다 큰 값은 다 불가능하므로 좌측 탐색
            right = mid - 1;
        }
    }
    return answer;
}