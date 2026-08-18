#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer = 0;
    int idx = 0;
    int time = 0;
    int cur = 0;
    queue<int> q;
    for (int i = 0; i < bridge_length; i++) q.push(0);
    
    while (idx < truck_weights.size()) {
        time++;
        cur -= q.front();
        q.pop();
        
        if (cur + truck_weights[idx] <= weight) {
            q.push(truck_weights[idx]);
            cur += truck_weights[idx];
            idx++;
        }
        else {
            q.push(0);
        }
    }
    answer = time + bridge_length;
    return answer;
}