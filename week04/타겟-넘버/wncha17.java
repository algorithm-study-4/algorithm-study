class Solution {
    int answer = 0;

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }

    // index: 현재 처리할 numbers의 인덱스, sum: 지금까지 계산한 합
    private void dfs(int[] numbers, int target, int index, int sum) {
        // 모든 숫자를 다 사용했다면, 합이 target과 같은지 확인
        if (index == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }

        // 현재 숫자를 더하는 경우
        dfs(numbers, target, index + 1, sum + numbers[index]);

        // 현재 숫자를 빼는 경우
        dfs(numbers, target, index + 1, sum - numbers[index]);
    }
}
