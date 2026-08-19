#include <iostream>
#include <algorithm>
#include <string>
using namespace std;

string solution(string number, int k) {
    string str = "";
    int cnt = 0;
    for (char ch : number) {
        int n = ch - '0';
        while (!str.empty() && cnt < k) {
            int top = str.back() - '0';
            if (n > top) {
                str.pop_back();
                cnt++;
            } else break;
        }
        str.push_back(ch);
    }
    str = str.substr(0, str.size() - (k - cnt));
    return str;
}

int main(void) {
    string number = "1924";
    int k = 2;

    cout << solution(number, k) << endl;

    return 0;
}
