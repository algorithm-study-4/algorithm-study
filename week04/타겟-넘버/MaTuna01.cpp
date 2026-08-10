#include<vector>
#include<iostream>
#include<queue>
using namespace std;

// void dfs(const vector<int>& numbers, int target, int index, int current_sum, int& answer) {

// if(index == numbers.size()){
//     if(current_sum == target){
//         answer ++;
//     }
//     return;
// }

//     dfs(numbers, target, index + 1, current_sum + numbers[index], answer);

//     dfs(numbers, target, index + 1, current_sum - numbers[index], answer);
// }


int solution(vector<int> numbers, int target) {
    int answer = 0;
    
    queue<pair<int,int>> q;
    q.push({0,0});

    while ((!q.empty())){
        int index = q.front().first;
        int current_sum = q.front().second;
        q.pop();

        if (index == numbers.size()){
            if (current_sum == target){
                answer ++;
            }
        } else {
            q.push({index + 1, current_sum + numbers[index]});
            q.push({index + 1, current_sum - numbers[index]});
        }
    }
    return answer;
}