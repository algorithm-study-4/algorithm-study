import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
    
    // 환자 한 명의 정보를 담는 클래스
    static class Patient {
        String name;
        int age;
        int severity; // 응급도
        int order; // 접수된 순서

        Patient(String name, int age, int severity, int order) {
            this.name = name;
            this.age = age;
            this.severity = severity;
            this.order = order;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine().trim());

        // A동: 접수 순서대로 진료 (FIFO) -> 일반 큐 사용
        Deque<Patient> queueA = new ArrayDeque<>();

        // B동: 응급도 높은 순 -> 같으면 나이 어린 순 -> 같으면 먼저 접수한 순
        PriorityQueue<Patient> queueB = new PriorityQueue<>((p1, p2) -> {
            if (p1.severity != p2.severity) {
                return p2.severity - p1.severity; // 응급도는 내림차순
            }
            if (p1.age != p2.age) {
                return p1.age - p2.age; // 나이는 오름차순
            }
            return p1.order - p2.order; // 접수 순서는 오름차순
        });

        // 이미 진료받은 환자의 이름을 기록 (A, B 두 대기열 모두에서 공유되는 '치료 완료' 표시)
        Set<String> treated = new HashSet<>();

        int regOrder = 0; // 환자 접수 순서를 매기기 위한 카운터
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                // 환자 접수: 이름, 나이, 응급도
                String name = st.nextToken();
                int age = Integer.parseInt(st.nextToken());
                int severity = Integer.parseInt(st.nextToken());

                Patient p = new Patient(name, age, severity, regOrder++);

                // 같은 환자 객체를 A동 큐와 B동 큐 양쪽에 모두 등록
                // (한쪽에서 진료되면 다른 쪽에서는 '이미 치료됨'으로 처리해서 걸러냄)
                queueA.addLast(p);
                queueB.add(p);
            } else {
                // 진료 호출: A 또는 B
                String ward = st.nextToken();

                if (ward.equals("A")) {
                    // A동 큐의 맨 앞부터 확인하면서, 이미 다른 쪽(B)에서 진료된 환자는 건너뜀 ('지연 삭제')
                    while (!queueA.isEmpty() && treated.contains(queueA.peekFirst().name)) {
                        queueA.pollFirst();
                    }

                    if (queueA.isEmpty()) {
                        sb.append("EMPTY").append("\n");
                    } else {
                        Patient p = queueA.pollFirst();
                        treated.add(p.name);
                        sb.append(p.name).append("\n");
                    }
                } else { // ward.equals("B")
                    // B동 큐의 최우선 순위부터 확인하면서, 이미 다른 쪽(A)에서 진료된 환자는 건너뜀
                    while (!queueB.isEmpty() && treated.contains(queueB.peek().name)) {
                        queueB.poll();
                    }

                    if (queueB.isEmpty()) {
                        sb.append("EMPTY").append("\n");
                    } else {
                        Patient p = queueB.poll();
                        treated.add(p.name);
                        sb.append(p.name).append("\n");
                    }
                }
            }
        }

        System.out.print(sb);
    }
}