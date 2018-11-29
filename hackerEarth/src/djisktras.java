import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 11/14/2018.
 */
public class djisktras{
    static class Edge{
        int b;
        short weight;
        public Edge(int b, int weight){
            this.b=b;
            this.weight= (short) weight;
        }
        public String toString(){return b+" "+weight;}
    }

    static class Vertex implements Comparable<Vertex>{
        int id;
        int distance;
        public Vertex(int id, int distance){
            this.id=id;
            this.distance=distance;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.distance-distance;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        int vertices=Integer.parseInt(stringTokenizer.nextToken());
        int edges=Integer.parseInt(stringTokenizer.nextToken());
        int[] dist=new int[vertices+1];
        for(int i=1;i<=vertices;i++)
            dist[i]=Integer.MAX_VALUE;
        HashMap<Integer, ArrayList<Edge>> edgeList=new HashMap<>();
        for(int i=1;i<=vertices;i++)
            edgeList.put(i,new ArrayList<Edge>(edges/vertices));
        for(int i=0;i<edges;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(tokenizer.nextToken());
            int b=Integer.parseInt(tokenizer.nextToken());
            int weight=Integer.parseInt(tokenizer.nextToken());
            edgeList.get(a).add(new Edge(b,weight));
        }
        Vertex start=new Vertex(1,0);
        PriorityQueue<Vertex> q=new PriorityQueue<>();
        q.add(start);
        while(!q.isEmpty()){
            Vertex cur=q.poll();
            if(dist[cur.id]>cur.distance)
                dist[cur.id]=cur.distance;
            for(Edge e:edgeList.get(cur.id)){
                int newWeight=e.weight+cur.distance;
                if(dist[e.b]>newWeight)
                    q.add(new Vertex(e.b,newWeight));
            }

        }
//        System.out.println(edgeList);
//        System.out.println(Arrays.toString(dist));
        for(int i=2;i<dist.length;i++){
            String end=i==dist.length-1?"":" ";
            if(dist[i]==Integer.MAX_VALUE)
                System.out.print((long)10e9+end);
            else
                System.out.print(dist[i]+end);
        }

    }
}
