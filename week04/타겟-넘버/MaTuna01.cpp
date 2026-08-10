#include<vector>
#include<iostream>
using namespace std;

void dfs(const vector<int>& numbers, int target, int index, int current_sum, int& answer) {

if(index == numbers.size()){
    if(current_sum == target){
        answer ++;
    }
    return;
}

    dfs(numbers, target, index + 1, current_sum + numbers[index], answer);

    dfs(numbers, target, index + 1, current_sum - numbers[index], answer);
}

int solution(vector<int> numbers, int target) {
    int answer = 0;
    dfs(numbers, target, 0, 0, answer);
    return answer;
}