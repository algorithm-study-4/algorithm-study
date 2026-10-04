#include <iostream>
#include <string>
#include <set>

using namespace std;

string pieces;
bool visited[7];
set<int> nums;

bool isPrime(int n)
{
    if (n < 2)
        return false;

    for (int i = 2; i * i <= n; i++)
        if (n % i == 0)
            return false;

    return true;
}

void dfs(string number)
{
    if (!number.empty())
        nums.insert(stoi(number));

    for (int i = 0; i < pieces.length(); i++)
    {
        if (visited[i])
            continue;

        visited[i] = true;
        dfs(number + pieces[i]);
        visited[i] = false;
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> pieces;

    dfs("");

    int cnt = 0;

    for (int x : nums)
        if (isPrime(x))
            cnt++;

    cout << cnt << '\n';

    return 0;
}