#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int INF = 1e9;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    // 입력 받기 (무시해도 됨)
    int n, k; // 마을 개수, 배달 가능 시간
    int p;    // 주어지는 정보 개수
    cin >> n >> k >> p;

    // graph[마을] = {(이웃 마을, 걸리는 시간), ...} 형태로 저장
    // 마을 1번부터 시작이므로 크기 n + 1
    vector<vector<pair<int, int>>> graph(n + 1);

    // 그래프 저장
    for (int i = 0; i < p; i++)
    {
        int where1, where2, time;
        cin >> where1 >> where2 >> time;

        graph[where1].push_back({where2, time});
        graph[where2].push_back({where1, time});
    }

    // dist[마을] = 1번 마을에서의 최단 시간 (초기값 INF: 아직 가는 길 못 찾음)
    vector<int> dist(n + 1, INF);

    // (시간, 마을), 최소 힙
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    dist[1] = 0; // 1번 마을에서 출발 (이동 시간 0)
    pq.push({0, 1});

    while (!pq.empty())
    {
        // 시간이 가장 짧은 거 꺼냄
        int d = pq.top().first;    // 시간
        int cur = pq.top().second; // 마을
        pq.pop();

        // dist[cur]이 d보다 이미 작으면 버림
        // 큐에 넣은 뒤 더 짧은 길이 발견된 낡은 정보이므로 버림
        if (d > dist[cur])
            continue;

        // cur 마을의 이웃을 하나씩 봄
        for (int i = 0; i < graph[cur].size(); i++)
        {
            // 이웃 마을
            int next = graph[cur][i].first;
            // cur 마을까지 온 시간 + cur에서 이웃 마을까지 가는 시간
            int nd = d + graph[cur][i].second;

            if (nd < dist[next]) // 더 짧은 시간을 찾은 경우
            {
                dist[next] = nd;
                pq.push({nd, next});
            }
        }
    }

    // k 이하로 배달 가능한 마을 수
    int cnt = 0;
    for (int i = 1; i <= n; i++)
        if (dist[i] <= k)
            cnt++;

    cout << cnt << '\n';

    return 0;
}