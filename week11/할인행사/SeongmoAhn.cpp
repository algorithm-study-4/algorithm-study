#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <map>
using namespace std;

int solution(vector<string> want, vector<int> number, vector<string> discount) {
    map<string, int> cnt;
    int ans = 0;

    for (int i = 0; i < discount.size(); i++) {
        cnt[discount[i]]++; // 슬라이딩 윈도우 오른쪽 추가
        if (i >= 10) cnt[discount[i - 10]]--; // 슬라이딩윈도우 왼쪽 제외

        // 슬라이딩윈도우에 할인 상품이 10개 들어온 경우부터 검사 시작
        if (i >= 9) {
            bool flag = true;
            for (int j = 0; j < want.size(); j++) {
                // 할인중인 상품과 내가 원하는 제품의 개수가 다르면 구매하지 않음
                if (cnt[want[j]] != number[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) ans++;
        }
    }
    return ans;
}

int main(void) {
    cout << solution({"banana", "apple", "rice", "pork", "pot"}, {3, 2, 2, 2, 1}, {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"}) << endl;
    cout << solution({"apple"}, {10}, {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"}) << endl;
}
