#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    priority_queue<int> pq; // 기본 max heap

    int n; // 명령어 개수
    cin >> n;

    for (int i = 0; i < n; i++)
    {
        string cmd;
        int num;
        cin >> cmd >> num;

        if (cmd == "push")
            pq.push(num);
        else if (cmd == "pop")
        {
            for (int j = 0; j < num; j++)
            {
                cout << pq.top() << " ";
                pq.pop();
            }
            cout << endl;
        }
        else if (cmd == "add")
        {
            int top = pq.top();
            pq.pop();
            pq.push(num + top);
        }
        else
        {
            cout << "Wrong command." << endl;
        }
    }

    return 0;
}