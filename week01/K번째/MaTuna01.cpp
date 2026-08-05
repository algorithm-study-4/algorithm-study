#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    for (int i = 0; i < commands.size(); i++){
        int a = commands[i][0];
        int b = commands[i][1];
        int c = commands[i][2];
        vector<int> t;
        for (int j = 0; j < b - a + 1; j++){
            t.push_back(array[a-1+j]);
        }
        sort(t.begin(),t.end());
        answer.push_back(t[c-1]);
    }
    return answer;
}