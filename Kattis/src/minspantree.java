/**
 * Created by user on 11/15/2019.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class minspantree {
    static class Edge implements Comparable<Edge>{
        int a,b,weight;
        public Edge(int a, int b, int weight){
            this.a=a;
            this.b=b;
            this.weight=weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight-o.weight;
        }
    }

    static class Pair implements Comparable<Pair>{
        int a,b;

        public Pair(Edge in){
            this.a=Math.min(in.a, in.b);
            this.b=Math.max(in.a,in.b);
        }
        @Override
        public int compareTo(Pair o) {
            if(a!=o.a)
                return a-o.a;
            else
                return b-o.b;
//            return 0;
        }

    }
    static class DisjointSet{
        int[] parent;
        public DisjointSet(int len){
            parent= new int[len];
            for(int i=0;i<len;i++)
                parent[i]=i;
        }
        int find(int x){
            if(parent[x]==x)
                return x;
            else
                return parent[x]=find(parent[x]);
        }

        void union(int x, int y){
            parent[find(x)]=find(y);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            int vertices = Integer.parseInt(stringTokenizer.nextToken());
            int edges = Integer.parseInt(stringTokenizer.nextToken());
            if(vertices+edges==0)
                break;

            PriorityQueue<Edge> q = new PriorityQueue<>();
            DisjointSet set = new DisjointSet(vertices);
            ArrayList<Pair> list = new ArrayList<>(vertices);

            for(int i=0;i<edges;i++){
                StringTokenizer tokenizer = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());
                int w = Integer.parseInt(tokenizer.nextToken());
                q.add(new Edge(a, b, w));
            }
            int cost=0;
            while(!q.isEmpty()){
                Edge cur = q.poll();
                if(set.find(cur.a)==set.find(cur.b))
                    continue;
                cost+=cur.weight;
                list.add(new Pair(cur));
                set.union(cur.a,cur.b);
                if(list.size()==vertices-1)
                    break;
            }
            if(list.size()!=vertices-1)
                System.out.println("Impossible");
            else{
                System.out.println(cost);
                Collections.sort(list);
                for(Pair e:list){
                    wr.write(e.a+" "+e.b+"\n");
                }
                wr.flush();
            }

        }
    }
}
