#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> people, int limit)
{
    int answer = 0;

    sort(people.begin(), people.end());

    int left = 0;
    int right = people.size() - 1;

    while (left <= right)
    {
        if (people[left] + people[right] <= limit)
            left++;

        right--;
        answer++;
    }

    return answer;
}

// #include <string>
// #include <vector>
// #include <algorithm>

// using namespace std;

// int solution(vector<int> people, int limit) {
//     int answer = 0;
    
//     sort(people.begin(), people.end());
    
//     int left = 0;
//     int right = people.size() - 1;
    
//     while(left <= right)
//     {
//         if(people[left] == -1)
//         {
//             left++;
//             continue;
//         }
        
//         if(people[left] + people[right] > limit)
//         {
//             right--;
//             answer++;
//         }
//         else
//         {
//             answer++;
//             people[left] = -1;
//             people[right] = -1;
//             left++;
//             right--;
//         }
//     }
    
//     return answer;
// }