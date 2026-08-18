import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        List<Integer> lostList = Arrays.stream(lost).boxed().collect(Collectors.toList());
        List<Integer> reserveList = Arrays.stream(reserve).boxed().collect(Collectors.toList());
        
        for(int i: new ArrayList<>(lostList)){
            if(reserveList.contains(i)){
                reserveList.remove(Integer.valueOf(i));
                lostList.remove(Integer.valueOf(i));
            }
        }
        
        lostList.sort(null);
        reserveList.sort(null);
        
        for(int i: new ArrayList<>(lostList)){
            if(reserveList.contains(i-1)){
                reserveList.remove(Integer.valueOf(i-1));
                lostList.remove(Integer.valueOf(i));
            }
            else if(reserveList.contains(i+1)){
                reserveList.remove(Integer.valueOf(i+1));
                lostList.remove(Integer.valueOf(i));
            }
        }
        
        return n - lostList.size();
    }
}