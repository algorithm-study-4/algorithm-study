#include <string>
#include <vector>

using namespace std;

int solution(vector<int> ingredient) {
    int answer = 0;
    string hamburger;
    for (int i = 0; i < ingredient.size(); i++) {
        hamburger += to_string(ingredient[i]);
        if (hamburger.size() < 4)
            continue;
        string temp = hamburger.substr(hamburger.size()-4);
        if (temp == "1231") {
            answer++;
            hamburger=hamburger.substr(0, hamburger.size()-4);
        }
    }
    return answer;
}