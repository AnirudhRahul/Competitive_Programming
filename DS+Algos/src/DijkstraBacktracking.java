/**
 * Created by user on 11/17/2019.
 */


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DijkstraBacktracking {
    static class Node implements Comparable<Node>{
        int id;
        long dist;
        Node last;
        public Node(int id, long dist){
            this.dist=dist;
            this.id=id;
        }

        public Node(int id, long dist, Node last){
            this.dist=dist;
            this.id=id;
            this.last=last;
        }

        @Override
        public int compareTo(Node o) {
            long diff = dist-o.dist;
            if(diff>0)
                return 1;
            else if(diff<0)
                return -1;
            else
                return 0;
        }
    }

    static class Edge{
        int a,b,weight;
        public Edge(int a, int b, int weight){
            this.a=a;
            this.b=b;
            this.weight=weight;
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int vertices = Integer.parseInt(tokenizer.nextToken());
        int edges = Integer.parseInt(tokenizer.nextToken());
        ArrayList<Edge>[] map = new ArrayList[vertices];
        for(int i=0;i<vertices;i++)
            map[i] = new ArrayList<>();

        for(int i=0;i<edges;i++){
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken())-1;
            int b = Integer.parseInt(tokenizer.nextToken())-1;
            int w = Integer.parseInt(tokenizer.nextToken());
            map[a].add(new Edge(a,b,w));
            map[b].add(new Edge(b,a,w));
        }

        Node start = new Node(0,0);
        long[] dist = new long[vertices];
        for(int i=0;i<dist.length;i++)
            dist[i]= Long.MAX_VALUE;


        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(start);
        Node end = null;
        while (!q.isEmpty()){
            Node cur = q.poll();
            if(cur.id==vertices-1){
                end=cur;
                break;
            }
            if(cur.dist>dist[cur.id])
                continue;
            dist[cur.id]=cur.dist;
            for(Edge e:map[cur.id]){
                if(dist[e.b]>cur.dist+e.weight)
                q.add(new Node(e.b,cur.dist+e.weight,cur));
            }
        }
        if(end==null){
            System.out.println(-1);
            return;
        }
        ArrayList<Integer> path = new ArrayList<>();
        path.add(end.id);
        while(true){
            if(end.id==0)
                break;
            if(end==null){
                System.out.println(-1);
                return;
            }

            path.add(end.last.id);
            end=end.last;
        }
        for(int i=path.size()-1;i>=0;i--){
            wr.write((path.get(i)+1)+"");
            if(i==0)
                wr.write("\n");
            else
                wr.write(" ");

        }
        wr.flush();



    }
}
