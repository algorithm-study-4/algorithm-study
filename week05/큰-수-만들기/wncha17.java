class Solution {
    public String solution(String number, int k) {
        char[] stack = new char[number.length()];
        int top = -1; // 스택 포인터

        for (int i = 0; i < number.length(); i++) {
            char digit = number.charAt(i);

            // 스택에 쌓인 숫자보다 현재 숫자가 더 크고, 아직 제거할 여유(k)가 남았다면
            // 스택의 작은 숫자를 계속 제거
            while (top >= 0 && k > 0 && stack[top] < digit) {
                top--;
                k--;
            }

            stack[++top] = digit; // 현재 숫자를 스택에 추가
        }

        // 순회가 끝났는데도 k가 남았다면 뒤쪽에서부터 남은 k개 제거
        top -= k;

        return new String(stack, 0, top + 1);
    }
}
