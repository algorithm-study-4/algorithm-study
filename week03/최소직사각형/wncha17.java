class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;

        for (int[] size : sizes) {
            // 각 명함마다 긴 쪽을 가로, 짧은 쪽을 세로로 맞춤
            int width = Math.max(size[0], size[1]);
            int height = Math.min(size[0], size[1]);

            // 전체 명함 중 가장 큰 가로, 가장 큰 세로를 각각 갱신
            maxWidth = Math.max(maxWidth, width);
            maxHeight = Math.max(maxHeight, height);
        }

        return maxWidth * maxHeight;
    }
}