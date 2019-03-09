
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 11/28/2018.
 */
public class wipeout {

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

    //Vertices 0 indexed
    static int dijkstra(HashMap<Integer,ArrayList<Edge>> edges, int vertices, int source, int end, int stamina){
        int[][] dist=new int[vertices][stamina+1];
        for(int[] e:dist)
            Arrays.fill(e, Integer.MAX_VALUE);
        boolean[][] visited=new boolean[vertices][stamina+1];
        PriorityQueue<Node> queue=new PriorityQueue<>();
        int endVisited=0;
        dist[source][stamina]=0;
        queue.add(new Node(source,0,stamina));
        while (!queue.isEmpty()&&endVisited!=stamina+1){
            Node cur=queue.poll();
            if(visited[cur.id][cur.stamina])
                continue;
            visited[cur.id][cur.stamina]=true;
            if(cur.id==end)
                endVisited++;

            dist[cur.id][cur.stamina]=Math.min(cur.dist,dist[cur.id][cur.stamina]);
            for(Edge e:edges.get(cur.id)){
                if(cur.stamina-e.stamina>=0)
                    queue.add(new Node(e.b,cur.dist+e.weight,cur.stamina-e.stamina));
            }
        }

        return Arrays.stream(dist[end]).min().getAsInt();
    }




    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        int c=0;
        while (cases-->0){
            c++;
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int platforms = Integer.parseInt(tokenizer.nextToken());
            int edges = Integer.parseInt(tokenizer.nextToken());
            int stamina = Integer.parseInt(tokenizer.nextToken());
            int vertices=platforms;
            HashMap<Integer,ArrayList<Edge>> edgeList=new HashMap<>();
            for(int i=0;i<vertices;i++)
                edgeList.put(i,new ArrayList<>(1));
            for(int i=0;i<edges;i++){
                StringTokenizer tokenizer1=new StringTokenizer(br.readLine());
                //0 index the edges
                int a=Integer.parseInt(tokenizer1.nextToken())-1;
                int b=Integer.parseInt(tokenizer1.nextToken())-1;
                int staminaCost=Integer.parseInt(tokenizer1.nextToken());
                int time=Integer.parseInt(tokenizer1.nextToken());
                edgeList.get(a).add(new Edge(a,b,time,staminaCost));
            }
//            System.out.println(edgeList);
            int dist=dijkstra(edgeList,vertices,0,vertices-1,stamina);
            if(dist==Integer.MAX_VALUE)
                System.out.println("Episode #"+c+": Wipeout!");
            else
                System.out.println("Episode #"+c+": "+dist);
        }
    }
}
