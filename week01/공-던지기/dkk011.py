def solution(numbers, k):
    arr = []
    count = 0
    i = 0
    while count < k:
        arr.append(numbers[i])
        count += 1
        i += 2
        if i >= len(numbers):
            i %= len(numbers)
    return arr[-1]