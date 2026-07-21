#include <string>
#include <vector>

using namespace std;

// https://school.programmers.co.kr/learn/courses/30/lessons/12926

string solution(string s, int n)
{
    string answer = "";
    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == ' ')
            answer += ' ';
        else if ('a' <= s[i] && s[i] <= 'z')
            answer += (s[i] - 'a' + n) % 26 + 'a';
        else
            answer += (s[i] - 'A' + n) % 26 + 'A';
    }
    return answer;
}
