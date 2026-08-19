import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);    // 오름차순 정렬

        int left = 0;   // 가장 가벼운 사람을 가리키는 포인터
        int right = people.length - 1;  // 가장 무거운 사람을 가리키는 포인터
        int count = 0;  // 사용한 구명보트 개수

        while (left <= right) {
            // 가장 무거운 사람과 가장 가벼운 사람을 함께 태울 수 있는지 확인
            if (people[left] + people[right] <= limit) {
                left++;
            }
            // 무거운 사람은 항상 태워야 하므로 right는 무조건 이동
            right--;
            count++;
        }

        return count;
    }
}
