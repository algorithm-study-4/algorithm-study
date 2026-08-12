def solution(sizes):
    answer = 0
    
    # 가로 길이 >= 세로 길이 로 각각의 배열 재정렬하기
    for i in sizes:
        if i[0] < i[1]:
            a = i[0]
            i[0] = i[1]
            i[1] = a
    
    # row, col (가장 긴 길이) 를 우선 첫 번째 세트로 지정
    row = sizes[0][0]
    col = sizes[0][1]
    
    # 나머지 배열들을 돌아가면서 첫 번째 배열보다 긴 게 나오면
    # row, col을 그 길이로 변경하도록 함
    for j in range(len(sizes)):
        if row < sizes[j][0]:
            row = sizes[j][0]
        if col < sizes[j][1]:
            col = sizes[j][1]
    
    # 이제 row, col 은 각각 제일 긴 길이가 되었으므로
    # anwer에 넓이 계산해서 반환
    answer = row * col
        
    return answer