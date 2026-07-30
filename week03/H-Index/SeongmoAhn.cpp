#include <string>
#include <vector>
#include <algorithm>

using namespace std;

// 완전 탐색
int solution(vector<int> citations) {
    for (int h = citations.size(); h >= 1; h--) {
		int cnt = 0;
        for (auto c : citations) {
            if (c >= h) cnt++;
        }
        if (cnt >= h) return h;
    }
}

// 정렬 + 선형 탐색
// int solution(vector<int> citations) {
// 	sort(citations.begin(), citations.end(), greater<int>());

//     int h = 0;

//     for (int i = 0; i < citations.size(); i++) {
//         if (citations[i] >= i + 1)  h = i + 1;
//         else break;
//     }
//     return h;
// }