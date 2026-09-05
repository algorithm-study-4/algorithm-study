#include <iostream>
#include <queue>
#include <unordered_map>

using namespace std;

struct Patient
{
    string name;
    int age;
    int urgency;
    int order;
};

struct cmp
{
    bool operator()(Patient a, Patient b)
    {
        if (a.urgency == b.urgency)
        {
            if (a.age == b.age)
            {
                if (a.order < b.order)
                    return false;
                else
                    return true;
            }
            else
            {
                if (a.age < b.age)
                    return false;
                else
                    return true;
            }
        }
        else
        {
            if (a.urgency < b.urgency)
                return true;
            else
                return false;
        }
    }
};

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int q;
    cin >> q;

    int order = 0;
    queue<string> A;
    priority_queue<Patient, vector<Patient>, cmp> B;
    unordered_map<string, bool> patient;

    for (int i = 0; i < q; i++)
    {
        int num;
        cin >> num;

        if (num == 1) // 환자 접수
        {
            string name;
            int age;
            int urgency_level;
            cin >> name >> age >> urgency_level;

            patient[name] = false;

            A.push(name);
            B.push({name, age, urgency_level, order});
            order++;
        }
        else if (num == 2) // 진료 호출
        {
            char c;
            cin >> c;

            if (c == 'A')
            {
                while (!A.empty() && patient[A.front()])
                    A.pop();

                if (A.empty())
                    cout << "EMPTY" << endl;
                else
                {
                    patient[A.front()] = true;
                    cout << A.front() << endl;
                    A.pop();
                }
            }
            else
            {
                while (!B.empty() && patient[B.top().name])
                    B.pop();

                if (B.empty())
                    cout << "EMPTY" << endl;
                else
                {
                    patient[B.top().name] = true;
                    cout << B.top().name << endl;
                    B.pop();
                }
            }
        }
    }

    return 0;
}