import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 12/1/2018.
 */
public class colony {
    static HashMap<Integer,HashSet<Integer>> edges;
    static int vertices=0;
    //Same data structure used for kruskals
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
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());

        for(int c=1;c<=cases;c++){
            vertices=Integer.parseInt(br.readLine());
            edges=new HashMap<>();
            DisjointSet set=new DisjointSet(vertices+1);
            for(int i=1;i<=vertices;i++){
                edges.put(i,new HashSet<>());
            }
            for(int i=0;i<vertices-1;i++){
                StringTokenizer tokenizer=new StringTokenizer(br.readLine());
                int a=Integer.parseInt(tokenizer.nextToken());
                int b=Integer.parseInt(tokenizer.nextToken());
                edges.get(a).add(b);
                edges.get(b).add(a);
            }
            int queries=Integer.parseInt(br.readLine());
            System.out.println("Colony #"+c+":");
            int[][] qList=new int[queries][3];
            boolean[] results=new boolean[queries];
            //Go through queries and store them
            while (queries-->0){
                StringTokenizer tokenizer=new StringTokenizer(br.readLine());
                int type=Integer.parseInt(tokenizer.nextToken());
                int s=Integer.parseInt(tokenizer.nextToken());
                int e=Integer.parseInt(tokenizer.nextToken());
                qList[queries][0]=type;
                qList[queries][1]=s;
                qList[queries][2]=e;
                //collapse
                if(type==1){
                    edges.get(s).remove(e);
                    edges.get(e).remove(s);
                }

            }

            for(int s:edges.keySet())
                for (int e:edges.get(s))
                    set.union(s,e);

            //Go through queries backwards so you are only combining edges
            for(int i=0;i<results.length;i++){
                int type=qList[i][0];
                int s=qList[i][1];
                int e=qList[i][2];
                if(type==1){
                    set.union(s,e);
                }
                else{
                    results[i]=set.find(s)==set.find(e);
                }

            }
            //Output formatting
            for(int i=results.length-1;i>=0;i--){
                int type=qList[i][0];
                int s=qList[i][1];
                int e=qList[i][2];
                if(type==1){
                    edges.get(s).remove(e);
                    edges.get(e).remove(s);
                    System.out.println("Tunnel from "+s+" to "+e+" collapsed!");

                }
                else{
                    boolean reach=results[i];
                    String word="";
                    if(reach)
                        word="can";
                    else
                        word="cannot";
                    System.out.println("Room "+s+" "+word+" reach "+e);
                }
            }



            System.out.println();

        }

    }
}
