class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int i = 0, j = 0;

        // goal의 단어를 앞에서부터 하나씩 확인하면서,
        for (String word : goal) {
            // cards1의 다음 카드와 일치하면 cards1에서 사용 (포인터 i 증가)
            if (i < cards1.length && cards1[i].equals(word)) {
                i++;
            } 
            // cards2의 다음 카드와 일치하면 cards2에서 사용 (포인터 j 증가)
            else if (j < cards2.length && cards2[j].equals(word)) {
                j++;
            }
            // 둘 다 일치하지 않으면 goal을 만들 수 없으므로 "No" 반환
            else {
                return "No";
            }
        }

        // 끝까지 goal의 모든 단어를 순서대로 매칭할 수 있으면 "Yes" 반환
        return "Yes";
    }
}