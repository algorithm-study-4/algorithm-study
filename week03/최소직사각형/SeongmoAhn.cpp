#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> sizes) {
    int w, h;
    int a = 0, b = 0;
    
    for (auto &s : sizes) {
        w = max(s[0], s[1]);
        h = min(s[0], s[1]);
        
       	a = max(a, w);
       	b = max(b, h);
    }
    
    return a * b;
}