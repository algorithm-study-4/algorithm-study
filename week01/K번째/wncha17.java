import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int c = 0; c < commands.length; c++) {
            int i = commands[c][0];
            int j = commands[c][1];
            int k = commands[c][2];

            // 1. array의 i번째부터 j번째까지 자르기
            int[] sliced = Arrays.copyOfRange(array, i - 1, j);

            // 2. 자른 배열을 오름차순으로 정렬하기
            Arrays.sort(sliced);

            // 3. 정렬된 배열에서 k번째 숫자를 정답 배열에 담기
            answer[c] = sliced[k - 1];
        }

        return answer;
    }
}