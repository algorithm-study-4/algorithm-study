#include <iostream>
#include <vector>

using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int price, n; // 물품의 가치, 동전 개수
    cin >> price >> n;

    vector<int> coin(n, 0); // 동전 배열
    // vector<int> dp(price + 1, 0); // dp[0] = 0; 생략해도 되나? 될 거 같음
    // -> 안 됨 밑에서 min 돌려야 해서 애초에 전부 0으로 초기화하면 안 됨
    vector<int> dp(price + 1, 500); // 큰 값으로 초기화
    dp[0] = 0;

    for (int i = 0; i < n; i++)
        cin >> coin[i];

    // price원을 만드는 최소 동전 수
    for (int i = 1; i <= price; i++)
        for (int c : coin)
        {
            if (i < c)
                continue;
            else
                dp[i] = min(dp[i], dp[i - c] + 1);
        }

    if (dp[price] == 500)
        cout << "impossible" << endl;
    else
        cout << dp[price];

    return 0;
}