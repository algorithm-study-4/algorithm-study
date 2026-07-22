class Solution {
    public int solution(int[] numbers, int k) {
        int answer = 0;
        
        for(int i = 1; i < k; i++){
          answer = (answer + 2) % numbers.length;
    }
        return numbers[answer];
}
}


// import java.io.BufferedReader;
// import java.io.InputStreamReader;

// 
//         public static void main(String[] args) throws Exception {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         // 
//         String[] temp = br.readLine().split(" ");
//         int[] numbers = new int[temp.length];
//         for (int i = 0; i < temp.length; i++) {
//             numbers[i] = Integer.parseInt(temp[i]);
//         }

//         // 
//         int k = Integer.parseInt(br.readLine());

//         System.out.print(solution(numbers, k));
//     }
// }