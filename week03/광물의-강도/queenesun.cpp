#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    int N, Q; // 광물 개수, 연구원 수
    cin >> N >> Q;

    vector<int> hardness(N, 0);

    for (int i = 0; i < N; i++) // 광물 배열 입력 O(n)
        cin >> hardness[i];

    sort(hardness.begin(), hardness.end()); // O(n log n)
    int n = hardness.size();

    for (int i = 0; i < Q; i++) // 연구원 수 Q만큼 반복
    {
        int srt = 0, end = 0;
        cin >> srt >> end;

        int srtIdx = n; // srt이상인 최초의 위치 인덱스
        int endIdx = n; // end초과인 최초의 인덱스

        // srtIdx 이진탐색 O(log n)
        int left = 0;
        int right = n - 1;

        while (left <= right)
        {
            int mid = left + (right - left) / 2;

            if (hardness[mid] >= srt)
            {
                srtIdx = mid;
                right = mid - 1;
            }
            else
                left = mid + 1;
        }

        // endIdx 이진탐색 O(log n)
        left = 0;
        right = n - 1;

        while (left <= right)
        {
            int mid = left + (right - left) / 2;

            if (hardness[mid] > end)
            {
                endIdx = mid;
                right = mid - 1;
            }
            else
                left = mid + 1;
        }

        cout << endIdx - srtIdx << endl;
    }
}
// 입력 O(n) + sort O(n log n) + 이진탐색 Q * 2 * O(log n)
// O((n+Q) log n)

/* lower_bound, upper_bound

- 구현 자체는 이분탐색으로 동일함.
- algorithm 헤더 안에 있는 함수

- lower_bound는 찾고자 하는 값보다 크거나 같은 값 중 가장 처음 나오는 값의 반복자를 반환
- upper_bound는 찾고자 하는 값보다 큰 값 중 처음 나오는 값의 반복자를 반환

- lower_bound(시작, 끝, 타겟)

- 비교 매개변수 사용 가능 (default: lower<>() 오름차순)
    - lower_bound(start, end, target, greater<>())내림차순 정렬로 target보다 이하인 것 중 최대인 반복자를 반환해준다.
    - upper_bound(start, end, target, greater<>())내림차순 정렬로 target보다 미만인 것 중 최대인 반복자를 반환해준다.

int left =
    lower_bound(hardness.begin(),
                hardness.end(),
                srt)
    - hardness.begin();
    // 반복자를 반환하므로 찾아낸 반복자에 시작 반복자를 빼 해당 반복자의 인덱스를 찾음

int right =
    upper_bound(hardness.begin(),
                hardness.end(),
                end)
    - hardness.begin();

cout << right - left << '\n';

*/