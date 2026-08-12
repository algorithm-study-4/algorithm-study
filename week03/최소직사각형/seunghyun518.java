class Solution {
    public int solution(int[][] sizes) {
        int maxx = 0;
        int maxy = 0;
        
        for(int[] num: sizes){
            maxx = Math.max(maxx, Math.max(num[0], num[1]));
            maxy = Math.max(maxy, Math.min(num[0], num[1]));
        }
        
        return maxx * maxy;
    }
}