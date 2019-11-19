import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 4/17/2019.
 */
public class dominatingDisney {
    static class Edge{
        int a,b, weight;
        int edgeName;
        public Edge(int a, int b, int weight, int edgeName){
            this.a=a;
            this.b=b;
            this.weight=weight;
            this.edgeName=edgeName;
        }
        public String toString(){return b+" "+edgeName;}

    }

    static class Vertex implements Comparable<Vertex>{
        int visited;
        int index;
        ArrayList<Integer> path = new ArrayList<>();
        int dist;

        public Vertex(int index, int dist){
            this.index=index;
            this.dist=dist;
        }

        public Vertex(Vertex in, int index, int distToAdd, int edgeName){
            path= (ArrayList<Integer>) in.path.clone();
            path.add(edgeName);
            dist=in.dist+distToAdd;
            this.index=index;
            visited=in.visited;
        }

        public boolean visitedPlace(int index){
            return (visited&(1<<index))!=0;
        }

        public void visit(int index){
            if(!visitedPlace(index))
            visited+=1<<index;

        }

        public boolean visitedAll(){
            return visited==31;
        }

        @Override
        public int compareTo(Vertex o) {
            if(dist==o.dist){
                return (path.get(0)-o.path.get(0));
            }
            return dist-o.dist;
        }

        public String toString(){return ""+dist;}

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        while(cases-->0){
            int[] weights = new int[7];
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            for(int i=0;i<7;i++)
                weights[i]=Integer.parseInt(tokenizer.nextToken());
            HashMap<Integer, HashSet<Edge>> edgeList = new HashMap<>();
            Edge path1 = new Edge(2,3,weights[0],1);
            Edge path2 = new Edge(0,3,weights[1],2);
            Edge path3 = new Edge(0,2,weights[2],3);
            Edge path4 = new Edge(0,1,weights[3],4);
            Edge path5 = new Edge(1,2,weights[4],5);
            Edge path6 = new Edge(1,4,weights[5],6);
            Edge path7 = new Edge(2,4,weights[6],7);
            Edge[] arr = {path1,path2,path3,path4,path5,path6,path7};
            for(int i=0;i<5;i++)
                edgeList.put(i,new HashSet<>());
            for(Edge e:arr){
                edgeList.get(e.a).add(e);
                edgeList.get(e.b).add(new Edge(e.b,e.a,e.weight,e.edgeName));
            }
            Vertex start = new Vertex(2,0);
            PriorityQueue<Vertex> queue = new PriorityQueue<>();
            queue.add(start);
            Vertex ans=null;
//            System.out.println(edgeList);
            while(!queue.isEmpty()){
                Vertex cur = queue.remove();
//                System.out.println("Index: "+cur.index+"\t");

                cur.visit(cur.index);
//                System.out.println("Visited: "+Integer.toBinaryString(cur.visited)+"\t"+cur.visited);

                if(cur.visitedAll()) {
                    ans = cur;
                    break;
                }

                for(Edge e: edgeList.get(cur.index)){
                    queue.add(new Vertex(cur,e.b,e.weight,e.edgeName));
                }
//                System.out.println(queue);
            }
            for(int i=0;i<ans.path.size();i++) {
                if(i!=ans.path.size()-1)
                    System.out.print(ans.path.get(i) + " ");
                else
                    System.out.println(ans.path.get(i));

            }
//            System.out.println(ans.path.toString().replaceAll("[d,w]",""));

        }
    }
}
