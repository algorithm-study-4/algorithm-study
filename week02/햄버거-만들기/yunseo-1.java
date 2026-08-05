import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int count = 0; // 완성한 햄버거 수
        int[] stack = new int[ingredient.length + 1]; // 재료를 쌓아둘 스택
        int top = 0; // 스택에 쌓인 재료 수 => stack[0] ~ stack[top-1] 까
        
        for (int i = 0; i < ingredient.length; i++) {
            stack[top++] = ingredient[i]; // 스택 맨 위에 재료 추가 => top 하나 업
            
            if (top >= 4 &&   // 스택에 4개 이상 쌓여있고 & 스택 맨 위 4개가 1-2-3-1 이면
                stack[top-4] == 1 &&
                stack[top-3] == 2 &&
                stack[top-2] == 3 &&
                stack[top-1] == 1) {
                
                top -= 4; // 4개 제거 (햄버거1개)
                count++;  // 햄버거 수 업
            }
        }
        
        return count;  
    }
}