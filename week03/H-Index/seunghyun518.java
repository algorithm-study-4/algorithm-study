import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int maxh = 0;
        int max = 0;
        
        for(int i: citations){
            max = Math.max(max, i);
        }
            
        for(int i = 0; i <= max; i++){
            int sum = 0;
            for(int j: citations){
                if(i <= j){
                    sum += 1;
                }
            }
            if(sum >= i){
                maxh = Math.max(maxh, i);
            }
        }
        
        return maxh;
    }
}