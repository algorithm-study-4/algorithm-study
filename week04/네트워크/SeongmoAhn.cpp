#include <string>
#include <vector>
#include <queue>
#include <iostream>
#define SIZE 201

using namespace std;

int row;
int visited[SIZE];

void bfs(vector<vector<int>> &map, int start) {
    queue<int> q;
    q.push(start);
    
    while (!q.empty()) {
        int cur = q.front(); q.pop();
        
    	for (int i = 0; i < row; i++) {
            if (map[cur][i] && !visited[i]) {
                visited[i] = 1;
                q.push(i);
            }
    	}
    }
}

void dfs(vector<vector<int>> &map, int start) {
    for (int i = 0; i < row; i++) {
        if (map[start][i] && !visited[i]) {
            visited[i] = 1;
            dfs(map, i);
        }
    }
}

int solution(int n, vector<vector<int>> computers) {
    row = computers.size();
    
    int answer = 0;
    for (int i = 0; i < row; i++) {
        if (!visited[i]) {
            visited[i] = 1;
       		//bfs(computers, i); 
       		dfs(computers, i); 
            answer++;
        }
    }
    return answer;
}