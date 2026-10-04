#include <string>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

string digits;
bool used[7];
// 만든 수 모음 (11과 011, 같은 조각 두 장으로 만든 같은 수 -> 자동 중복 제거)
set<int> made;

bool isPrime(int x) {
    if (x < 2) return false;
    for (int i = 2; i * i <= x; i++)
        if (x % i == 0) return false;
    return true;
}

void dfs(int num, int depth) {
    // 길이 1~n 모두 후보 → 모든 노드에서 기록
    if (depth > 0) made.insert(num);
    // 조각을 다 썼으면 종료
    if (depth == (int)digits.size()) return;

    for (int i = 0; i < (int)digits.size(); i++) {
        // 이미 쓴 조각은 건너뜀
        if (used[i]) continue;
        // 선택
        used[i] = true;
        // 탐색
        dfs(num * 10 + (digits[i] - '0'), depth + 1);  
        // 취소
        used[i] = false;
    }
}

int solution(string numbers) {
    digits = numbers;
    made.clear();
    fill(used, used + 7, false);

    dfs(0, 0);

    int answer = 0;
    for (int x : made)
        if (isPrime(x)) answer++;
    return answer;
}

/*
전체 시간복잡도 : O(n!*10^(n/2))
수 만들기 과정 : O(n*n!)
 - 각 노드에서 for문으로 조각 n개 확인 => O(n)
 - set에 삽입 => O(logM)
 소수 판별 과정 : O(n!*10^(n/2))
 
 즉, n=7 일때 소수 개수 1336개 판별

*/