/*
 AI가 해주셨습니다
 한 명씩 넣어서 시뮬 -> 최대 10억 명이라 불가능
 "시간"을 이분탐색한다
 -> 주어진 시간 T에 몇 명을 처리할 수 있는지 구할 수 있음.
 시간 T가 증가하면 '처리 가능한 사람 수'는 절대 감소하지 않는다.
*/

#include <vector>
#include <algorithm>

using namespace std;

long long solution(int n, vector<int> times)
{
    // 최악의 경우 1,000,000,000 × 1,000,000,000 = 10^18 (최대 범위 1 ~ 10^18)
    // 반복 횟수 = log₂(10^18) = 최대 약 60
    long long answer = 0;

    // 탐색 범위
    long long left = 1; // 최소값: 가장 빠른 경우라도 최소 1분은 걸림
    // 최대값: 가장 느린 심사관이 모두를 처리하는 경우
    long long right = (long long)*max_element(times.begin(), times.end()) * n;

    // 이분탐색
    while (left <= right)
    {
        long long mid = left + (right - left) / 2;

        long long people = 0;

        for (int time : times) // 심사관 수를 m이라고 하면 시간복잡도 O(m)
        {
            people += mid / time; // 각 심사관이 처리 가능한 사람 수 누적합

            if (people >= n) // 목표 인원 이상을 계산했다면 더 계산할 필요 X
                break;
        }

        if (people >= n) // 가능한 시간이므로 정답 후보로 저장 후 더 작은 시간 탐색
        {
            answer = mid;
            right = mid - 1;
        }
        else
            left = mid + 1; // 불가능한 시간이므로 더 큰 시간 탐색
    }

    return answer;
}
// 이분탐색 O(log(10^18)) * 한 번 판별 O(m) = O(60m)