#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <cmath>
using namespace std;
#define SIZE 10000000

bool isPrime[SIZE]; // false: prime
bool made[SIZE]; // 중복 수 제거
bool visited[10];
int cnt;

void dfs(string& numbers, string str) {
    if (str.size() > 0) {
        int num = stoi(str);
        if (!made[num]) {
            made[num] = true;
            if (!isPrime[num]) cnt++;
        }
    }

    for (int i = 0; i < numbers.size(); i++) {
        if (visited[i]) continue;
        if (i > 0 && numbers[i] == numbers[i - 1] && !visited[i - 1]) continue; // 같은 레벨 중복 스킵

        visited[i] = true;
        dfs(numbers, str + numbers[i]);
        visited[i] = false;
    }
}

int solution(string numbers) {
    // 에라토스 테네스 체로 소수 배열 만들어두기
    isPrime[0] = isPrime[1] = true;
    for (int i = 2; i <= sqrt(SIZE); i++) {
        if (isPrime[i] == true) continue;

        for (int j = 2 * i; j < SIZE; j += i)
            isPrime[j] = true;
    }

    sort(numbers.begin(), numbers.end());
    dfs(numbers, "");

    return cnt;
}

int main(void) {

    string numbers = "011";

cout << solution(numbers) << endl;
    return 0;
}
