import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Set<Integer> lostSet = new HashSet<>();
        for (int l : lost) lostSet.add(l);

        Set<Integer> reserveSet = new HashSet<>();
        for (int r : reserve) reserveSet.add(r);
        
        // 여벌이 있으면서 동시에 도난당한 학생은 두 목록에서 모두 제외
        Set<Integer> bothSet = new HashSet<>();
        for (int r : reserve) {
            if (lostSet.contains(r)) {
                bothSet.add(r);
            }
        }
        lostSet.removeAll(bothSet);
        reserveSet.removeAll(bothSet);

        // "도난만 당한" 학생들은 오름차순으로 확인
        Integer[] lostArr = lostSet.toArray(new Integer[0]);
        java.util.Arrays.sort(lostArr);

        for (int student : lostArr) {
            // 바로 앞번호 학생이 여벌이 있다면 빌려줌
            if (reserveSet.contains(student - 1)) {
                reserveSet.remove(student - 1);
            }
            // 앞번호가 안 되면 바로 뒷번호 학생 확인
            else if (reserveSet.contains(student + 1)) {
                reserveSet.remove(student + 1);
            }
            // 둘 다 안 되면 이 학생은 체육복을 못 빌림
            else {
                continue;
            }
            lostSet.remove(student);
        }

        return n - lostSet.size();
    }
}
