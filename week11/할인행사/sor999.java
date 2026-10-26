import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int ans = 0;
        int n = discount.length;

        Map<String, Integer> wantMap = new HashMap<>();
        Map<String, Integer> cntMap = new HashMap<>();

        for(int i = 0; i < want.length; i++){
            wantMap.put(want[i], number[i]);
        }

        for(int i = 0; i < 10; i++){
            String curr = discount[i];
            cntMap.put(curr, cntMap.getOrDefault(curr, 0) + 1);
        }

        int s = 0;
        int e = 9;

        if(cntMap.equals(wantMap)) {
            ans++;
        }

        while(e < n - 1){
            String left = discount[s];
            cntMap.put(left, cntMap.get(left) - 1);
            if(cntMap.get(left) == 0){
                cntMap.remove(left);
            }
            s++;

            e++;
            String right = discount[e];
            cntMap.put(right, cntMap.getOrDefault(right, 0) + 1);

            if(cntMap.equals(wantMap)){
                ans++;
            }
        }

        return ans;
    }
}