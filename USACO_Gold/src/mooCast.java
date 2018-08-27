import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by user on 8/20/2018.
 */
public class mooCast{
    static class DisjointSet {
        int[] parent;
        int[] rank;
        public DisjointSet(int size){
            parent=new int[size];
            rank=new int[size];
            for(int i=0;i<size;i++)
                parent[i]=i;
        }

        public int find(int x){
            if(parent[x]==x)
                return x;
            else
                return parent[x]=find(parent[x]);
        }

        public void union(int x, int y){
            int xRoot=find(x);
            int yRoot=find(y);
            if (rank[xRoot] < rank[yRoot])
                parent[xRoot]=yRoot;
            else if(rank[yRoot] < rank[xRoot])
                parent[yRoot]=xRoot;
            else {
                parent[yRoot]=xRoot;
                rank[xRoot]++;
            }

        }


    }
    static class Point{
        int x,y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    public static int dist(Point a, Point b){
        return (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
    }
    static class Edge implements Comparable<Edge>{
        int len;
        int start,end;
        public Edge(int start, int end, int len){
            this.start=start;
            this.end=end;
            this.len=len;
        }

        @Override
        public int compareTo(Edge o) {
            return len-o.len;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int points=Integer.parseInt(br.readLine());
        Point[] list=new Point[points];
        for(int i=0;i<points;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            list[i]=new Point(Integer.parseInt(tokenizer.nextToken()),Integer.parseInt(tokenizer.nextToken()));
        }
        ArrayList<Edge> edgeList=new ArrayList<Edge>(points*points/2);
        for(int i=0;i<points;i++)
            for(int j=i+1;j<points;j++){
                edgeList.add(new Edge(i,j,dist(list[i],list[j])));
//                edgeList.add(new Edge(j,i,dist));
            }

        Collections.sort(edgeList);
        DisjointSet disjointSet=new DisjointSet(points);
        Edge last=null;
        int edges=0;
        for(int i=0;i<edgeList.size();i++){
            if(edges==points-1)
                break;
            Edge cur=edgeList.get(i);
            if(disjointSet.find(cur.start)==disjointSet.find(cur.end))
                continue;
            else{
                disjointSet.union(cur.start,cur.end);
                edges++;
                last=cur;
            }
        }
        pw.println(last.len);
        pw.close();
//        System.out.println(last.len);
    }
}
