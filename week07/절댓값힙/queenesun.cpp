#include <iostream>
#include <queue>
#include <cstdlib> // abs 함수를 위한 헤더

using namespace std;

struct cmp
{
    bool operator()(int a, int b)
    {
        // a와 b의 절댓값을 별도 변수에 넣음
        int absA = abs(a);
        int absB = abs(b);

        // a와 b의 절댓값 비교
        if (absA != absB) // 절댓값이 다를 경우
        {
            if (absA < absB)  // 둘 중 뭐가 더 낮은 우선순위인지 결정
                return false; // false를 반환하면 a가 b보다 우선순위가 높다는 뜻
            else
                return true; // true를 반환하면 a가 b보다 우선순위가 낮다는 뜻
        }
        else // 절댓값이 같을 경우
        {
            if (a < b)        // 둘 중 뭐가 더 작은지 판단
                return false; // a가 더 작으면 a가 b보다 우선순위 높음
            else
                return true; // a가 더 크면 a가 b보다 우선순위 낮음
        }
    }
};

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; // query의 수
    cin >> n;

    // 절댓값이 가장 작은 값이 우선순위가 높으므로 구조체 cmp 사용.
    // 큐 내부에서 두 원소를 비교할 때마다 cmp 호출한다.
    priority_queue<int, vector<int>, cmp> pq;

    for (int i = 0; i < n; i++) // 다음 n개의 줄에 query 정보 들어옴
    {
        int cmd;
        cin >> cmd;

        if (cmd != 0)
            pq.push(cmd); // 절대값 힙에 값 삽입
        else
        {
            if (pq.size() == 0)      // 출력할 값이 존재하지 않으면
                cout << "0" << endl; // 0 출력
            else
            {
                cout << pq.top() << endl; // 절댓값이 가장 작은 값 출력 후 개행
                pq.pop();                 // 값 삭제
            }
        }
    }

    return 0;
}
// n번 명령이 들어오고, 각 명령마다 트리의 높이만큼 비교가 일어남
// 완전 이진 트리에서 원소가 n개 있을 때 트리의 높이는 log n
// 따라서 시간복잡도는 O(n * log n)