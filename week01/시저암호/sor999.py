"""
chr: 숫자 -> 문자 / ord: 문자 -> 숫자
O(len(s))
"""
def solution(s, n):
    strs = []
    
    for curr in s:
        if curr == ' ':
            strs.append(curr)
        elif 'a' <= curr <= 'z':
            strs.append(chr((ord(curr) - ord('a') + n) % 26 + ord('a')))
        else:
            strs.append(chr((ord(curr) - ord('A') + n) % 26 + ord('A')))

    return "".join(strs)