#include <string>
#include <vector>
#include <algorithm>

using namespace std;

// https://school.programmers.co.kr/learn/courses/30/lessons/42748

vector<int> solution(vector<int> array, vector<vector<int>> commands)
{
    vector<int> answer;
    for (int i = 0; i < commands.size(); i++)
    {
        int srt = commands[i][0];
        int end = commands[i][1];
        int num = commands[i][2];

        vector<int> newArr = vector<int>(array.begin() + srt - 1, array.begin() + end);
        sort(newArr.begin(), newArr.end());

        answer.push_back(newArr[num - 1]);
    }
    return answer;
}