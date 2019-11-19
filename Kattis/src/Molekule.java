import java.io.*;
import java.util.*;

/**
 * Created by user on 11/5/2019.
 */
public class Molekule {
    static class Edge{
        int a,b;
        public Edge(int a, int b){
            this.a=a;
            this.b=b;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashSet<Integer>[] edges = new HashSet[n];
        ArrayList<Edge> list = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            edges[i]=new HashSet<>();
        }

        for(int i=1;i<n;i++){
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken())-1;
            int b = Integer.parseInt(tokenizer.nextToken())-1;
            edges[a].add(b);
            edges[b].add(a);
            list.add(new Edge(a,b));
        }
        HashSet<Integer>[] directed = new HashSet[n];
        for(int i=0;i<n;i++){
            directed[i]=new HashSet<>();
        }
        int root =1;


        ArrayDeque<Integer> q= new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        ArrayDeque<Integer> dist = new ArrayDeque<>();
        q.addFirst(root);
        dist.addFirst(0);
        while(!q.isEmpty()){
            int cur = q.pollFirst();
            int curDist = dist.pollFirst();

            visited[cur]=true;
            for(int out: edges[cur]){
                if(!visited[out]) {
                    q.addLast(out);
                    if(curDist%2==0)
                        directed[cur].add(out);
                    else
                        directed[out].add(cur);
                    dist.addLast(curDist+1);
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(Edge e:list){

            if(directed[e.a].contains(e.b)){
                bw.write('1');
                bw.write('\n');
            }
            else {
                bw.write('0');
                bw.write('\n');
            }

        }
        bw.flush();



    }
}
