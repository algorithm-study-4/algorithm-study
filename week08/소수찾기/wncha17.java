import java.util.HashSet;
import java.util.Set;

class Solution {
    // 1. 만들어진 숫자들을 저장하여 중복을 제거하기 위한 Set 컬렉션 선언
    Set<Integer> numberSet = new HashSet<>();

    public int solution(String numbers) {
        int answer = 0;

        // 문자열 길이만큼 방문(사용) 여부를 체크할 배열 생성
        // 예: numbers가 "17"이면 길이가 2인 boolean 배열 생성
        boolean[] visited = new boolean[numbers.length()];

        // 2. DFS(백트래킹)를 이용해 가능한 모든 숫자 조합을 만들어 Set에 저장
        // 초기 현재 문자열은 빈 문자열("")로 시작
        generateNumbers("", numbers, visited);

        // 3. Set에 저장된 고유한 숫자들을 하나씩 꺼내어 소수인지 판별
        for (int number : numberSet) {
            if (isPrime(number)) {
                answer++; // 소수라면 정답 카운트 증가
            }
        }

        return answer;
    }

    /*
     * 백트래킹을 통해 가능한 모든 숫자의 조합을 만드는 메서드
     * @param current 현재까지 조합된 문자열
     * @param numbers 주어지는 원본 숫자 문자열
     * @param visited 각 자리 숫자의 사용 여부 배열
    */
    private void generateNumbers(String current, String numbers, boolean[] visited) {
        // 현재 만들어진 문자열이 비어있지 않다면, 정수로 변환하여 Set에 추가
        // "011" 같은 문자열은 Integer.parseInt를 거치면 11로 변환됨
        if (!current.equals("")) {
            numberSet.add(Integer.parseInt(current));
        }

        // 원본 문자열의 모든 자리를 탐색하며 숫자 이어 붙이기
        for (int i = 0; i < numbers.length(); i++) {
            // 아직 사용하지 않은 숫자(종이 조각)라면
            if (!visited[i]) {
                visited[i] = true; // 1. 방문(사용) 처리
                
                // 2. 현재 문자열에 해당 숫자를 이어 붙인 후 다음 단계로 재귀 호출
                generateNumbers(current + numbers.charAt(i), numbers, visited);

                visited[i] = false; // 3. 백트래킹: 다른 조합을 찾기 위해 원상 복구 (방문 해제)
            }
        }
    }

    /*
     * 특정 숫자가 소수인지 판별하는 메서드
     * @param num 판별할 정수
     * @return 소수이면 true, 아니면 false
    */
    private boolean isPrime(int num) {
        // 1. 0과 1은 소수가 아니므로 무조건 false 반환
        if (num < 2) {
            return false;
        }

        // 2. 2부터 해당 숫자의 제곱근까지만 나누어 떨어지는지 확인
        // 제곱근까지만 검사하는 이유는 약수는 대칭적으로 존재하기 때문
        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            // 나누어 떨어지는 수가 하나라도 있다면 소수가 아님
            if (num % i == 0) {
                return false;
            }
        }

        // 위 조건을 모두 통과했다면 소수
        return true;
    }
}