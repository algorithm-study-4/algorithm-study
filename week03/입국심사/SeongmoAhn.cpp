#include <string>
#include <vector>
#include <algorithm>
using namespace std;
typedef long long ll;

ll solution(int n, vector<int> times) {
    ll low = 0, high = (ll)n * *max_element(times.begin(), times.end());
    
    while (low <= high) {
        ll mid = (low + high) / 2;
        
        ll cnt = 0;
        for (auto t : times) {
           	cnt += mid / t; 
        }
        
        if (cnt >= n) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return low;
}