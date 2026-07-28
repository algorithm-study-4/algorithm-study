class Solution {
    boolean solution(String s) {
        int balance = 0; // 여는괄호 - 닫는괄호 
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;  // 여는괄호면 => balance증가
            } else {
                balance--;  // 닫힘괄호면 => 감소
            }
            
            if (balance < 0) {
                return false; // 닫는 괄호가 여는 괄호보다 많아진 경우
            }
        }
        
        return balance == 0; // 최종적으로 열고 닫은 개수가 같아야 함
    }
}