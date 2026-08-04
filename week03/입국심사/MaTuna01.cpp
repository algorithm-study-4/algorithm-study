#include <algorithm>
#include <string>
#include <vector>

using namespace std;

// 변수 타입이 long long으로 설정된 이유 : int 타입의 오버플로우를 방지하기 위함
long long solution(int n, vector<int> times) {
    long long answer = 0;
    sort(times.begin(), times.end());

    //최소시간 부여
    long long min = 1;
    //최대시간 부여(심사하는 시간이 가장 오래 걸리는 심사관이 전원을 심사하는 경우)
    long long max = n * (long long)times.back();

    while (min <= max) {

        //중간값 계산
        long long avg = (max + min) / 2;
        long long tmp = 0;

        for (int i = 0; i < times.size(); i++) {
			//각 심사관이 심사할 수 있는 사람 수를 계산하여 tmp에 누적
            tmp += (avg / (long long)times[i]);
        }

		//tmp가 n 이상이면, 심사할 수 있는 사람 수가 충분하므로 우선 answer에 저장하고 시간을 줄여서 더 빠른 시간 탐색
        if (tmp >= n) {
            max = avg - 1;
            answer = avg;
        }
        else min = avg + 1;
    }
    return answer;
}