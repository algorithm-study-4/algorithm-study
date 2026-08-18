#include <string>
#include <iostream>
#include <vector>

using namespace std;

bool solution(string s)
{
    bool answer = true;
    vector<char> stack;

    if (s[0] == ')' || s[s.length() - 1] == '(')
        answer = false;
    else
    {
        for (int i = 0; i < s.length(); i++)
        {
            stack.push_back(s[i]);

            if (stack.size() >= 2)
                if (stack[stack.size() - 2] == '(' && stack[stack.size() - 1] == ')')
                {
                    stack.pop_back();
                    stack.pop_back();
                }
                else
                    continue;
        }

        if (stack.empty())
            answer = true;
        else
            answer = false;
    }

    return answer;
}