import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
        int order = 0;

        class Patient{
            String name;
            int age;
            int serverity;
            int order;
            boolean treated;

            Patient(String name, int age, int serverity, int order, boolean treated){
                this.name = name;
                this.age = age;
                this.serverity = serverity;
                this.order = order;
                this.treated = treated;
            }
        }

        Queue<Patient> q = new ArrayDeque();
        PriorityQueue<Patient> pq = new PriorityQueue<>((a,b) -> {
            if(a.serverity != b.serverity) return b.serverity - a.serverity;
            if(a.age != b.age) return a.age - b.age;
            else return a.order - b.order;
        });

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            if(Integer.parseInt(st.nextToken()) == 1){
                String name = st.nextToken();
                int age = Integer.parseInt(st.nextToken());
                int serverity = Integer.parseInt(st.nextToken());
                boolean treated = false;
                Patient p = new Patient(name, age, serverity, order++, treated);
                pq.offer(p);
                q.offer(p);
            }
            else{
                if(st.nextToken().equals("A")){
                    while(!q.isEmpty() && q.peek().treated == true){
                        q.poll();
                    }
                    if(q.isEmpty()){
                        System.out.println("EMPTY");
                    }
                    else{
                        Patient p = q.poll();
                        p.treated = true;
                        System.out.println(p.name);
                    }
                }
                else{
                    while(!pq.isEmpty() && pq.peek().treated == true){
                        pq.poll();
                    }
                    if(pq.isEmpty()){
                        System.out.println("EMPTY");
                    }
                    else{
                        Patient p = pq.poll();
                        p.treated = true;
                        System.out.println(p.name);
                    }

                }

            }
        }

    }
}