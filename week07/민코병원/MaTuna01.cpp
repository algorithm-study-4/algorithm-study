#include <iostream>
#include <vector>
#include <queue>
using namespace std;

// 환자 정보
struct Patient {
    string name;
    int age;        // 나이
    int urgency;    // 응급도
    int id;         // 접수 순번 (0부터 증가)
};

vector<Patient> p;      // 모든 접수 환자 정보 (인덱스 = id)
vector<bool> done;      // done[i] = i번 환자가 이미 진료를 받았는가

// B동(응급병동) 우선순위 비교자
struct cmp {
    bool operator()(int a, int b) const {
        // 1순위: 응급도가 높은 환자 먼저 -> a의 응급도가 더 작으면 a가 뒤로
        if (p[a].urgency != p[b].urgency)
            return p[a].urgency < p[b].urgency;

        // 2순위: 나이가 어린 환자 먼저 -> a의 나이가 더 크면 a가 뒤로
        if (p[a].age != p[b].age)
            return p[a].age > p[b].age;

        // 3순위: 먼저 접수한 환자 먼저 -> a의 접수 순번이 더 크면(늦게 왔으면) a가 뒤로
        return p[a].id > p[b].id;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int q;
    cin >> q;

    // A동: 접수 순서대로 일반적인 큐(FIFO)
    queue<int> normal;
    // B동: cmp 기준 우선순위 큐 cmp 구조체로 우선순위 비교자를 정의
    priority_queue<int, vector<int>, cmp> emg;

    while (q--) {
        // 1: 접수, 2: 진료 호출
        int type;
        cin >> type;

        if (type == 1) {
            // 접수받는 환자 선언
            Patient np;
            // 환자 정보 입력
            cin >> np.name >> np.age >> np.urgency;
            // 환자 순번 = 현재까지 접수된 인원
            np.id = p.size();

            p.push_back(np);
            done.push_back(false); // 접수 완료

            // 환자는 한 명이지만 두 병동 모두에서 호출될 수 있으므로 A동과 B동 모두에 환자 id를 넣어준다
            normal.push(np.id);
            emg.push(np.id);

        } else {
            // 2인경우(진료 호출)
            char ward;
            cin >> ward;
            int idx = -1;

            if (ward == 'A') {
                // 맨 앞이 이미 다른 병동에서 진료받은 환자라면 버린다(두 자료구조가 실시간 동기화 되지 않으므로 검사 후 삭제)
                while (!normal.empty() && done[normal.front()])
                    normal.pop();
                // 맨 앞이 아직 진료받지 않은 환자라면 idx에 저장하고 큐에서 제거
                if (!normal.empty()) {
                    idx = normal.front();
                    normal.pop();
                }
            } else {
                // B동(응급병동)의 경우
                while (!emg.empty() && done[emg.top()])
                    emg.pop();
                // 맨 앞이 아직 진료받지 않은 환자라면 idx에 저장하고 큐에서 제거
                if (!emg.empty()) {
                    idx = emg.top();
                    emg.pop();
                }
            }

            if (idx == -1) {
                cout << "EMPTY\n";
            } else {
                done[idx] = true;      // 진료 완료 표시 -> 반대편 큐(다른 병동)에서 자동으로 걸러짐
                cout << p[idx].name << "\n";
            }
        }
    }
    return 0;
}