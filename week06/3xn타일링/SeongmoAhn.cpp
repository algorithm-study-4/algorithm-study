#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;
#define MOD 1000000007
#define SIZE 5001

int solution(int n) {
    if (n % 2) return 0;

    long long dp[SIZE] = {1, 0, 3};
    long long sum[SIZE] = {1, 0, 4};
    for (int i = 4; i < SIZE; i += 2) {
        // dp[i] = (dp[i - 2] * 3 + sum[i - 4] * 2) % MOD;
        // sum[i] = (sum[i - 2] + dp[i]) % MOD;
        dp[i] = ((dp[i - 2] * 4) % MOD - dp[i - 4] + MOD) % MOD;
    }

    return (int)dp[n];
}

int main(void) {
    int n = 4;

cout << solution(n) << endl;

    return 0;
}
