#include <string>
#include <vector>

using namespace std;

bool solution(string s) {
    int stack = 0;
    for (int i = 0; i < s.size(); i++) {
        if (s[i] == '(') {
            stack++;
        } else {
            if (stack <= 0)
                return false;
            stack--;
        }
    }

    return stack > 0 ? false : true;
}