#include <string>
#include <iostream>
#include <stack>

using namespace std;

bool solution(string s)
{
    stack<char> Stack;
       
    for (char Parenthesis : s) {
        if(Parenthesis == '(')
            Stack.emplace(Parenthesis);
        
        else {
            if(true == Stack.empty())
                return false;
            
            else
                Stack.pop();
        }                        
    }
    
    return Stack.empty();
}