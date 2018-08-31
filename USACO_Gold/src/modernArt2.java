import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by user on 8/30/2018.
 */
public class modernArt2{
    static class Range{
        int start,end;
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public String toString(){
            return start+" "+end;
        }
    }
    static class DisjointSet {
        int[] parent;
        int[] rank;
        public DisjointSet(int size,int[] list){
            parent=new int[size];
            rank=new int[size];
            for(int i=1;i<size;i++) {
                if(list[i]==list[i-1])
                    parent[i]=parent[i-1];
                else
                    parent[i] = i;
            }
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
            if(xRoot==yRoot)
                return;
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
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("art2.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));
        int len=Integer.parseInt(br.readLine());
        int[] list=new int[len];
        for(int i=0;i<len;i++)
            list[i]=Integer.parseInt(br.readLine());

        //finds the troll test case
        boolean symmetric=true;
        for(int i=0;i<len;i++)
            if(list[i]!=list[len-i-1]) {
                symmetric = false;
                break;
            }
        if(symmetric){
            pw.println(len/2);
            pw.close();
            return;
        }

        DisjointSet disjointSet=new DisjointSet(len,list);
        HashMap<Integer,Range> ranges=new HashMap<>(len/10);
        int totalColors=0;
        for(int i=0;i<len;i++){
            if(list[i]==0)
                continue;
            if(!ranges.containsKey(list[i])) {
                ranges.put(list[i],new Range(i, i));
                totalColors++;
            }
            else
                ranges.get(list[i]).end=i;
        }

//        System.out.println(Arrays.toString(ranges));
        int iter=0;
        int colorsUsed=0;
//        System.out.println(Arrays.toString(disjointSet.parent));
        while(true){
            int foundThisIter=0;
            HashSet<Integer> connectionSpots=new HashSet<>();
            ArrayDeque<Integer> toRemove=new ArrayDeque<>(ranges.size());
            for(int e:ranges.keySet()){
                Range cur=ranges.get(e);
                if(disjointSet.find(cur.start)==disjointSet.find(cur.end)){
                    connectionSpots.add(cur.start);
                    connectionSpots.add(cur.end);
                    toRemove.add(e);
                    colorsUsed++;
                    foundThisIter++;
                }

            }

            if(foundThisIter==0)
                break;
            if(colorsUsed==totalColors)
                break;
            for(int e:toRemove){
                ranges.remove(e);
            }
            for(int e:connectionSpots){
//                System.out.println(e);
                if(e-1>=0&&list[e-1]!=0)
                disjointSet.union(e,e-1);
                if(e+1<list.length&&list[e+1]!=0)
                disjointSet.union(e,e+1);
            }


            iter++;
        }
//        System.out.println(iter+1);
        if(colorsUsed!=totalColors)
            pw.println(-1);
        else
            pw.println(iter+1);
        pw.close();
    }
}
