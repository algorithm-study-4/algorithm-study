import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0, window = 10;

        // 장보기 목록 해시맵 선언
        HashMap<String, Integer> hm = new HashMap<>();

        // 장보기 목록 작성
        for(int i = 0; i < number.length; i++){
            hm.put(want[i], number[i]);
        }

        // discount 목록 검사(0부터)
        for(int i = 0; i <= discount.length - window; i++){

            // 할인 목록
            HashMap<String, Integer> sale = new HashMap<>();

            // window 크기에 장보기 목록이 다 들어있는지 검사
            for(int j = i; j < i + window; j++){

                // 할인 목록 작성
                int count = sale.getOrDefault(discount[j], 0);
                sale.put(discount[j], count + 1);
            }

            // 할인 목록 = 장보기 목록이면 회원가입할 수 있는 날이 하루 추가
            if(hm.equals(sale)){
                answer++;
            }
        }

        return answer;
    }
}