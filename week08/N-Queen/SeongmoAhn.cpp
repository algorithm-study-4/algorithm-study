#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <cmath>
using namespace std;

int N;
int col[12]; // col[row] = 그 행에 놓인 퀸의 열 위치
int cnt;

// row행 c열에 퀸을 놓을 수 있는지 확인
bool canPut(int row, int c) {
    for (int r = 0; r < row; r++) {
        if (col[r] == c) return false; // 열 충돌 검사
        if (abs(col[r] - c) == row - r) return false; // 대각선 충돌 검사
    }
    return true;
}

void backtrack(int row) {
    if (row == N) {
        cnt++;
        return;
    }

    for (int c = 0; c < N; c++) {
        if (canPut(row, c)) {
            col[row] = c;
            backtrack(row + 1);
        }
    }
}

int solution(int n) {
    N = n;
    backtrack(0);
    return cnt;
}

int main(void) {
    int n = 4;

cout << solution(n) << endl;
}
