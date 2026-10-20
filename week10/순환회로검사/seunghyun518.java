import java.util.*;
import java.io.*;

public class Main{
    static int[] parent;

    // 순환회로의 존재 유무
    static boolean hasCycle = false;

    // 팀장 찾기
    static int find(int node){
        if(parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }

    // 팀 합치기
    // 순환 회로가 있는지 검사하고 있다면 hasCycle(전역변수)를 true로 변경
    static void union(int node1, int node2){
        node1 = find(node1);
        node2 = find(node2);
        if(node1 != node2){
            parent[node2] = node1;
        }
        else{
            hasCycle = true;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 팀 초기화
        parent = new int[n + 1];
        for(int i = 0; i < n + 1; i++){
            parent[i] = i;
        }

        // 2차원 배열의 대각선 오른쪽 위만 검사
        // 전부 검사하면 동일한 회로를 두 번 검사하므로 무조건 순환회로가 있다고 판별
        for(int i = 0; i < n - 1; i++){
            String[] input = br.readLine().split(" ");
            for(int j = i + 1; j < n; j++){
                if(input[j].equals("1")){
                    union(i + 1, j + 1);
                }
            }
        }

        // 정답 출력
        if(hasCycle){
            System.out.println("WARNING");
        }
        else{
            System.out.println("STABLE");
        }
    }
}