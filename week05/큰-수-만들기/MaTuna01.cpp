#include <string>
#include <vector>
#include <iostream>
using namespace std;

string solution(string number, int k) {
    string answer = "";
    // 정답의 길이는 정해져 있으므로 길이 미리 정의
    answer = number.substr(k); 
    // 앞쪽에 남아있는 k개의 숫자들을 뒤에서부터 역순(k-1 -> 0)으로 탐색
    for(int i = k-1;i >=0;i--){
        int j = 0;
        // 현재 숫자(number[i])를 answer의 가장 큰 자릿수(j = 0)부터 비교
        do{
            // // 앞선 자릿수의 숫자가 answer[j]보다 크거나 같다면 교체(swap)하여 더 큰 숫자를 앞자리에 배치
            if(number[i] >= answer[j]){
                char temp = answer[j];
                answer[j] = number[i];
                number[i] = temp; // 밀려난 작은 숫자는 다음 자릿수(j+1)와 다시 비교하기 위해 보관
                j++;
            }else{
                // 현재 비교 대상보다 작다면 더 이상 뒤로 밀어 넣지 않고 중단
                break;
            }
        }while(1);
    }
    return answer;
}