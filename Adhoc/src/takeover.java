import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class takeover {
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
        int kingdoms=Integer.parseInt(br.readLine());
        DisjointSet set=new DisjointSet(kingdoms);
        int unions=Integer.parseInt(br.readLine());
        while (unions-->0){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(tokenizer.nextToken());
            int y=Integer.parseInt(tokenizer.nextToken());
            set.union(x-1,y-1);
        }
        HashSet<Integer> distinct=new HashSet<>();
        for(int i=0;i<kingdoms;i++){
            distinct.add(set.find(i));
        }
        System.out.println(distinct.size());


    }
}
