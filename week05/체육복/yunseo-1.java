import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // case: 여벌도 있고 도난도 당한 학생 
        // 이런 학생은 자기 체육복으로 자기가 해결하므로 lost와 reserve 양쪽 배열에서 미리 제거해야 함
        
        boolean[] hasReserve = new boolean[n + 1]; // n+1: 1번부터 n번까지 쓰려고
        for (int r : reserve) hasReserve[r] = true;
        
        List<Integer> realLost = new ArrayList<>();
        List<Integer> realReserve = new ArrayList<>();
        
        for (int l : lost) {
            if (hasReserve[l]) {
                hasReserve[l] = false; // 자기 것으로 자기가 해결 -> 더 이상 빌려줄 여벌 X
            } else {
                realLost.add(l); // 진짜로 빌려야 하는 학생만 남긴다
            }
        }
        for (int i = 1; i <= n; i++) {
            if (hasReserve[i]) realReserve.add(i); // 아직 빌려줄 수 있는 여벌만 남긴다
        }
        
        int[] lostArr = realLost.stream().mapToInt(Integer::intValue).toArray();
        int[] reserveArr = realReserve.stream().mapToInt(Integer::intValue).toArray();
        
        int answer = n - lostArr.length; // 애초에 도난 당하지 않은 학생 수
        
        Arrays.sort(lostArr);
        Arrays.sort(reserveArr);
        
        // 인접 번호(-1, +1) 매칭
        for (int i = 0; i < lostArr.length; i++) {
            for (int j = 0; j < reserveArr.length; j++) {
                if (reserveArr[j] == lostArr[i] - 1 || reserveArr[j] == lostArr[i] + 1) {
                    answer++;
                    reserveArr[j] = -1;
                    break;
                }
            }
        }
        
        return answer;
    }
}