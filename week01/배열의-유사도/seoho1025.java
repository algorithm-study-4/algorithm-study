//1. 프로그래머스ver
class Solution {
    public int solution(String[] s1, String[] s2) {
        int answer = 0;
        
        for(int i = 0; i < s1.length; i++){
            for(int j = 0; j < s2.length; j++){
                if(s1[i].equals(s2[j])){
                    answer++;
                }
            }
        }
        
        return answer;
    }
}

//2.이클립스ver
// import java.io.BufferedReader;
// import java.io.InputStreamReader;

// public class arraySimilarity {
//     public static void main(String[] args)throws Exception{
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        
//         String[] arr1 = br.readLine().split(" "); // String 배열이기 떄문데 split 사용 
//         String[] arr2 = br.readLine().split(" ");
        
//         int count = 0; // 반복문 안에 변수를 선언하면 계속 값이 초기화되기 때문에 반복문 밖에 선언 

//         for(int i = 0; i < arr1.length; i++){ // 각자 배열을 반복하면서 동일값 찾게 
//             for(int j = 0; j < arr2.length; j++){
                
//                 if(arr1[i].equals(arr2[j])){ //string이 기 때문에 equals로 비교 
//                     count++;
//                 }
//             }
//         }
//         System.out.print(count);
//     }
    
// }
