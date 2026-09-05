#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int k; // 원하는 스코빌 지수
    int n; // scoville 배열 요소 수
    cin >> k >> n;

    priority_queue<int, vector<int>, greater<int>> scoville;
    int cnt = 0;

    for (int i = 0; i < n; i++)
    {
        int a;
        cin >> a;
        scoville.push(a);
    }

    while (1)
    {
        if (scoville.top() >= k)
        {
            cout << cnt << endl;
            break;
        }

        if (scoville.size() >= 2)
        {
            int first = scoville.top();
            scoville.pop();
            int second = scoville.top();
            scoville.pop();

            int mix = first + second * 2;
            scoville.push(mix);

            cnt++;
        }
        else if (scoville.top() < k)
        {
            cout << "-1" << endl;
            break;
        }
    }

    return 0;
}