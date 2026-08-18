#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve)
{
    sort(lost.begin(), lost.end());       // 정렬 O(l log l)
    sort(reserve.begin(), reserve.end()); // 정렬 O(r log r)

    int cnt = 0;
    int answer = n - lost.size(); // 잃어버린 애들에 대해서만 고려하면 됨

    // 여분 있는 애 중 안 가져온 애가 있으면 -1
    for (int i = 0; i < lost.size(); i++) // 최악의 경우 (l, r = n) O(n^2)
        for (int j = 0; j < reserve.size(); j++)
            if (lost[i] == reserve[j])
            {
                reserve[j] = -1;
                lost[i] = -1;
                answer++;
                break;
            }

    // lost 배열 돌면서 reserve 배열에서 smaller, bigger 순서대로 찾기
    for (int i = 0; i < lost.size(); i++) // 최악의 경우 (l = n, r = n보다 작은 어떤 수) O(n * (n - x)) = O(n ^ 2)
    {
        int smaller = lost[i] - 1;
        int bigger = lost[i] + 1;

        if (lost[i] != -1)
        {
            for (int j = 0; j < reserve.size(); j++)
            {
                // smaller 먼저 확인 -> 있으면 -1 넣고 break
                if (reserve[j] == smaller)
                {
                    reserve[j] = -1;
                    answer++;
                    break;
                }

                // smaller에서 못 찾은 경우 bigger 확인
                if (reserve[j] == bigger)
                {
                    reserve[j] = -1;
                    answer++;
                    break;
                }
            }
        }
        else
            continue;
    }

    return answer;
}