import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by user on 7/27/2018.
 */

public class fencedIn {

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


    static class Edges implements Comparable<Edges>{
        long length;
        //does the set of edges run vertically
        boolean isVertical;
        int index;
        public Edges(long length,int index, boolean isVertical){
            this.length=length;
            this.index=index;
            this.isVertical=isVertical;
        }

        @Override
        public int compareTo(Edges o) {
            long val=length-o.length;
            if(val>0)
                return 1;
            else if(val<0)
                return -1;
            else
                return 0;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fencedin.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        long maxX=Long.parseLong(tokenizer.nextToken());
        long maxY=Long.parseLong(tokenizer.nextToken());
        int cols=Integer.parseInt(tokenizer.nextToken())+1;
        int rows=Integer.parseInt(tokenizer.nextToken())+1;
        ArrayList<Edges> list=new ArrayList<Edges>();
        ArrayList<Long> vertical=new ArrayList<>(cols+2);
        ArrayList<Long> horizontal=new ArrayList<>(rows+2);
        DisjointSet disjointSet=new DisjointSet(cols+rows);
        vertical.add(0L);
        vertical.add(maxX);
        horizontal.add(0L);
        horizontal.add(maxY);
        for(int i=0;i<cols-1;i++)
            vertical.add(Long.parseLong(br.readLine()));
        for(int i=0;i<rows-1;i++)
            horizontal.add(Long.parseLong(br.readLine()));
        Collections.sort(vertical);
        Collections.sort(horizontal);
        //Differences between vertical lines create horizontal edges
        for(int i=1;i<vertical.size();i++){
            long len=vertical.get(i)-vertical.get(i-1);
            list.add(new Edges(len,i-1,true));

        }
        //Differences between horizontal lines create vertical edges
        for(int i=1;i<horizontal.size();i++){
            long len=horizontal.get(i)-horizontal.get(i-1);
            list.add(new Edges(len,i-1,false));
        }
        Collections.sort(list);
        long edgesAdded=0;
        long length=0;
        int[][] set=new int[rows][cols];
        for(int[] e:set)
            Arrays.fill(e,-1);
        int curId=0;
        for(int iter=0;iter<list.size();iter++){
            Edges cur=list.get(iter);
            int index=cur.index;
            boolean isVertical=cur.isVertical;
            if(edgesAdded==rows*cols-1)
                break;
            if(!isVertical){
                for(int j=1;j<set[0].length;j++){
                    int val1=set[index][j-1];
                    int val2=set[index][j];
                    if(val1<0&&val2<0) {
                        set[index][j-1] = curId;
                        set[index][j] = curId;
                    }
                    else if(val1<0||val2<0){
                        set[index][j-1]=Math.max(val1,val2);
                        set[index][j]=Math.max(val1,val2);
                    }
                    else if(disjointSet.find(val1)!=disjointSet.find(val2)){
                        disjointSet.union(val1,val2);
                    }
                    else    //dont add an edge or add to your length if the 2 parts are already connected
                        continue;
                    edgesAdded++;
                    length+=cur.length;
                }

            }
            else{
                for(int j=1;j<set.length;j++){
                    int val1=set[j-1][index];
                    int val2=set[j][index];
                    if(val1<0&&val2<0){
                        set[j-1][index]=curId;
                        set[j][index]=curId;
                    }
                    else if(val1<0||val2<0){
                        set[j-1][index]=Math.max(val1,val2);
                        set[j][index]=Math.max(val1,val2);
                    }
                    else if(disjointSet.find(val1)!=disjointSet.find(val2))
                        disjointSet.union(val1,val2);
                    else
                        continue;
                    edgesAdded++;
                    length+=cur.length;
                }

            }
            curId++;

        }
//        System.out.println(length);
        pw.println(length);
        pw.close();



    }
}
