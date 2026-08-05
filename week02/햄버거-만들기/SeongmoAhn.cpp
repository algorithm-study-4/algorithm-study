#include <string>
#include <vector>

using namespace std;

int solution(vector<int> ingredient) {
    int answer = 0;
    vector<int> s;
    for (auto n : ingredient) {
        s.push_back(n);
        if (n != 1 || s.size() < 4) continue;

        if (s[s.size() - 2] == 3 && s[s.size() - 3] == 2 && s[s.size() - 4] == 1) {
            answer++;
            s.pop_back(); s.pop_back(); s.pop_back(); s.pop_back(); }
    }
    return answer;
}