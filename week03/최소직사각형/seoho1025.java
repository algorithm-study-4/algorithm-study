//문제가 요구하는 것 : 모든 명함을 수납 가능한 지갑 중 가장 작은 넓이(가로 x 세로)
//핵심 : 명함은 눕힐 수 있음 → 가로/세로 구분이 없어짐 → 각 명함마다 큰 값/작은 값으로 통일
//코드로 구현해야 할 것 : 명함마다 (긴 변, 짧은 변)으로 정리 → 긴 변들 중 최댓값 x 짧은 변들 중 최댓값

class Solution {
    public int solution(int[][] sizes) {

        int max = 0; // 긴 변들 중 최댓값 (지갑의 한 변)
        int min = 0; // 짧은 변들 중 최댓값 ← 이름은 min이지만 구하는 건 최댓값

        // 1. 명함을 하나씩 꺼내기
        for(int i = 0; i < sizes.length; i++){

            //    sizes[i][0]=가로, sizes[i][1]=세로
            int current_max = Math.max(sizes[i][0], sizes[i][1]); // 이번 명함의 가장 긴 길이
            int current_min = Math.min(sizes[i][0], sizes[i][1]); // 이번 명함의 짧은 길이

            //    한 장이라도 삐져나오면 안 되므로 제일 큼 명함에 맞춰야 하기때문에 더 크면 이전 기록보다 더 크면 교체
            if(max < current_max) max = current_max;
            if(min < current_min) min = current_min;
        }
        return max * min;
    }
}