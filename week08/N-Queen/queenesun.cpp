#include <iostream>

using namespace std;

int n; // (n * n) chess board
int cnt = 0;
bool visitedCol[12];
bool visitedRD[23];
bool visitedLD[23];

void dfs(int row)
{
    if (row == n)
    {
        cnt++;
        return;
    }

    for (int col = 0; col < n; col++)
    {
        int d1 = row - col + n - 1;
        int d2 = row + col;

        if (visitedCol[col] || visitedRD[d1] || visitedLD[d2])
            continue;

        visitedCol[col] = true;
        visitedRD[d1] = true;
        visitedLD[d2] = true;

        dfs(row + 1);

        visitedCol[col] = false;
        visitedRD[d1] = false;
        visitedLD[d2] = false;
    }

}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n;

    dfs(0);

    cout << cnt << '\n';

    return 0;
}