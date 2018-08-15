import java.io.*;
import java.util.*;

/**
 * Created by user on 8/12/2018.
 */
public class closingFarm {
    static class DisjointSet {
        static int[] parent;
        static int[] rank;
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
//    static class Edge{
//        int start,end;
//        public Edge(int start, int end){
//            this.start=start;
//            this.end=end;
//        }
//    }

    public static void main(String args[]) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("closing.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int barns=Integer.parseInt(tokenizer.nextToken());
        int edges=Integer.parseInt(tokenizer.nextToken());
        DisjointSet set=new DisjointSet(barns+1);
        ArrayList<Integer>[] edgeList=new ArrayList[barns+1];
        for(int i=0;i<edgeList.length;i++)
            edgeList[i]=new ArrayList<>();
        for(int i=0;i<edges;i++){
            StringTokenizer t=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(t.nextToken());
            int end=Integer.parseInt(t.nextToken());
            edgeList[start].add(end);
            edgeList[end].add(start);
        }
        ArrayList<Integer> toAdd=new ArrayList<>(barns);
        for(int i=0;i<barns;i++)
            toAdd.add(Integer.parseInt(br.readLine()));
        Collections.reverse(toAdd);
        boolean[] connected=new boolean[barns];
        boolean[] open=new boolean[barns+1];
        ArrayDeque<Integer> list=new ArrayDeque<>();
        for(int i=0;i<barns;i++){
            int start=toAdd.get(i);
            open[start]=true;
            for(int end:edgeList[start]){
                if(open[end])
                set.union(start,end);
            }
            list.add(start);
            while(list.size()>1){
                int first=list.pollFirst();
                int second=list.pollFirst();
                if(set.find(first)==set.find(second))
                    list.addFirst(first);
                else{
                    list.addFirst(second);
                    list.addFirst(first);
                    break;
                }
            }
//            System.out.println(list);
            connected[i]=list.size()==1;
        }
//        System.out.println(Arrays.toString(connected));
//        System.out.println(Arrays.toString(edgeList));
        for(int i=connected.length-1;i>=0;i--)
            pw.println(connected[i]?"YES":"NO");
        pw.close();
    }
}
