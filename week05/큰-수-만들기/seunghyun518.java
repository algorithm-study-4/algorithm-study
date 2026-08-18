import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> st = new Stack<>();
        
        for(int i = 0; i < number.length(); i++){
            while(!st.isEmpty() && st.peek() < number.charAt(i) && k > 0){
                st.pop();
                k--;
            }
            st.push(number.charAt(i));
        }
        
        while(k > 0){
            st.pop();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : st) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}

//////////////////////////////////////////////////////////////////////////

class Solution {
    public String solution(String number, int k) {
        // substring의 성능 저하를 막기 위해 StringBuilder 사용
        StringBuilder sb = new StringBuilder(number);
        
        int j = 0; 
        
        // k번 반복하는 대신, 문자열을 한 번만 순회하면서 해결합니다.
        while (k > 0 && j < sb.length() - 1) {
            
            // 작성하신 로직과 동일: 앞 숫자가 뒤 숫자보다 작으면
            if (sb.charAt(j) < sb.charAt(j + 1)) {
                sb.deleteCharAt(j); // 해당 숫자 삭제
                k--; // 지워야 할 횟수 감소
                
                // 처음(j=0)으로 돌아가는 대신, 바로 앞 인덱스로 한 칸만 뒤로 감
                if (j > 0) {
                    j--;
                }
            } else {
                // 삭제할 필요가 없으면 다음 인덱스로 이동
                j++;
            }
        }
        
        // 내림차순이라서 지우지 못하고 k가 남았다면, 마지막 숫자들을 잘라냄
        if (k > 0) {
            sb.delete(sb.length() - k, sb.length());
        }
        
        return sb.toString();
    }
}

/*
풀이: 숫자를 차례대로 넣으면서 먼저 들어간 숫자가 뒤에 들어갈 숫자보다 작으면 제거
넣으면서 앞에서부터 제거 -> 다 넣을 때까지 k개가 제거가 안됐다면
맨 뒤에서 k번 제거
1. 맨 처음엔 이중 for문 -> 너무 여러번 반복(시간초과)
2. 리스트 -> 중간 값을 제거할 때마다 리스트를 새로 만듦(시간초과)
3. 링크드리스트 -> 제거할 값을 조회할 때 시간이 너무 오래걸림(시간초과)
4. 스택 -> 가능 or for문 최적화(string bilder)
*/