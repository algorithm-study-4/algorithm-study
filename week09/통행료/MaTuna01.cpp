#include<iostream>
#include<string>
#include<vector>
using namespace std;

#define INF 1e9

int n, m, k ,start, dest;
vector<pair<int,int>> road[1001]; // <cost,linked_node>
vector<int>year; // 연간 통행료 인상액 저장 벡터
vector<int>dist;

int main() {
    cin >> n >> m >> k >> start >> dest;
    for (int i = 0; i < m; i++) {
        int city1, city2, cost;
        cin >> city1 >> city2>> cost;
        road[city1].push_back({cost, city2});
        road[city2].push_back({cost, city1});
    }
    // 첫해 인상률은 0으로 설정
    year.push_back(0);

    for(int i = 0; i < k; i++){
        cin >> year[i+1];
    }

    dist[start] = 0;

    return 0;
}