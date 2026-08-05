class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        int[] stack = new int[ingredient.length];
        int top = -1; // 스택 포인터 (마지막 원소의 인덱스를 가리킴)

        for (int i = 0; i < ingredient.length; i++) {
            // 재료가 들어올 때마다 스택에 하나씩 쌓기
            stack[++top] = ingredient[i];

            // 스택의 맨 위 4개가 아래부터 (빵-야채-고기-빵)과 일치하는지 확인
            if (top >= 3
                && stack[top] == 1
                && stack[top-1] == 3
                && stack[top-2] == 2
                && stack[top-3] == 1) {
                    top -= 4; // 4개 재료 제거 (버거 완성)
                    answer++;
                }
        }

        return answer;
    }
}