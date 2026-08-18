class Solution {
    public int solution(int[] numbers, int k) {
        // 1. 공을 던지는 횟수는 총 k - 1번
        // 2. 한 번 던질 때마다 2칸씩 이동하므로 총 이동 거리는 (k - 1) * 2
        // 3. 배열의 길이를 벗어나면 처음으로 순환하도록 나머지 연산(%) 수행
        int targetIndex = ((k - 1) * 2) % numbers.length;
        
        // 4. 계산된 인덱스 위치에 있는 친구의 번호를 반환
        return numbers[targetIndex];
    }
}