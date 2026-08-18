#include <iostream>
#include <string>

using namespace std;

int main()
{
    int T;    // 입력할 문자열 개수
    cin >> T; // 문자열 하나 당 O(log N)이므로 O(T log N)

    while (T--)
    {
        string str;
        cin >> str;

        int left = 0;
        int right = str.length() - 1;
        int pos = str.length(); // 처음 "_"가 나온 위치 저장
        // 습관적으로 0으로 초기화했는데 _가 하나도 없으면 큰일!이기 때문에 저렇게 써야 함

        while (left <= right)
        {
            int mid = left + (right - left) / 2;

            if (str[mid] == '_')
            {
                pos = mid;
                right = mid - 1;
            }
            else
                left = mid + 1;
        }
        cout << pos * 100 / str.length() << endl;
    }
}