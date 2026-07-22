package week01.K번째;
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int num = 0; num < commands.length; num++) {
            int i = commands[num][0];
            int j = commands[num][1];
            int k = commands[num][2];

            int[] cut = Arrays.copyOfRange(array, i - 1, j);
            Arrays.sort(cut);
            answer[num] = cut[k - 1];
        }
        return answer;
    }
}