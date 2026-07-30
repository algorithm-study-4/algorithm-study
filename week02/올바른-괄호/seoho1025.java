class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        int count = 0;
        
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(' ){
                count++;
            }else{
                count--;
            }
            if(count < 0){
                return false;
            }
        }
        return count == 0;
    }
}

// 2. stack으로 풀기
// import java.util.*;
// class Solution {
//     boolean solution(String s) {
//         Deque<Character> stack = new ArrayDeque<>();
//         boolean answer = true;
        
//         for(int i = 0; i < s.length(); i++){
//             if(s.charAt(i) == '('){
//                 stack.push('(');
//             }else{
//                 if(stack.isEmpty()) return false;
//                 stack.pop();
//             }
//         }
//     return stack.isEmpty();
//     }
// }