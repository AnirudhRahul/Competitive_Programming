/**
 * Created by user on 11/17/2019.
 */

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class jailBreak {
    static int[] dR = {0,0,-1,1};
    static int[] dC = {1,-1,0,0};
    static int rows, cols;
    static char[][] map;
    static class Point{
        int r,c;
        int cost;
        public Point(int r, int c, int cost){
            this.r=r;
            this.c=c;
            this.cost=cost;
        }
        Point theLast;
        public Point(int r, int c, int cost, Point theLast){
            this.r=r;
            this.c=c;
            this.cost=cost;
            this.theLast=theLast;
        }
        public String toString(){return "("+r+","+c+") dst:"+cost;}
        ArrayList<Point> adj(){
           ArrayList<Point> list = new ArrayList<>(4);
           for(int k=0;k<4;k++) {
               if(inRange(r+dR[k], c+dC[k])) {
                    char cur = map[r+dR[k]][c+dC[k]];
                    if(cur=='*')
                        continue;
                    if(cur=='#') {
                        list.add(new Point(r + dR[k], c + dC[k], cost+1,this));
                    }
                    else
                        list.add(new Point(r + dR[k], c + dC[k], cost, this));

               }
           }
           return list;
        }


    }
    static boolean isEdge(Point in){
        boolean ro = in.r==0 || in.r==rows-1;
        boolean co = in.c==0 || in.c==cols-1;
        return ro || co;

    }
    static boolean inRange(int r, int c){
        return r>=0&&r<rows && c>=0&&c<cols;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        int cases = Integer.parseInt(br.readLine());
        while(cases-->0) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            rows = Integer.parseInt(tokenizer.nextToken());
            cols = Integer.parseInt(tokenizer.nextToken());
            map = new char[rows][cols];
            ArrayDeque<Point> q = new ArrayDeque<>();
            Point source1=null, source2=null;
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    map[i][j]= (char) br.read();
                    if(map[i][j]=='$' && source1==null)
                        source1 = new Point(i,j,0);
                    else if(map[i][j]=='$' && source2==null)
                        source2 = new Point(i,j,0);
                }
                br.read();
            }
            q.add(source1);
            int[][] dist = new int[rows][cols];
            for(int i=0;i<rows;i++)
                for(int j=0;j<cols;j++)
                    dist[i][j]=Integer.MAX_VALUE;
            Point fin=null;
            while(!q.isEmpty()){
                Point cur = q.pollFirst();
                if(map[cur.r][cur.c]=='*')
                    continue;
                if(cur.cost>=dist[cur.r][cur.c])
                    continue;

                if(isEdge(cur)){
                    fin=cur;
                    break;
                }
                dist[cur.r][cur.c]=cur.cost;
                for(Point e:cur.adj()){
                    if(e.cost>cur.cost)
                        q.addLast(e);
                    else
                        q.addFirst(e);
                }

            }
            int totalCost = fin.cost;
            if(map[fin.r][fin.c]=='#')
                map[fin.r][fin.c]='.';
            while(true){
                fin=fin.theLast;
                if(fin==null)
                    break;
                if(map[fin.r][fin.c]=='#')
                    map[fin.r][fin.c]='.';
            }

            q = new ArrayDeque<>();

            q.add(source2);
            dist = new int[rows][cols];
            for(int i=0;i<rows;i++)
                for(int j=0;j<cols;j++)
                    dist[i][j]=Integer.MAX_VALUE;
            while(!q.isEmpty()){
                Point cur = q.pollFirst();
                if(map[cur.r][cur.c]=='*')
                    continue;
                if(cur.cost>=dist[cur.r][cur.c])
                    continue;
                if(isEdge(cur)){
                    totalCost+=cur.cost;
                    break;
                }
                dist[cur.r][cur.c]=cur.cost;
                for(Point e:cur.adj()){
                    if(map[e.r][e.c]=='#')
                        q.addLast(e);
                    else
                        q.addFirst(e);
                }

            }
            System.out.println(totalCost);


        }
    }
}
