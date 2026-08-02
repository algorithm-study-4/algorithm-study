#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> sizes)
{
    int answer = 0;
    int max = sizes[0][0]; // 가장 긴 길이
    int max2 = 0;          // 각 명함의 가로, 세로 길이 중 더 짧은 것의 최대값

    for (int i = 0; i < sizes.size(); i++)        // 배열의 요소가 n개라고 가정
        for (int j = 0; j < sizes[0].size(); j++) // 각 요소는 길이 2 고정
            if (sizes[i][j] >= max)
                max = sizes[i][j]; // 시간복잡도 O(2n)

    for (int i = 0; i < sizes.size(); i++)         // n번
        if (min(sizes[i][0], sizes[i][1]) >= max2) // min 함수 = 숫자 두 개 비교 O(1)
            max2 = min(sizes[i][0], sizes[i][1]);  // 시간복잡도 O(n)

    answer = max * max2;

    return answer;
    // total 시간복잡도 = O(2n + n) = O(n)
}