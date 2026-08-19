#include <iostream>
#include <vector>
using namespace std;


int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    int N; cin >> N;
    vector<int> coins = {500, 100, 50, 10};
    int cnt = 0;
    for (auto coin : coins) {
        while (coin <= N) {
            N -= coin;
            cnt++;
        }
    }
    cout << cnt;

    return 0;
}
