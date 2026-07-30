#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int time = 0;
    queue<int> que;

    int weightSum = 0;

    int i = 0;
    while(true) {
        int now_weight = truck_weights[i];
        if(i == truck_weights.size()){
            time += bridge_length;
            break;
        }
        if(que.size() == bridge_length){
            weightSum -= que.front();
            que.pop();
        }
        if(weightSum + now_weight <= weight){
            weightSum += now_weight;
            que.push(now_weight);
            i++;
        }
        else que.push(0);
        time++;
    }
    return time;
}