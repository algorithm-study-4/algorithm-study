import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //사람의 수
        int M = Integer.parseInt(st.nextToken()); //친구 관계의 수
        ArrayList<Integer>[] Line = new ArrayList[N + 1];  // 크기가 늘어나는 리스트 배열 (각 리스트 별로 친구들을 넣을 수 있는 배열)


        //친구들의 번호를 받기 위해서 빈 배열을 하나씩 만들기 위한 반복문(예를 들어서 1번의 친구들이 누군지 확인할 수 있음)
        for (int i = 0; i < Line.length; i++) {
            Line[i] = new ArrayList<>();
        }

        for (int j = 0; j < M; j++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            //A와 B가 주어졌을 때, 두 사람이 친구 관계임을 의미하고 있다는 것을 의미(양방향)
            int A = Integer.parseInt(st2.nextToken()); //A 사람번호
            int B = Integer.parseInt(st2.nextToken()); //B 사람번호

            Line[A].add(B); // 만일, A와 B에 사람이 1, 3으로 수가 들어갔을 때, 배열 1번에 3의 값이 들어가고
            Line[B].add(A); // 배열 3번에 1의 값이 들어가게 하면 됨
        }

        //문제의 포인트 가장 작은 수를 가지고 있는 사람의 수를 구하기
        int min = Integer.MAX_VALUE; //가장 작은 점수
        int people_number = 0; // 가장 작은 점수를 들고 있는 사람의 번호

        for (int start = 1; start <= N; start++) {
            int[] dist = new int[N + 1]; // 1부터 시작하므로 배열의 크기를 N + 1로 설정
            Arrays.fill(dist, -1); //가장 작은 수를 계속 갱신하기 위해서 사용(만일, 0으로 선언을 할 경우 계속 0에 갇혀 있는 문제가 발발할 수 있음)
            dist[start] = 0; // 출발점은 0으로

            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]); // 배열 두개를 비교할 때 더 작은 값을 꺼냄
            pq.offer(new int[]{0, start}); //거리, 출발점(출발점은 항상 새롭게 갱신)


            while (!pq.isEmpty()) {
                int[] min_num = pq.poll(); // 가장 작은 수를 꺼냄(자바에서 우선순위 큐는 poll명령어로 꺼낼 수 있음)
                int dist_count = min_num[0]; // 0번 칸은 거리
                int people_num = min_num[1]; // 1번 칸은 사람들의 번호

                if (dist_count > dist[people_num]) { // 제일 짧은 값을 출력하는 것이 정답이므로 계속해서 더 짧은 값으로 갱신
                    continue; // 더 짧은 값이 나올경우, 이전 짧은 값을 버림
                }

                for (int friend : Line[people_num]) { //위에서 만들어 둔 친구들의 배열을 하나씩 살피면서 해당 라인의 사람의 친구들을 한명씩 살피기
                    if (dist_count + 1 < dist[friend]) { // 더 짧은 길이일 경우
                        dist[friend] = dist_count + 1; // 더 짧은 길이로 기록을 갱신해줌
                        pq.offer(new int[]{dist[friend], friend}); // 기록에 추가

                    }
                }
            }

            int score = 0;
            for (int i = 1; i <= N; i++) {
                score += dist[i];
            }

            if (score < min) {
                min = score;
                people_number = start;
            }
        }
        System.out.println(people_number);
    }
}

//BFS 풀이 방법
public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //사람의 수
        int M = Integer.parseInt(st.nextToken()); // 친구 관계의 수

        ArrayList<Integer>[] friends_list = new ArrayList[N + 1]; //사람 번호가 1부터 시작하므로 배열의 크기는 N + 1 한 값으로 선언

        for (int i = 0; i < friends_list.length; i++) {
            friends_list[i] = new ArrayList<>(); // 배열이 하나씩 만들어짐 ex) 예를 들어서 현재 예시를 확인하면 5명의 친구들이 있는데 1번 친구의 친구는 어떤 친구가 존재하는지 그런 부분을 담기 위해서 현재 해당 배열을 선언
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine()); // 새로운 값으르 st 로 나누어야 하기 때문에 재선언 및 값이 한줄마다 달라져야 하므로 반복문 안에 같이 선언

            int a = Integer.parseInt(st2.nextToken()); // 두 사람의 번호가 주어짐
            int b = Integer.parseInt(st2.nextToken());

            friends_list[a].add(b); // 지금 위에서 한 줄마다 친구들을 친구 관계들을 받고 있음(a의 친구 리스트에 b를 담아주고)
            friends_list[b].add(a); // b의 친구 리스트에서도 b를 넣어줘야함
        }

        //거리 배열(1번 사람을 기준으로 각 사람까지 몇 단계인지 저장할 배열)
        int min = Integer.MAX_VALUE; // 지금까지 제일 작은 관계 수
        int people_number = 0; // 그 관계 수를 들고 있는 사람의 수

        for (int i = 1; i < N + 1; i++) { //사람 번호가 1번부터 이므로 1로 시작
            int[] dist = new int[N + 1]; //각 사람마다 관계의 단계가 몇인지 정하는 배열이므로 사람 수로 배열을 선언
            //초기값을 음수러 전부 채워줌
            Arrays.fill(dist, -1); // 초기값을 음수로 채우는 이유는 BFS는 도중에 해당 번호의 사람을 만났는지 유무를 확인해야함 그러므로 아직못만났다는 표시를 하기위해 나올 수 없는 수인 -1로 많이 사용

            dist[i] = 0; //시작점은 0으로 시작

            Queue<Integer> q = new ArrayDeque<>(); //이전 문제와 다른 부분은 BFS는 우선순위 큐가 아니라 그냥 큐 // 해당 문제는 친구 관계의 거리가 1로 고정이 되어있음 그렇기 때문에 줄이 거리 순서대로 정렬이 되어있음 맨 앞 사람을 꺼내면 그 사람이 거리가 가장 가까운 사람

            q.offer(i); // q에 시작점을 넣어줌

            while (!q.isEmpty()) { //큐가 비지 않을 때까지 반복
                int current = q.poll(); // q에서 하나씩 번호를 꺼내서 목록 정리

                for (int friend : friends_list[current]) {
                    if (dist[friend] != -1) { //friend까지의 거리가 -1이 아니면 이미 만난사람이므로 건너뛰기
                        continue;
                    }
                    dist[friend] = dist[current] + 1;
                    q.offer(friend);
                }
            }

            int score = 0; // 친구 관게를 합산 하는 변수
            for (int j = 1; j <= N; j++) {
                score += dist[j]; //모든 관게ㅢ 수를 살피는 반복문
            }
            if(score < min){ // 가장 작은 수가 있으면 새롭게 갱신을 하고 사람도 새롭게 갱신
                min = score;
                people_number = i;
            }
        }
            System.out.println(people_number);
    }
}