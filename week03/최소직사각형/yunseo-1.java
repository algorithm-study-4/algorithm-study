class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        int maxW =0; // 긴변 최댓값
        int maxH =0; // 짧은변 최댓값
        
        for(int[] size : sizes) {
            // 명함 가로,세로 중에 큰값이 w, 작은값이 h
            int w = Math.max(size[0],size[1]);
            int h = Math.min(size[0],size[1]);
            
            // 긴변, 짧은변 최댓값 갱신
            maxW = Math.max(maxW, w);
            maxH = Math.max(maxH, h);
        }
        answer= maxW*maxH; // 모든 명함을 수납 가능한 최소지갑크기 = 최대로긴변 x 최대로짧은변
        return answer;
    }
}