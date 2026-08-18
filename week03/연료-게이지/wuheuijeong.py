n = int(input()) # 테스트 케이스 개수 입력받기

for i in range(n): # 테스트 케이스마다 실행
  arr = list(map(str,input())) # arr 입력받기

  low = 0
  high = len(arr) # #이 끝나는 경계가 인덱스 밖일 수도 있음 (전체가 #인 경우)
  now = 0

  while low < high: # low 가 high보다 작은 상태일 때만 반복함

    now = (low + high) // 2 # now는 중간 인덱스

    if arr[now] == "#": # 중간 지점이 # 이면 오른쪽에 # 더 있음
      low = now + 1 # low를 now + 1로 (오른쪽으로) 이동

    elif arr[now] == "_": # 중간 지점이 _이면 왼쪽으로 가야 함
      high = now # high의 위치를 now로 이동 (now가 경계일 수도 있으므로)

    # low == high 가 되고, #이 끝나고 _가 시작되는 인덱스 = #의 개수임
    # now는 중간값이라 그 경계값인 low를 사용함

  print(f"{int((low)/len(arr)*100)}%")