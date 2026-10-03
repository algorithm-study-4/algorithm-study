#include <iostream>

using namespace std;

int n;
int cnt = 0;

void bt(int sum)
{
    if (sum == n) // n이 되는 경우 cnt++ 후 return
    {
        cnt++;
        return;
    }

    if (sum > n) // n보다 커지는 경우 더 보지 않고 return
        return;

    for (int i = 1; i <= 3; i++) // 1, 2, 3 재귀
    {
        if (sum + i <= n)
            bt(sum + i);
        else
            return;
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    /*
    합이 N과 같아지면 cnt++
    같은 수를 몇 번이든 사용할 수 있으므로 visited 배열 불필요
    합이 N을 넘으면 더 내려가도 답이 될 수 없으니 바로 돌아감

    [시간 복잡도]
    1. 재귀 한 번에 최대 세 개 호출
    2. 최대 깊이 = 1만 계속 고를 때 = n
    3. 따라서 노드 수는 3^n
    => O(3^n)
    */

    cin >> n;

    bt(0);

    cout << cnt << '\n';

    return 0;
}