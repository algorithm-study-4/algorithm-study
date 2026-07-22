"""
O(n*mlogm), n = len(commands), m = len(array)
"""
def solution(array, commands):
    answer = []
    for command in commands:
        i, j, k = command
        
        nums = array[i - 1:j]
        nums.sort()
        answer.append(nums[k - 1])

    return answer