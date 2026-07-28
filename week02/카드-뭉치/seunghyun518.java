class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int idx1 = 0;
        int idx2 = 0;
        
        for(String word: goal){ // goal 단어 배열 순회
            if(idx1 < cards1.length && word.equals(cards1[idx1])){
                idx1++; // cards1의 맨 앞에 있다면, cards1의 인덱스 +1
            }
            else if(idx2 < cards2.length && word.equals(cards2[idx2])){
                idx2++; // cards2의 맨 앞에 있다면, cards2의 인덱스 +1
            }
            else{
                return "No"; // 둘 다에 없다면 No반환
            }
        }
        
        return "Yes"; // 모든 goal단어 배열을 문제 없이 순회했다면 Yes 반환
    }
}