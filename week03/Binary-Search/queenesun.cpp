#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    int n;
    cin >> n;

    vector<int> a(n);
    for (int i = 0; i < n; i++)
        cin >> a[i];

    // O(log N) 속도 => 이진 탐색
    sort(a.begin(), a.end()); // 정렬 O(N log N)

    int m;
    cin >> m;

    for (int test_case = 1; test_case <= m; test_case++) // m번 반복
    {
        bool found = false;
        int target;
        cin >> target;

        // bool found = binary_search(a.begin(), a.end(), target); // O(log N)

        int left = 0;
        int right = n - 1;

        while (left <= right) // O(log n)
        {
            // int mid = (right - left) / 2;
            int mid = left + (right - left) / 2;
            if (a[mid] == target)
            {
                found = true;
                break;
            }
            else if (a[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }

        if (found)
            cout << "O";
        else
            cout << "X";
    }

    return 0;
}