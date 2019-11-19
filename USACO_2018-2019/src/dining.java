import javafx.scene.input.InputMethodTextRun;

import java.io.*;
import java.util.*;

public class dining {
    static class Node implements Comparable<Node>{
        int id,dist,stamina;
        public Node(int id,int dist,int stamina){this.dist=dist;this.id=id;this.stamina=stamina;}

        @Override
        public int compareTo(Node o) {
            return dist-o.dist;
        }
    }

    static class Edge{
        int a,b,weight,stamina;
        public Edge(int a, int b, int weight, int stamina){this.a=a;this.b=b;this.weight=weight;this.stamina=stamina;}
        public String toString(){return a+"->"+b+" weight:"+weight+" stamina-cost:"+stamina;}
    }



    static int[][] dijkstra(HashMap<Integer,ArrayList<Edge>> edges, int vertices, int source,int stamina){
        int[][] dist=new int[vertices][stamina+1];
        for(int[] e:dist)
            Arrays.fill(e, Integer.MAX_VALUE);
//        boolean[][] visited=new boolean[vertices][stamina+1];
        PriorityQueue<Node> queue=new PriorityQueue<>();

        dist[source][stamina]=0;
        queue.add(new Node(source,0,stamina));
        while (!queue.isEmpty()){
            Node cur=queue.poll();
            if(dist[cur.id][cur.stamina]<cur.dist)
                continue;


            dist[cur.id][cur.stamina]=Math.min(cur.dist,dist[cur.id][cur.stamina]);
            for(Edge e:edges.get(cur.id)){
                if(cur.stamina-e.stamina>=0)
                    queue.add(new Node(e.b,cur.dist+e.weight,cur.stamina-e.stamina));
            }
        }

        return dist;
    }







    public static void main(String args[]) throws IOException {
        String name = "dining";
        BufferedReader br = new BufferedReader(new FileReader(name + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(name + ".out")));
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer2=new StringTokenizer(br.readLine());
        int vertices=Integer.parseInt(tokenizer2.nextToken());
        int edgeNum=Integer.parseInt(tokenizer2.nextToken());
        int reducers=Integer.parseInt(tokenizer2.nextToken());
        int[][] dist;

        HashMap<Integer,ArrayList<Edge>> edges=new HashMap<>();
        for(int i=0;i<vertices;i++)
            edges.put(i,new ArrayList<>());
        for(int i=0;i<edgeNum;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            Edge e=new Edge(Integer.parseInt(tokenizer.nextToken())-1,Integer.parseInt(tokenizer.nextToken())-1,Integer.parseInt(tokenizer.nextToken()),0);
            edges.get(e.a).add(e);
            edges.get(e.b).add(new Edge(e.b,e.a,e.weight,0));
        }
        int[] haybayles=new int[vertices];
        for(int i=0;i<reducers;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int index= Integer.parseInt(tokenizer.nextToken());
            int yum= Integer.parseInt(tokenizer.nextToken());
            haybayles[index-1]=Math.max(yum,haybayles[index-1]);
        }
        for(int i=0;i<haybayles.length;i++)
            if(haybayles[i]!=0)
                edges.get(i).add(new Edge(i,i,-haybayles[i],1));

//        int totalVisited=0;
//        int source=vertices-1;
//        dist[source][0]=0;
//        PriorityQueue<Node> queue=new PriorityQueue<>();


        dist=dijkstra(edges,vertices,vertices-1,1);

        for(int[] e:dist)
            System.out.println(Arrays.toString(e));
        for(int i=0;i<dist.length-1;i++){
            if(dist[i][0]<=dist[i][1])
                pw.println(1);
            else
                pw.println(0);
        }
        pw.close();
    }
}