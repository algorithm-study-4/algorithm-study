n, q = map(int, input().split()) # 광물의 개수 n, 연구원의 수 q
arr = list(map(int, input().split())) #  광물에 대한 상대 강도

arr = sorted(arr) # 광물의 강도가 작은 순서대로 오름차순 나열하기

for i in range(q): # 연구원의 수만큼 반복
    si, ei = map(int, input().split()) # 필요한 상대 강도 si, ei 입력받기

    # 필요한 강도의 범위에 있는 광물의 개수를 출력해야 함

    # si 이상인 index 먼저 찾기
    low = 0 # 여기까지는 정답일 수도 아닐 수도 있어
    high = len(arr) # 여기서부터는 target 이상 구역 (확정)   

    while low < high: # 경계를 찾을 때까지 좁혀나감 (low == high가 되면 종료)

        mid =  (low + high) // 2 # 현재 확인할 중간 인덱스

        if arr[mid] < si: # mid가 si보다 작으면 → 아직 "si 미만 구역"
            low = mid + 1 # 경계는 mid보다 오른쪽에 있음
        else:
            high = mid # 경계를 mid 이하로 좁힘

    start = low

    # ei 이하인 index 찾기
    # 변수 리셋

    low, high = 0, len(arr)

    while low < high:
        mid =  (low + high) // 2

        if arr[mid] <= ei: # mid가 ei 이하면 → 아직 "ei 이하 구역"
            low = mid + 1 # 경계는 mid보다 오른쪽에 있음
        elif arr[mid] > si: # mid가 ei보다 크면 → 경계 후보 (si < ei 이므로 이 조건은 항상 참)
            high = mid # 경계를 mid 이하로 좁힘

    finish = low # ei 이하인 원소의 개수 = ei 초과인 원소가 시작되는 인덱스

    print(finish - start) # [si, ei] 범위에 속하는 광물 개수 = (ei 이하 개수) - (si 미만 개수)
