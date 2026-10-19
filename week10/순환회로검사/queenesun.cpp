#include <iostream>
#include <vector>

using namespace std;

int parent[1001]; // 입력으로 들어올 수 있는 노드의 개수는 최대 1000개

// find 함수 (노드 x를 받아서 x가 속한 그룹의 루트 번호를 돌려줌)
int find(int x)
{
    if (parent[x] != x)
        parent[x] = find(parent[x]);

    return parent[x];
}

// C++에서 union은 예약어이므로 함수명 unite로 지음
// union 함수 (A가 속한 그룹과 B가 속한 그룹을 하나로 합침)
bool unite(int x, int y)
{
    x = find(x);
    y = find(y);

    if (x == y) // 이미 같은 집합인 경우
        return false;

    if (x < y)
        parent[y] = x;
    else
        parent[x] = y;

    return true;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n; // 노드의 개수
    cin >> n;

    // 초기화
    for (int i = 0; i < n; i++)
        parent[i] = i;

    // 인접 행렬 읽으며 사이클 검사
    // 이미 같은 그룹인 두 노드를 연결하려 하면 -> 사이클 생김
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
        {
            int num;
            cin >> num;

            if (j < i)
                if (num == 1)
                    if (!unite(i, j))
                    {
                        cout << "WARNING" << '\n';
                        return 0;
                    }
        }

    cout << "STABLE" << '\n';

    return 0;
}