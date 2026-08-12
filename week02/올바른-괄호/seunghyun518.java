import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true; // 기본값 true
        Deque<Character> stack = new ArrayDeque<>();
        
        // 입력 문자열을 하나씩 char형태로 읽어옴
        for(int i = 0; i< s.length(); i++){ 
            char ch = s.charAt(i);
            if(ch == '('){ // 여는 괄호가 들어왔을 땐 stack에 push
                stack.push('(');
            }
            else if(ch == ')' && stack.isEmpty()){ //닫는 괄호가 들어왔을 때, 스택이 비어있다면 false 반환
                return false;
            }
            else{ // 닫는 괄호가 들어왔을 때, 스택이 비어있지 않다면 하나 pop
                stack.pop();
            }
        }

        // 문자열을 모두 돌았을 때, 스택이 비어있지않다면(짝이 맞지않는다면) false
        if(!stack.isEmpty()){ 
            answer = false;
        }
        
        return answer;
    }
}