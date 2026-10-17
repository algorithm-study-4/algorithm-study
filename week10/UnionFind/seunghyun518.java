import java.util.*;
import java.io.*;

public class Main{
    // 자신의 부모(팀의 팀장)을 나타내는 배열
    static int[] parent;

    // 팀장을 찾는 함수
    static int find(int node){
        // 내 상사가 나 자신이라면(자기가 팀장) 자신을 리턴
        if(parent[node] == node) return node;
        // 자기 자신이 팀장이 아니라면, 내 상사를 찾아감 -> 상사도 팀장이 아니면 상사의 상사를 찾아감
        return parent[node] = find(parent[node]);
    }

    // 팀을 합치는 함수(두 번재 노드의 팀장을 첫 번째 노드의 팀장 밑으로 편성)
    static void union(int node1, int node2){
        node1 = find(node1);
        node2 = find(node2);
        // 첫 번째 노드와 두 번째 노드의 팀장이 다르다면
        if(node1 != node2){
            // 두 번째 노드의 팀장을 첫 번째 노드로 설정
            parent[node2] = node1;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        // 팀 초기화
        parent = new int[q + 1];
        for(int i = 0; i < q + 1; i++){
            parent[i] = i;
        }

        // 문제 내용대로 동작
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            if(query == 1){
                union(node1, node2);
            }
            else{
                if(find(node1) == find(node2)){
                    System.out.println("YES");
                }
                else{
                    System.out.println("NO");
                }
            }
        }
    }
}