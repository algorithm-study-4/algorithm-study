
def solution(s, n):
    answer = ''
    
    alphabet_small = "abcdefghijklmnopqrstuvwxyz"
    alphabet_big = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    
    for char in s:
        if char in alphabet_small:
            idx = (alphabet_small.index(char) + n) % 26
            answer += alphabet_small[idx]
        elif char in alphabet_big:
            idx = (alphabet_big.index(char) + n) % 26
            answer += alphabet_big[idx]
        elif char == " ":
            answer += " "
    
    return answer