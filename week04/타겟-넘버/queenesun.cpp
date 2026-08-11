// numbers 배열의 모든 숫자에 대해 양수-음수를 벡터에 저장한다 (X)
// 각 숫자마다 + / - 중 어떤 걸 붙일지 DFS로 결정한다 (O)
// 방문 체크가 아니라 '현재 몇 번째 숫자까지 사용했는지' 체크 (그래프 DFS vs 백트래킹 DFS)

#include <string>
#include <vector>

using namespace std;

// vector<int> number;
// int targetNum = 0;
int cnt = 0;

void dfs(vector<int> &numbers, int target, int idx, int sum)
{
    if (idx == numbers.size())
    {
        if (sum == target)
            cnt++;

        return;
    }

    dfs(numbers, target, idx + 1, sum + numbers[idx]);
    dfs(numbers, target, idx + 1, sum - numbers[idx]);
    // numbers 배열의 size가 N이라면 시간복잡도는 O(2^N)
}

int solution(vector<int> numbers, int target)
{
    // number = numbers; // 전역으로 쓰기 위해 복사해줌
    // targetNum = target;

    dfs(numbers, target, 0, 0);

    return cnt;
}