# 재귀함수 사용
# idx, total 을 매개변수로 사용해서 index 가 n(마지막 숫자)가 되면
# total == target 인지 확인하고 1 or 0 을 리턴한다

def solution(numbers, target):
    
    n = len(numbers)
    
    def dfs(idx, total):
        if idx == n:
            if total == target:
                return 1 # total == target 일 경우 count 에 하나씩 더해지는 것
            else:
                return 0
        
        count = 0 # 더하고 빼는 2가지 경우의 수가 있으므로 가지로 뻗어나가게
        count += dfs(idx + 1, total + numbers[idx])
        count += dfs(idx + 1, total - numbers[idx])
        return count
    
    
    answer = dfs(0,0)
    
    return answer