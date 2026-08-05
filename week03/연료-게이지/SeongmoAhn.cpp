#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
#define endl '\n'
#define SIZE 

// 완전 탐색
// void solve() {
//     int N; cin >> N;
//     while (N--) {
//         string s; cin >> s;

//         int i;
//         for (i = 0; i < s.size(); i++) {
//             if (s[i] == '_') break;
//         }
//         cout << i * 100 / s.size() << '%' << endl;
//     }
// }

// 이진 탐색
void solve() {
    int N; cin >> N;
    while (N--) {
        string s; cin >> s;

        if (s[0] == '_') {
            cout << "0%" << endl;
            continue;
        } else if (s[s.size() - 1] == '#') {
            cout << "100%" << endl;
            continue;
        }

        int low = 0, high = s.size() - 1, mid;
        while (low <= high) {
            mid = (low + high) / 2;

            if (s[mid] == '#' && s[mid + 1] == '_') {
                cout << (mid + 1) * 100 / s.size() << '%' << endl;
                break;
            } else if (s[mid] == '#') {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    solve();

    return 0;
}
