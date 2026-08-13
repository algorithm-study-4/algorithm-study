import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
        HashMap<Integer, List<Integer>> hm = new HashMap<>();

        int answer = 0;
        int n = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());
        int[] visited = new int[n + 1];
        
        // hashmap에 연결 통로 모두 넣기(이동 가능 검사 가능)
        for(int i = 0; i < t; i++){
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            List<Integer> keyList = hm.get(key);
            if(keyList == null){
                keyList = new ArrayList<>();
                hm.put(key, keyList);
            }
            keyList.add(value);

        // 통로가 양방향이므로 반대로도 넣어줌
            List<Integer> valueList = hm.get(value);
            if (valueList == null) {
                valueList = new ArrayList<>();
                hm.put(value, valueList);
            }
            valueList.add(key);
        }

        // BFS
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = 1;

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int value: hm.get(cur)){
                if(visited[value] == 0){
                    q.offer(value);
                    visited[value] = 1;
                }
            }
        }

        // 감염된 컴퓨터 개수 세기
        for(int i : visited){
            if(i == 1){
                answer += 1;
            }
        }

        System.out.println(answer - 1);
    }
}

////////////////////////////////////////////////////////////////
// DFS
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, List<Integer>> hm = new HashMap<>();

        int answer = 0;
        int n = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());
        int[] visited = new int[n + 1];
        
        // hashmap에 연결 통로 모두 넣기(이동 가능 검사 가능)
        for(int i = 0; i < t; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            
            List<Integer> keyList = hm.get(key);
            if(keyList == null){
                keyList = new ArrayList<>();
                hm.put(key, keyList);
            }
            keyList.add(value);

            // 통로가 양방향이므로 반대로도 넣어줌
            List<Integer> valueList = hm.get(value);
            if (valueList == null) {
                valueList = new ArrayList<>();
                hm.put(value, valueList);
            }
            valueList.add(key);
        }

        // DFS 실행 (1번 컴퓨터부터 시작)
        dfs(1, hm, visited);

        // 감염된 컴퓨터 개수 세기
        for(int i : visited){
            if(i == 1){
                answer += 1;
            }
        }

        if (answer > 0) {
            System.out.println(answer - 1);
        }
    }

    // DFS 로직
    public static void dfs(int cur, HashMap<Integer, List<Integer>> hm, int[] visited) {
        // 현재 노드 방문 처리
        visited[cur] = 1;
        
        // 현재 노드와 연결된 리스트 가져오기
        List<Integer> neighbors = hm.get(cur);
        
        // 연결된 컴퓨터가 있을 경우에만 탐색 진행 (NullPointerException 방지)
        if (neighbors != null) {
            for (int value : neighbors) {
                // 아직 방문하지 않은 컴퓨터라면 재귀 호출
                if (visited[value] == 0) {
                    dfs(value, hm, visited);
                }
            }
        }
    }
}
