def solution(s, n):
    answer = ''
    for i in s:
        if i.islower():
            answer += chr((ord(i) - 97 + n) % 26 + 97)
        elif i.isupper():
            answer += chr((ord(i) - 65 + n) % 26 + 65)
        else:
            answer += i
    return answer