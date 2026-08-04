n = int(input())
arr = list(map(int, input().split()))
k = int(input())
toSearch = list(map(int, input().split()))

answer = ''

arr = sorted(arr) # 주어진 배열 오름차순 정렬 -40 -30 -20 -10 50
for i in range(k):
    
    now = 0
    low = 0
    high = n-1
    
    while low <= high: # low 인덱스가 high 인덱스보다 작은 상태일 경우에만 반복
        # 등호가 들어가야 하는 이유는 '후보가 하나 남은 상태'까지 검사해야 하기 때문에
        
        now = (low + high) // 2 # 현재 인덱스를 arr의 중간 인덱스로
        
        if toSearch[i] > arr[now]:
            low = now + 1 
            
        elif toSearch[i] < arr[now]:
            high = now - 1
            
        elif toSearch[i] == arr[now]:
            answer += 'O'
            break
        
    if (low > high):
        answer += 'X'
            
print(answer)