/**
 * Created by user on 11/10/2019.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

//Wrong Solution still very stuck
public class MST01_R599 {
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int vertices = Integer.parseInt(tokenizer.nextToken());
        int edges = Integer.parseInt(tokenizer.nextToken());
        HashSet<Integer>[] edgeList  = new HashSet[vertices];
        for(int i=0;i<vertices;i++)
            edgeList[i] = new HashSet<>();

        DisjointSet set = new DisjointSet(vertices);

        for(int i=0;i<edges;i++){
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken())-1;
            int b = Integer.parseInt(tokenizer.nextToken())-1;
            edgeList[a].add(b);
            edgeList[b].add(a);

        }
        for (int i = 0; i < vertices; i++) {
            if (set.find(i) == i)
                for (int j = 0; j < vertices; j++)
                    if (i != j && !edgeList[i].contains(j)) {
                        set.union(i, j);
                    }
        }

        HashSet<Integer> distinct = new HashSet<>();
        for(int i = 0 ; i<vertices;i++){
            distinct.add(set.find(i));
        }
        System.out.println(distinct.size()-1);



    }
}
