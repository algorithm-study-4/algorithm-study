class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int i = 0, j = 0;  // i,j : cards1, cards2에서 현재 몇번째 카드까지 사용했는지
        
        for (String word : goal) {
            if (i < cards1.length && cards1[i].equals(word)) {
                i++;  // cards1에서 다음카드가 남아있고 & 지금 필요한 word랑 같으면 => i를 하나 넘김
            } else if (j < cards2.length && cards2[j].equals(word)) {
                j++;  // cards1에서 못 가져왔으면, cards2에서 다음카드가 남아있고 & 필요한 word랑 같으면 => j를 하나 넘김
            } else {
                return "No"; // cards1,2 찾았을때 어디에도 단어 꺼낼 수 없을 시 => NO
            }
        }
        
        return "Yes";
    }
}