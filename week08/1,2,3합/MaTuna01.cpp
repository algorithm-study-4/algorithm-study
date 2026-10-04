#include<iostream>
using namespace std;

int n;
int cnt = 0;

void dfs(int sum) {
    // 합이 정확히 N이면 경우 하나 완성
    if (sum == n) {
        cnt++;
        return;
    }
    for (int x = 1; x <= 3; x++) {
        // 가지치기 -> N을 넘으면 더 큰 x는 볼 필요 없음
        if (sum + x > n)
            break;
        dfs(sum + x);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n;
    dfs(0);
    cout << cnt << '\n';
    return 0;
}

/*
접근 : 매 단계마다 1,2,3 중 하나를 골라 더라고, 합이 정확히 N이 되면 경우 하나를 센다.
1+2와 2+1은 다른 경우로 취급한다.
따라서 같은 수를 여러번 쓸 수 있는 중복 순열 형태이다.
상태 : 지금까지의 합 sum/ 선택: : 1,2,3 / 종료조건 sum == N/ 가지치기 : sum  + x > N
점화식으로 쓰면 f(n) = f(n-1) + f(n-2) + f(n-3) → 반복문 DP로도 풀 수 있다.
*/