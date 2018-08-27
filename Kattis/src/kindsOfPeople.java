import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by user on 8/16/2018.
 */
public class kindsOfPeople {
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
    static int[] Rmoves={0,0,1,-1};
    static int[] Cmoves={1,-1,0,0};
    static int rows,cols;
    public static boolean inRange(int r, int c){
        return r>=0&&c>=0&&r<rows&&c<cols;
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens=new StringTokenizer(br.readLine());
        rows=Integer.parseInt(tokens.nextToken());
        cols=Integer.parseInt(tokens.nextToken());
        char[][] map=new char[rows][cols];
        for(int i=0;i<rows;i++)
            map[i]=br.readLine().toCharArray();
        int[][] ids=new int[rows][cols];
        int curMaxId=0;
        DisjointSet disjointSet=new DisjointSet(rows*cols);
        for(int i=0;i<map.length;i++)
            for(int j=0;j<map[0].length;j++){
                char cur=map[i][j];
                ArrayList<Integer> surroundings=new ArrayList<>(4);
                out:for(int k=0;k<4;k++){
                    int newR=i+Rmoves[k];
                    int newC=j+Cmoves[k];
                    if(inRange(newR,newC)&&map[newR][newC]==cur){
                        int curId=ids[newR][newC];
                        if(curId==0)
                            continue;
                        for(int e:surroundings)
                            if(disjointSet.find(e)==disjointSet.find(curId))
                                continue out;
                        surroundings.add(disjointSet.find(curId));
                    }
                }
                if(surroundings.size()==0) {
                    ids[i][j] = curMaxId + 1;
                    curMaxId++;
                }
                else{
                    ids[i][j]=surroundings.get(0);
                    for(int index=1;index<surroundings.size();index++){
                        disjointSet.union(surroundings.get(index),surroundings.get(index-1));
                    }

                }


            }
            int queries=Integer.parseInt(br.readLine());
            for(int i=0;i<queries;i++){
                StringTokenizer tokenizer=new StringTokenizer(br.readLine());
                int r1=Integer.parseInt(tokenizer.nextToken())-1;
                int c1=Integer.parseInt(tokenizer.nextToken())-1;
                int r2=Integer.parseInt(tokenizer.nextToken())-1;
                int c2=Integer.parseInt(tokenizer.nextToken())-1;
                if(disjointSet.find(ids[r1][c1])==disjointSet.find(ids[r2][c2]))
                    System.out.println(map[r1][c1]=='1'?"decimal":"binary");
                else
                    System.out.println("neither");
            }


    }
}
