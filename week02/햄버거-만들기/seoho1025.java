class Solution {
    public int solution(int[] ingredient) {
        int count = 0;
        int[] stack = new int[ingredient.length];
        int pointer = 0;
        
        for(int i = 0; i < ingredient.length; i++){
             stack[pointer] = ingredient[i];
                pointer++;
            if(pointer >= 4
               && stack[pointer - 4] == 1
               && stack[pointer - 3] == 2
               && stack[pointer - 2] == 3
               && stack[pointer - 1] == 1){
                count++;
                pointer -= 4;
            } 
        }
            return count;        
    }
}