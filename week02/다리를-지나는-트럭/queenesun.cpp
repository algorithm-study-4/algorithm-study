#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights)
{
    int answer = 0;
    int bridge_weight = 0;
    int truck_idx = 0;
    queue<int> bridge;

    while (truck_idx < truck_weights.size())
    {
        answer++;

        if (bridge.size() == bridge_length)
        {
            bridge_weight -= bridge.front();
            bridge.pop();
        }

        if (bridge_weight + truck_weights[truck_idx] <= weight)
        {
            bridge_weight += truck_weights[truck_idx];
            bridge.push(truck_weights[truck_idx]);
            truck_idx++;
        }
        else
            bridge.push(0);
        }

    answer += bridge_length;

    return answer;
}