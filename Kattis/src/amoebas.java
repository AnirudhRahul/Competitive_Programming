import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 9/12/2018.
 */
public class amoebas{
    static int[] dR={1,-1,0,0,1,-1,1,-1};
    static int[] dC={0,0,1,-1,1,-1,-1,1};
    static class Index{
        int r,c;
        public Index(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public String toString(){return "("+r+" "+c+")";}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        int r=Integer.parseInt(stringTokenizer.nextToken());
        int c=Integer.parseInt(stringTokenizer.nextToken());
        char[][] map=new char[r][c];
        for(int i=0;i<r;i++)
            map[i]=br.readLine().toCharArray();
        boolean[][] visited=new boolean[r][c];
        int count=0;
        for(int i=0;i<r;i++)
            out:for(int j=0;j<c;j++){
                if(map[i][j]=='.'||visited[i][j])
                    continue;

                ArrayDeque<Index> q=new ArrayDeque<>();
                q.add(new Index(i,j));
                while (!q.isEmpty()){
                    Index cur=q.pollFirst();
                    int curR=cur.r;
                    int curC=cur.c;
                    if(visited[curR][curC])
                        continue ;
                    ArrayList<Index> list=new ArrayList<>(2);
                    for(int k=0;k<8;k++){
                        try{
                            if(map[curR+dR[k]][curC+dC[k]]=='#')
                                list.add(new Index(curR+dR[k],curC+dC[k]));
                        }catch (Exception e){}
                    }
                    q.addAll(list);
                    visited[curR][curC]=true;

                }
                count++;

           }
            System.out.println(count);



    }
}
