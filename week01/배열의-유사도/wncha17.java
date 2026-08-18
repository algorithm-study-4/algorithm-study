import java.util.HashSet;

class Solution {
    public int solution(String[] s1, String[] s2) {
        int answer = 0;

        HashSet<String> set = new HashSet<>();

        for (String str : s1) {
            set.add(str);
        }

        for (String str : s2) {
            if (set.contains(str)) {
                answer++;
            }
        }

        return answer;
    }
}