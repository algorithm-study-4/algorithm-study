#include <string>
#include <vector>

using namespace std;

string solution(string s, int n) {
    string answer = "";
    for(char c : s) {
        if(c >= 'a' && c <= 'z') {
            answer += (c - 'a' + n) % 26 + 'a';
        }
        else if(c >= 'A' && c <= 'Z') {
            answer += (c - 'A' + n) % 26 + 'A';
        }
        else {
            answer += c;
        }
    }
    return answer;
}