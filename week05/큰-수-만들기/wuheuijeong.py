def solution(number, k):
    list_number = [int(c) for c in number]
    target_len = len(number) - k
    
    answer = ''
    
    while len(answer) < target_len:
        fix_num = -1
        fix_idx = 0
        
        for i in range(len(list_number)):
            if i <= k and list_number[i] > fix_num:
                fix_num = list_number[i]
                fix_idx = i
        
        answer += str(fix_num)
        
        del list_number[0:fix_idx + 1]
        k -= fix_idx
        
    return answer