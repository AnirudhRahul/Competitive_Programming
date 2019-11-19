/**
 * Created by user on 11/15/2019.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Hierarchy_R17 {
    static class Edge implements Comparable<Edge>{
        int a,b, weight, rankB;
        public Edge(int a, int b, int weight, int rankB){
            this.a=a;
            this.b=b;
            this.weight=weight;
            this.rankB=rankB;
        }


        @Override
        public int compareTo(Edge o) {
            if(rankB!=o.rankB)
                return -rankB+o.rankB;
            else
                return weight-o.weight;

        }
    }

//    static class DisjointSet{
//        int[] parent;
//        public DisjointSet(int len){
//            parent = new int[len];
//            for(int i=0;i<len;i++)
//                parent[i]=i;
//        }
//
//        public int find(int x){
//            if(parent[x]==x)
//                return x;
//            else
//                return parent[x]=find(parent[x]);
//        }
//
//        public void union(int a, int b){
//            parent[find(a)]=find(b);
//        }
//
//
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        int vertices = Integer.parseInt(br.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        int root =-1;
        int[] w = new int[vertices];
        for(int i=0;i<vertices;i++){
            int cur = Integer.parseInt(stringTokenizer.nextToken());
            w[i]=cur;
            if(cur>max) {
                max=cur;
                root = i;
            }
        }

        int edges = Integer.parseInt(br.readLine());
        PriorityQueue<Edge> q = new PriorityQueue<>();
        ArrayList<Edge>[] edgeList = new ArrayList[vertices];
        for(int i=0;i<vertices;i++)
            edgeList[i] = new ArrayList<>();
//        DisjointSet set = new DisjointSet(vertices);
        for(int i=0;i<edges;i++){
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken())-1;
            int b = Integer.parseInt(tokenizer.nextToken())-1;
            int weight = Integer.parseInt(tokenizer.nextToken());
//            q.add(new Edge(a,b,weight));
            edgeList[a].add(new Edge(a,b,weight,w[b]));

        }

        long sum =0;

        q.addAll(edgeList[root]);
        boolean[] inTree = new boolean[vertices];
        inTree[root]=true;
        while(!q.isEmpty()){
            Edge cur = q.poll();
            if(inTree[cur.b])
                continue;

            inTree[cur.b]=true;
            sum+=cur.weight;
            q.addAll(edgeList[cur.b]);

        }

        for(int i=0;i<vertices;i++){
            if(!inTree[i]){
                System.out.println(-1);
                return;
            }
        }

        System.out.println(sum);


    }
}
