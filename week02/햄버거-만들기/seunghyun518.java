import java.util.*;

class Solution {
    public int solution(int[] ingredient) {

        int answer = 0;
        Stack<Integer> s = new Stack<>(); // 포장 중인 햄버거(스택) 
        
        for(int i: ingredient){ // 들어오는 재료
            s.push(i); 
            if(s.size() >= 4){ // 햄버거가 만들어지려면 최소 4개의 재료가 필요함
                int size = s.size();
                if(s.get(size - 1) == 1 && // 1-2-3-1의 순서로 재료가 쌓인다면
                  s.get(size - 2) == 3 &&
                  s.get(size - 3) == 2 &&
                  s.get(size - 4) == 1){
                    s.pop(); // 포장 완료(없어짐)
                    s.pop();
                    s.pop();
                    s.pop();
                    answer++; // 하나 완성
                }
                
            }
        }
        return answer;
    }
}