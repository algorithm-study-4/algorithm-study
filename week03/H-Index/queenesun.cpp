#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> citations)
{
    // 정렬 사용
    // sort(citations.begin(), citations.end()); // 시간복잡도 O(nlogn)
    // int n = citations.size();

    // for (int i = 0; i < n; i++) // n회 반복 O(n)
    //     if (citations[i] >= n - i) // i번지 논문의 인용 횟수가 citations[i] 이상 인용된 논문의 개수보다 크면 조건 만족
    //         return n - i;

    // return 0;

    // 정렬 사용 X - 덕규 코드 보고 함
    int n = citations.size();
    vector<int> count(n + 1, 0);

    for (int i = 0; i < n; i++) // O(n)
        if (citations[i] >= n)
            count[n] += 1;
        else
            count[citations[i]] += 1;

    // 뒤에서부터 누적합
    int papers = 0;

    for (int i = n; i >= 0; i--)
    {
        papers += count[i];

        if (papers >= i)
            return i;
    }
}