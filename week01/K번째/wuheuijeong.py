def solution(array, commands):
    answer = []
    n = len(commands)
    
    for i in range(n):
        a = commands[i][0]
        b = commands[i][1]
        c = commands[i][2]
        
        newArr = [] # 빈 배열 만들어서 해당하는 숫자들 append 하기
        for j in range(a-1,b):
            newArr.append(array[j])
        
        newArr.sort() # newArr 에 새로 할당하면 X
        answer.append(newArr[c-1])
        
    return answer