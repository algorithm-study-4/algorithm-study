#include <iostream>
#include <vector>

using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int price;
    cin >> price;

    int cnt = 0;

    int arr[4] = {500, 100, 50, 10};

    for (int i = 0; i < 4; i++)
    {
        cnt += price / arr[i];
        price = price % arr[i];
    }

    cout << cnt;

    return 0;
}