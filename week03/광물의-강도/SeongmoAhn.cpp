#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
#define endl '\n'
#define SIZE 

int N, Q;
vector<int> v;

void init_input() {
    cin >> N >> Q;
    while (N--) {
        int n; cin >> n;
        v.push_back(n);
    }
    sort(v.begin(), v.end());
}

void solve() {
    while (Q--) {
        int num1, num2; cin >> num1 >> num2;
        
        // 가장 앞 num1 값 찾기
        int low = 0, high = v.size() - 1, mid1;
        while (low <= high) {
            mid1 = (low + high) / 2;
            
            if (v[mid1] < num1) {
                low = mid1 + 1;
            } else {
                high = mid1 - 1;
            }
        }
        mid1 = low;

        // 가장 뒤 num2 값 찾기
        low = 0, high = v.size() - 1;
        int mid2;
        while (low <= high) {
            mid2 = (low + high) / 2;
            
            if (v[mid2] <= num2) {
                low = mid2 + 1;
            } else {
                high = mid2 - 1;
            }
        }
        mid2 = low;
        cout << mid2 - mid1 << endl;
    }
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);
    init_input();
    solve();

    return 0;
}
