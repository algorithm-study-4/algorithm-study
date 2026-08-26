#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, w; // 광물의 개수, 인벤토리 길이
    cin >> n >> w;

    vector<vector<int>> rock(n, vector<int>(2, 0)); // 광물 배열
    vector<int> dp(w + 1, -1);

    dp[0] = 0;

    for (int i = 0; i < n; i++)
        cin >> rock[i][0] >> rock[i][1];

    // 각각의 광물을 하나씩 사용
    for (int i = 0; i < n; i++)
    {
        int l = rock[i][0];
        int h = rock[i][1];
        int weight = l * h;

        // 가로로 놓는 경우
        for (int j = w; j >= l; j--)
        {
            if (dp[j - l] != -1)
                dp[j] = max(dp[j], dp[j - l] + weight);
        }

        // 세로로 놓는 경우
        for (int j = w; j >= h; j--)
        {
            if (dp[j - h] != -1)
                dp[j] = max(dp[j], dp[j - h] + weight);
        }
    }

    if (dp[w] == -1)
        cout << 0 << endl;
    else
        cout << dp[w] << endl;

    return 0;
}