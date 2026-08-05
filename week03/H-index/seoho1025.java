//문제가 요구하는 것 : h번 이상 인용된 논문이 h번이상 - 이런 h 중 최댓값
//예외 사항 : sort 금지
//코드로 구현해야 할 것 : h를 하나 정하고 그게 성립하는지 및 성립하는 값 중에서 제일 최댓값인지

class Solution {
    public int solution(int[] citations) {

        int n = citations.length; // 논문개수 = "h 축의 끝점"
        int[] list = new int[citations.length + 1]; // h회 인용된 논문을 확인하기 위해 새배열 선언

        // 새로운 배열에 값을 배치하기
        for(int c : citations){ // citations에서 수를 하나씩 꺼내오기 // c = 인용 횟수
            if(c >= n){ // 배열의 크기가 넘어가지 않도록(논문 인용 횟수 제한)
                list[n]++; // 마지막 칸 수를 1증가
            }else{
                list[c]++; // c번 칸 수를 1증가
            }
        }

        // 누적 편수를 세는 코드
        int sum = 0;
        for(int i = n; i >= 0; i--){ // 마지막부터 세면서 내려옴 : 이유 = "i번 이상"을 세야 하니까 큰 칸부터 더해야 함 + 큰 h부터 봐야 처음 걸린 게 최댓값
            sum += list[i]; // sum = i번 이상 인용된 논문 편수
            if(sum >= i) return i;
        }
        return 0;
    }
}

//sort를 사용했을 경우 풀이 
//class Solution {
//    public int solution(int[] citations) {
//        Arrays.sort(citations);
//
//        int n = citations.length;
//        int min_idx = 0;
//        int max_idx = n;
//
//        while (min_idx < max_idx) {
//            int mid = (min_idx + max_idx) / 2;
//            if (citations[mid] >= n - mid) {
//                max_idx = mid;
//            } else {
//                min_idx = mid + 1;
//            }
//        }
//        return n - min_idx;
//    }
//}

