#include <iostream>
#include <vector>

using namespace std;

vector<int> mem;

int fibo(int n)
{
    if (n <= 1)
        return n;

    if (mem[n] != 0)
        return mem[n];
    else
    {
        mem[n] = (fibo(n - 2) + fibo(n - 1)) % 1000000007;
        return mem[n];
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n;
    cin >> n;

    mem.resize(n + 1, 0);

    int res = fibo(n);

    cout << res << endl;

    return 0;
}