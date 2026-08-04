import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Integer[] sorted = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        Arrays.sort(sorted, (a, b) -> b - a); // 내림차순 : 인용횟수가 큰 논문부터 확인
        
        int n = sorted.length;
        
        for (int i = 0; i < n; i++) {
            // i번째까지 논문 수 = i+1 
            // 정렬된 배열이므로 sorted[i]는 "i+1번째로 많이 인용된 논문의 인용 수"
            // 조건: "인용 횟수 >= (지금까지 확인한 논문 수)"가 성립하는 동안은
            //       그 논문 수만큼은 h번 이상 인용된 논문이 h편 이상 있다는 뜻
            if (sorted[i] >= i + 1) {
                answer = i + 1; // h의 후보를 계속 갱신
            } else {
                break; // 조건이 깨지면 더 큰 h는 불가능하므로 종료
            }
        }
        
        return answer;
    }
}