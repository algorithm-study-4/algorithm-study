import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
        // Stack: 최종적으로 남길 숫자들을 순서대로 쌓아두는 자료구조
        Stack<Character> stack = new Stack<>();
        
        for (char digit : number.toCharArray()) {
            // 1. 아직 지울 수 있는 횟수(k)가 남아있고
            // 2. 스택 맨 위(직전에 쌓인 숫자)보다 지금 숫자가 더 크다면
            // -> 스택 맨 위 숫자를 지우는 게 이득 (더 큰 숫자를 앞에 놓기 위해서)
            while (!stack.isEmpty() && k > 0 && stack.peek() < digit) {
                stack.pop(); // 작은 숫자 제거
                k--;         // 제거 횟수 차감
            }
            stack.push(digit); // 현재 숫자는 일단 스택에 쌓음
        }
        
        // k가 남았다면
        // 위에 k를 다 못 썼다는 건 숫자가 오름차순으로만 쭉 이어졌다는 거기때문에
        // 예: "123"에서 k=1이면 지울 대상이 없어서 while문이 한 번도 안 돎
        // 이럴 땐 뒤에서부터 남은 k개를 그냥 제거
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        // 스택에 남은 길이가 (원래 길이 - k)보다 길면, 뒤에서부터 잘라내기
        String result = sb.toString();
        if (k > 0) {
            result = result.substring(0, result.length() - k);
        }
        
        return result;
    }
}