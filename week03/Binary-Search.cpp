#include <iostream>
#include <vector>
//algorithm 헤더에 있는 Binary_Search를 사용할 수 있지만, 직접 구현해 해결
#include <algorithm>

using namespace std;

// Binary Search 직접 구현
bool binarySearch(const vector<int>& arr, int target) {
    int left = 0;
    int right = arr.size() - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return true;
        }
        else if (arr[mid] < target) {
            left = mid + 1;
        }
        else {
            right = mid - 1;
        }
    }

    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;

    vector<int> arr(n);
    for (int i = 0; i < n; ++i) {
        cin >> arr[i];
    }
    //이진탐색 수행 전 정렬
    sort(arr.begin(), arr.end());

    int k;
    cin >> k;

    for (int i = 0; i < k; ++i) {
        int target;
        cin >> target;

        if (binarySearch(arr, target)) {
            cout << "O";
        }
        else {
            cout << "X";
        }
    }

    cout << "\n";

    return 0;
}