import java.util.Arrays;

// 정렬 + 투 포인터
class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people); // 오름차순 정렬: 가벼운 사람부터 순서대로
        
        int boats = 0;
        int light = 0;              // 가장 가벼운 사람을 가리키는 포인터
        int heavy = people.length - 1; // 가장 무거운 사람을 가리키는 포인터
        
        // 두 포인터가 서로 교차할 때까지 반복
        while (light <= heavy) {
            // 가장 무거운 사람(heavy) + 가장 가벼운 사람(light)을 같이 태울 수 있는지?
            if (people[light] + people[heavy] <= limit) {
                light++; // 같이 탈 수 있으면 가벼운 사람도 태움 (포인터 전진)
            }
            // 같이 못 타면 무거운 사람 혼자 탐 (light는 그대로 둠, 다음 기회에 다시 시도)
            
            heavy--;  // 무거운 사람은 어떤 경우든 이번 보트에 탑승 완료 (포인터 후퇴)
            boats++;  // 보트 한 척 사용
        }
        
        return boats;
    }
}