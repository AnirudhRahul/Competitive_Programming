
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 11/28/2018.
 */
public class dijkstra {
    static class Node implements Comparable<Node>{
        int id,dist;
        public Node(int id,int dist){this.dist=dist;this.id=id;}

        @Override
        public int compareTo(Node o) {
            return dist-o.dist;
        }
    }

    static class Edge{
        int a,b,weight;
        public Edge(int a, int b, int weight){this.a=a;this.b=b;this.weight=weight;}
        public String toString(){return a+"->"+b+" "+weight;}
    }

    //Vertices 0 indexed
    public static int[] dijkstra(HashMap<Integer,ArrayList<Edge>> edges, int vertices, int source){
        int[] dist=new int[vertices];
        Arrays.fill(dist, (int) 1e9);
        boolean[] visited=new boolean[vertices];
        PriorityQueue<Node> queue=new PriorityQueue<>();
        int totalVisited=0;
        dist[source]=0;
        queue.add(new Node(source,0));
        while (!queue.isEmpty()&&totalVisited!=vertices){
            Node cur=queue.poll();
            if(visited[cur.id])
                continue;
            visited[cur.id]=true;
            totalVisited++;
            dist[cur.id]=Math.min(cur.dist,dist[cur.id]);
            for(Edge e:edges.get(cur.id)){
                queue.add(new Node(e.b,cur.dist+e.weight));
            }
        }

        return dist;
    }

    public static int dijkstra(HashMap<Integer,ArrayList<Edge>> edges, int vertices, int source, int end){
        int[] dist=new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited=new boolean[vertices];
        PriorityQueue<Node> queue=new PriorityQueue<>();
        int totalVisited=0;
        dist[source]=0;
        queue.add(new Node(source,0));
        while (!queue.isEmpty()&&totalVisited!=vertices){
            Node cur=queue.poll();
            if(cur.id==end)
                return cur.dist;
            if(visited[cur.id])
                continue;
            visited[cur.id]=true;
            totalVisited++;
            dist[cur.id]=Math.min(cur.dist,dist[cur.id]);
            for(Edge e:edges.get(cur.id)){
                queue.add(new Node(e.b,cur.dist+e.weight));
            }
        }

        return Integer.MAX_VALUE;
    }

}
