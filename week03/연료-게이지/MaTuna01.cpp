#include <iostream>
#include <string>

using namespace std;

int get_filled_count(const string& s) {
    int left = 0;
    int right = s.length(); // 문자열의 전체 길이

    while (left < right) {
        int mid = left + (right - left) / 2;
        //가운데 위치 문자 # 여부 확인
        if (s[mid] == '#') {
            left = mid + 1;
        }
        else {
            right = mid;
        }
    }

    return left;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    if (!(cin >> n)) return 0;

    for (int i = 0; i < n; ++i) {
        string s;
        cin >> s;

        int total_len = s.length();
        // #의가장 오른쪽 인덱스 반환
        int filled_len = get_filled_count(s);
        // 퍼센트 계산
        int percentage = (filled_len * 100) / total_len;

        cout << percentage << "%\n";
    }
    return 0;
}