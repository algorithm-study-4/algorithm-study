#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int solution(vector<int> people, int limit) {
    sort(people.begin(), people.end());
    int left = 0, right = people.size() - 1;
    int cnt = 0;
    while (left <= right) {
        if (people[left] + people[right] <= limit) left++;

        right--;
        cnt++;
    }
    return cnt;
}

int main(void) {
// 50 50 70 80
vector<int> people = {70, 50, 80, 50};
int limit = 100;

cout << solution(people, limit) << endl;
    return 0;
}
