# 처음 코드 -> N = 30 으로 실행 시 시간 초과 위험 있음
# 이유: 같은 remain을 저장하지 않고 매번 다시 실행하고 있기 때문에

# def dfs(remain):
#     if remain == 0:
#         return 1
#     if remain < 0:
#         return 0
#     return dfs(remain - 1) + dfs(remain - 2) + dfs(remain - 3)
#
#
# N = int(input("N: "))
# print(dfs(N))


# 메모이제이션 사용해서 재작성 -> 같은 remain 값이 나올 경우 다시 계산 X 저장해둔 값 꺼내서 사용

memo = {} # remain값을 저장할 memo 생성하기

def dfs(remain): # remain 값을 매개변수로 받는 dfs 함수 생성
    if remain == 0: # remain 값이 0일 경우 -> 알맞게 숫자를 만든 경우
        return 1 # 1 추가
    if remain < 0: # remain 값이 0보다 작아진 경우 -> 알맞게 숫자를 만들지 못한 경우
        return 0 # 0 처리
    if remain in memo: # 만약 memo에 전에 계산한 remain 값이 있다면 (계산 아끼기)
        return memo[remain] # 저장된 값을 불러온다
    memo[remain] = dfs(remain - 1) + dfs(remain - 2) + dfs(remain - 3) # 기록이 없을 경우 memo의 remain index 값에 1, 2, 3으로 만드는 경우 계산, 더해서 넣기
    return memo[remain] # 윗줄에서 저장 후 값을 리턴

N = int(input()) # N 입력 받기
print(dfs(N))# 입력받은 N에 대한 계산값 반환하기