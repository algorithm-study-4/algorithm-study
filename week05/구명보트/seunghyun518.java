import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        int left = 0;
        int right = (people.length)-1;
        
        for(; right >= left; right--){
            if(people[right] + people[left] <= limit){
                left++;
                answer++;
            }
            else{
                answer++;
            }
        }
        
        return answer;
    }
}