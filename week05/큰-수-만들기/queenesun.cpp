#include <string>
#include <vector>

using namespace std;

string solution(string number, int k)
{
    string answer;

    for (char c : number)
    {
        while (k > 0 && !answer.empty() && answer.back() < c)
        {
            answer.pop_back();
            k--;
        }

        answer.push_back(c);
    }

    // 아직 삭제할 게 남았다면 뒤에서 삭제
    if (k > 0)
        answer.erase(answer.size() - k, k);

    return answer;

    // erase 사용 -> 시간 초과
    //     int start = 0;

    //     while(k > 0)
    //     {
    //         if (start + 1 >= number.size()) {
    //             number.erase(number.size() - 1, 1);
    //             k--;
    //             start = 0;
    //             continue;
    //         }

    //         if(number[start] < number[start + 1])
    //         {
    //             number.erase(start, 1);
    //             k--;
    //             start = 0;
    //         }
    //         else
    //             start++;
    //     }

    //     return number;
}