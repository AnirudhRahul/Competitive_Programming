/**
 * Created by user on 11/16/2019.
 */

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class RoughRoads10356 {
    static class Edge{
        int a,b, weight;
        public Edge(int a, int b, int weight){
            this.a=a;
            this.b=b;
            this.weight=weight;
        }
        public String toString(){return a+"->"+b;}
    }

    static class Node implements Comparable<Node>{
        int id, dist;
        public Node(int id, int dist){
            this.id=id;
            this.dist=dist;
        }


        @Override
        public int compareTo(Node o) {
            return dist-o.dist;
        }
    }

//    static int offSet=Integer.MAX_VALUE/1024;
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner scan = new Scanner(System.in);
        int c=0;
        testCase: while(scan.hasNextInt()){
            c++;
//            String in = br.readLine();
//            if(in==null||in.length()<3)
//                break;
//            StringTokenizer tokenizer = new StringTokenizer(in);
            int vertices = scan.nextInt();
            int edges = scan.nextInt();

            ArrayList<Edge>[] map = new ArrayList[2*vertices];
            for(int i=0;i<map.length;i++)
                map[i]=new ArrayList<>();
            // 0 to vert-1 biked last road
            // vert to 2vert-1 walked last road

            for(int i=0;i<edges;i++){
//                tokenizer = new StringTokenizer(br.readLine());
                int a = scan.nextInt();
                int b = scan.nextInt();
                int w = scan.nextInt();
                map[a+vertices].add(new Edge(a+vertices,b,w));
//                map[a+vertices].add(new Edge(a+vertices,b+vertices,w));
                map[a].add(new Edge(a,b+vertices,w));

                map[b+vertices].add(new Edge(b+vertices,a,w));
//                map[b+vertices].add(new Edge(b+vertices,a+vertices,w));
                map[b].add(new Edge(b,a+vertices,w));
            }

//            int source = vertices;
//            System.out.println(Arrays.toString(map));
            PriorityQueue<Node> q = new PriorityQueue<>();
            q.add(new Node(0,0));
            int[] dist = new int[2*vertices];
            for(int i=0;i<dist.length;i++)
                dist[i]=Integer.MAX_VALUE;
            while(!q.isEmpty()){
                Node cur = q.poll();
                if(cur.id==vertices-1){
                    System.out.println("Set #"+c);
                    System.out.println(cur.dist);
                    continue testCase;
                }
                if(cur.dist < dist[cur.id]){
                    dist[cur.id]=cur.dist;
                    for(Edge e:map[cur.id]){
                        q.add(new Node(e.b,cur.dist+e.weight));
                    }

                }
            }
            System.out.println("Set #"+c);
            System.out.println("?");


        }

    }
}
