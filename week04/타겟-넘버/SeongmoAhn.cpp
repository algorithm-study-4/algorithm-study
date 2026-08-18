#include <string>
#include <vector>
#include <queue>
using namespace std;

int dfs(vector<int> n, int idx, int target, int sum) {
    if (idx == n.size()) {
        if (sum == target) return 1;
        return 0;
    }
    int plus = dfs(n, idx + 1, target, sum + n[idx]);
    int minus = dfs(n, idx + 1, target, sum - n[idx]);
    return plus + minus;
}

int bfs(vector<int> numbers, int target) {
    int cnt = 0;
    int size = numbers.size();
    queue<pair<int, int>> q; // <sum, idx>
    q.push({0, 0});
    
    while (!q.empty()) {
        auto [sum, idx] = q.front(); q.pop();
        
        if (idx == size) {
            if (sum == target) cnt++;
            continue;
        }
        
        q.push({sum + numbers[idx], idx + 1});
        q.push({sum - numbers[idx], idx + 1});
    }
    
    return cnt;
}

int solution(vector<int> numbers, int target) {
    // return dfs(numbers, 0, target, 0);
    return bfs(numbers, target);
}