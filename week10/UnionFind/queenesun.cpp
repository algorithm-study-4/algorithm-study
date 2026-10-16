#include <iostream>

using namespace std;

int parent[1001]; // 각 노드의 부모를 저장할 배열

// find 함수 (노드 x를 받아서 x가 속한 그룹의 루트 번호를 돌려줌)
int find(int x)
{
    if (parent[x] != x)
        parent[x] = find(parent[x]);

    return parent[x];
}

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

    int n, q; // 노드 맨 끝 번호, 쿼리 개수
    cin >> n >> q;

    // 초기화
    for (int i = 1; i <= n; i++)
        parent[i] = i;

    for (int i = 0; i < q; i++)
    {
        int query, a, b;
        cin >> query >> a >> b;

        switch (query)
        {
        case 0:
            if (find(a) == find(b))
                cout << "YES" << '\n';
            else
                cout << "NO" << '\n';
            break;

        case 1:
            unite(a, b);
            break;
        }
    }

    return 0;
}