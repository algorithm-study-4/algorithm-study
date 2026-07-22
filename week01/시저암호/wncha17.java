class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();

        // 문자열을 문자 배열로 변환하여 하나씩 검사
        for (char ch : s.toCharArray()) {
            // 1. 공백은 그대로 추가
            if (ch == ' ') {
                sb.append(ch);
            }
            // 2. 대문자인 경우
            else if (Character.isUpperCase(ch)) {
                char shifted = (char) ((ch - 'A' + n) % 26 + 'A');
                sb.append(shifted);
            }
            // 3. 소문자인 경우
            else if (Character.isLowerCase(ch)) {
                char shifted = (char) ((ch - 'a' + n) % 26 + 'a');
                sb.append(shifted);
            }
        }

        return sb.toString();
    }
}