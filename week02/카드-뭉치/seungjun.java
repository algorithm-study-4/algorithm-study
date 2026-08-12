public class seungjun {
    public static void main(String[] args) {
        seungjun s = new seungjun();

        String[] cards1 = {"i", "drink", "water"};
        String[] cards2 = {"want", "to"};
        String[] goal = {"i", "want", "to", "drink", "water"};

        System.out.println(s.solution(cards1, cards2, goal));
    }

    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int idx1 = 0;
        int idx2 = 0;

        for (String word : goal) {
            // 카드1에 확인할 카드고 남아있는가, 현재카드가 goal에서 필요한 단어와 같은지
            if (idx1 < cards1.length && cards1[idx1].equals(word)) {
                idx1++;
            }
            // 첫번째 카드1에서 못 찾았다면 두 번째 카드 뭉치 고고
            else if (idx2 < cards2.length && cards2[idx2].equals(word)) {
                idx2++;
            }
            else {
                return "No";
            }
        }
        return "Yes";
    }
}