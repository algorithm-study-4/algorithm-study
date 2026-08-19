#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N;
    cin >> N;

    vector<int> weight(26, 0);

    for (int i = 0; i < N; i++)
    {
        string word;
        cin >> word;

        int place = 1;

        // 오른쪽부터 자리값 계산
        for (int j = word.size() - 1; j >= 0; j--)
        {
            int idx = word[j] - 'A';

            weight[idx] += place;

            place *= 10;
        }
    }

    // 가중치가 큰 순서대로 정렬
    sort(weight.begin(), weight.end(), greater<int>()); // 내림차순 정렬

    int answer = 0;
    int num = 9;

    for (int i = 0; i < 26 && weight[i] > 0; i++)
    {
        answer += weight[i] * num;
        num--;
    }

    cout << answer;

    return 0;
}