class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long max = 0;
        
        for(int i = 0; i < times.length; i++){
            if(max < times[i]){
                max = times[i];
            }
        }
        
        long start = 0;
        long end = max * n + 1;
        
        while(start < end){
            long sum = 0;
            long mid = (start + end) / 2;
            
            for(long i: times){
                sum += mid / i;
            }
            if(sum < n){
                start = mid + 1;
            }
            else{
                end = mid;
            }
        }
        return end;
    }
}