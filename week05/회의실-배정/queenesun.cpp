/*
가장 많은 개수의 회의를 욱여넣으려면 -> 회의 길이가 가장 짧은 순서대로 정렬? ㄴㄴ
회의 길이가 짧다 != 회의실을 빨리 비운다
현실에서도 다른 팀이 회의를 얼마나 하는지는 중요하지 않음. 내가 쓸 시간에 끝나는지가 중요
if)
A : 1 ~ 11 (길이 10)
B : 10 ~ 12 (길이 2)
더 짧은 B가 오히려 더 늦게 끝남

-> 언제 회의실을 비워주는지가 중요. 가장 빨리 끝나는 회의를 선택해야 앞으로 선택할 수 있는 회의가 가장 많아진다.
따라서 정렬 기준 = 종료 시간 오름차순 (종료 시간이 같으면 시작 시간 오름차순)
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// 종료 시간 오름차순 정렬 (종료 시간이 같으면 시작 시간 오름차순)
bool cmp(vector<int> &v1, vector<int> &v2)
{
    if (v1[1] == v2[1])       // 종료 시간이 같으면
        return v1[0] < v2[0]; // v1이 v2보다 앞에 와야 하면 true (시작 시간이 더 작은 회의를 앞에 배치)
    else
        return v1[1] < v2[1]; // v1이 v2보다 앞에 와야 하면 true (종료 시간이 더 작은 회의를 앞에 배치)
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; // 회의실 예약 스케쥴의 개수
    cin >> N;

    vector<vector<int>> schedule(N, vector<int>(2, 0)); // N행 2열 벡터 0으로 초기화

    for (int i = 0; i < N; i++)
        cin >> schedule[i][0] >> schedule[i][1];

    // sort의 세 번째 인자(사용자가 정의한 함수를 기준으로 정렬) -> default는 오름차순 정렬
    sort(schedule.begin(), schedule.end(), cmp); // cmp에 {1, 2}, {5, 8} 형태로 (각각 vector<int>) 들어감

    int answer = 1;
    int end_time = schedule[0][1];

    for (int i = 1; i < N; i++)
        if (end_time <= schedule[i][0])
        {
            answer++;
            end_time = schedule[i][1];
        }

    cout << answer;

    return 0;
}

/*
회의 N개
- 입력 O(N)
- sort O(N log N) -> N log N번정도 cmp함수 호출(cmp 내부에선 상수 시간만 연산 O(1)) => 정렬 전체 O(N log N)
- 정렬 후 회의 선택 O(N)
*/