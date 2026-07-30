#include <string>
#include <vector>

using namespace std;

int solution(vector<int> ingredient)
{
    int answer = 0;
    vector<int> stk;

    for (int ing : ingredient)
    {
        stk.push_back(ing); // ingredient 벡터의 모든 요소를 stk 벡터에 push_back 하며 그 때마다 검사한다

        int size = stk.size();

        if (size >= 4 && stk[size - 4] == 1 && stk[size - 3] == 2 && stk[size - 2] == 3 && stk[size - 1] == 1)
        {
            stk.pop_back();
            stk.pop_back();
            stk.pop_back();
            stk.pop_back();

            answer++;
        }
    }

    return answer;
}