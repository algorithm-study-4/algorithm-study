#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <cstdio>
using namespace std;
#define endl '\n'

int N;
string str;

// 시간 초과 나는 코드 (완전탐색, DFS 방식)
vector<string> v;
void makeStr(int l, int r, string tmp) {
    if (tmp.size() == str.size()) {
        v.push_back(tmp);
        return ;
    }

    // 왼쪽 글자 추가
    makeStr(l + 1, r, tmp + str[l]);
    // 오른쪽 글자 추가
    makeStr(l, r - 1, tmp + str[r]);
}

void solve1() {
    makeStr(0, N - 1, "");
    sort(v.begin(), v.end());
    cout << v[0];
}
// N의 범위가 2000까지 이므로 makeStr으로 만들어지는 문자열의 개수는 2^2000 개
// 너무 많음
// sort() 시간복잡도 O(N logN)만 계산해도 2000 * 2^2000 절대 불가능

// 시간 초과 해결 코드 (투포인터 방식)
void solve2() {
    string ans;
    int l = 0, r = N - 1;
    while (l <= r) {
        bool takeLeft = true;
        // 왼쪽에서 가져올지 오른쪽에서 가져올지 결정해야하는데
        // 포인트는 양쪽 문자가 같은 경우
        // CABC 가 문자열인 경우로 생각을 해보면
        // 왼쪽 오른쪽이 모두 C이기 때문에 뭘 가져와도 똑같음, 하지만 다음 문자가 달라지게 된다.
        // 1. 왼쪽 C를 가져오는 경우 => CABC
        // 2. 오른쪽 C를 가져오는 경우 => CBAC
        // 사전식 검사를 했을 때 1번 케이스가 더 먼저 온다는 걸 알 수 있고,
        // 양쪽 문자가 같은 경우 다음 문자가 더 빠른 쪽을 가져오면 된다.
        for (int i = 0; l + i < r - i; i++) {
            if (str[l + i] < str[r - i]) { takeLeft = true; break; }
            if (str[l + i] > str[r - i]) { takeLeft = false; break; }
        }

        ans += takeLeft ? str[l++] : str[r--];
    }

    // 80글자마다 줄바꿈
    for (int i = 0; i < N; i++) {
        cout << ans[i];
        if ((i + 1) % 80 == 0) cout << endl;
    }
}

int main(void) {
    cout.tie(NULL); cin.tie(NULL); ios_base::sync_with_stdio(false);
    // freopen("input.txt", "r", stdin);

    // 초기값 세팅
    cin >> N;
    for (int i = 0; i < N; i++) { char a; cin >> a; str += a; }

    // solve1();
    solve2();

    return 0;
}
