// 문제 설명
// 어떤 문장의 각 알파벳을 일정한 거리만큼 밀어서 다른 알파벳으로 바꾸는 암호화 방식을 시저 암호라고 합니다. 예를 들어 "AB"는 1만큼 밀면 "BC"가 되고, 3만큼 밀면 "DE"가 됩니다. "z"는 1만큼 밀면 "a"가 됩니다. 문자열 s와 거리 n을 입력받아 s를 n만큼 민 암호문을 만드는 함수, solution을 완성해 보세요.

// 제한 조건
// 공백은 아무리 밀어도 공백입니다.
// s는 알파벳 소문자, 대문자, 공백으로만 이루어져 있습니다.
// s의 길이는 8000이하입니다.
// n은 1 이상, 25이하인 자연수입니다.
// 입출력 예
// s	    n	result
// "AB"	1	"BC"
// "z"	    1	"a"
// "a B z"	4	"e F d"

class Solution {
    public String solution(String s, int n) {
        StringBuilder answer = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ' ') {    // 공백 유지
                answer.append(c);
                continue;
            }

            char base = Character.isUpperCase(c) ? 'A' : 'a'; // 대문자면 'A', 소문자면 'a'가 기준
            char shifted = (char) (base + (c - base + n) % 26);
            // c - base : 기준 A/a 부터 몇 번째 알파벳인지 계산 (0~25)
            // + n     : n만큼 밀기
            // % 26    : 26을 넘으면 다시 처음으로
            // + base  : 다시 원래 알파벳 범위(A~Z/a~z) 로 변환
            answer.append(shifted);
        }

        return answer.toString();
    }
}