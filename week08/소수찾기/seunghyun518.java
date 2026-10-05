import java.util.*;

class Solution {

    // 전역 변수 선언(solution, dfs 함수에서 공용으로 사용하기 위해)
    static Set<Integer> s;
    static String[] nums;
    static boolean[] visited;


    public int solution(String numbers) {

        // 중복을 거르기 위한 Set 만들기
        s = new HashSet<>();
        nums = numbers.split("");
        visited = new boolean[nums.length];

        // 재귀함수 시작
        dfs("");

        // 중복 없는 소수의 개수 제출
        return s.size();
    }

    // 재귀 함수
    static void dfs(String str){

        // 정답 조건(isPrime이면 Set에 추가)
        if(!str.isEmpty() && isPrime(Integer.parseInt(str))){
            s.add(Integer.parseInt(str));
        }

        // 배열 순회
        for(int i = 0; i < nums.length; i++){
            // 방문 했었다면 건너뛰기
            if(visited[i]){
                continue;
            }

            // 방문하기 전 방문 표시
            visited[i] = true;
            dfs(str + nums[i]);
            // 다른 순서에서 이 조각을 다시 쓸 수 있도록 표시 지움
            visited[i] = false;
        }

    }

    // 소수 판별 함수
    static boolean isPrime(int num){

        if(num < 2){
            return false;
        }

        for(int i = 2; i * i<= num; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}