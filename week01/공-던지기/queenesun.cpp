#include <vector>
#include <string>

using namespace std;

// https://school.programmers.co.kr/learn/courses/30/lessons/120843

int solution(vector<int> numbers, int k)
{
    int answer = 0;
    answer = numbers[(k - 1) * 2 % numbers.size()];
    return answer;
}