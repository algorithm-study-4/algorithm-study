#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct Work
{
    int num;  // 작업의 번호
    int time; // 작업 요청 시각
    int due;  // 작업 소요 시간
};

struct cmp
{
    bool operator()(Work a, Work b)
    {
        if (a.due == b.due)
        {
            if (a.time == b.time)
            {
                if (a.num < b.num)
                    return false;
                else
                    return true;
            }
            else
            {
                if (a.time < b.time)
                    return false;
                else
                    return true;
            }
        }
        else
        {
            if (a.due < b.due)
                return false;
            else
                return true;
        }
    }
};

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; // 작업의 개수
    cin >> n;

    vector<Work> jobs(n);
    priority_queue<Work, vector<Work>, cmp> pq;

    for (int i = 0; i < n; i++)
    {
        jobs[i].num = i;
        cin >> jobs[i].time >> jobs[i].due;
    }

    sort(jobs.begin(), jobs.end(), [](const Work &a, const Work &b)
         { return a.time < b.time; });

    int curr_time = 0;
    int idx = 0;
    int done = 0;
    long long return_time = 0;

    while (done < n)
    {
        while (idx < n && jobs[idx].time <= curr_time)
        {
            pq.push(jobs[idx]);
            idx++;
        }

        if (pq.empty())
        {
            curr_time = jobs[idx].time;
            continue;
        }

        Work cur = pq.top();
        pq.pop();

        curr_time += cur.due;
        return_time += curr_time - cur.time;
        done++;
    }

    cout << return_time / n << endl;

    return 0;
}