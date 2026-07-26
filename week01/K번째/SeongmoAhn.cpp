#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    for (int t = 0; t < commands.size(); t++) {
        auto cur = commands[t];
        int i = cur[0] - 1;
        int j = cur[1];
        int k = cur[2] - 1;
        vector<int> arr;
        for (int idx = i; idx < j; idx++) {
            arr.push_back(array[idx]);
        }
        sort(arr.begin(), arr.end());
        answer.push_back(arr[k]);
    }
    return answer;
}