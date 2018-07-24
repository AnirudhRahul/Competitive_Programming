import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by user on 7/24/2018.
 */
public class bessieDream {
    static boolean[][][] visited;
    static short[][] map;
    static int[] Dr={1,-1,0,0};
    static int[] Dc={0,0,1,-1};
    static class Point{
        short prevDirection;
        boolean smells;
        int r,c;
        int dist;
        public Point(int r, int c){
            this.r=r;
            this.c=c;
        }
        public short getVal(){
            return map[r][c];
        }
        public void visit(){
            short val=getVal();
            if(val==1)
                visited[r][c][smells?1:0]=true;
            else if(val==2)
                visited[r][c][0]=true;
            else if(val==3)
                visited[r][c][0]=true;
            else if(val==4)
                visited[r][c][prevDirection]=true;
        }
        public boolean hasBeenVisited(){
            short val=getVal();
            int height=0;
            if(val==1)
                height=smells?1:0;
            else if(val==4)
                height=prevDirection;
            return visited[r][c][height];
        }

        public ArrayList<Point> adjacent(){
            ArrayList<Point> list=new ArrayList<>(4);
            if(getVal()==4) {
                int newR=r+Dr[prevDirection];
                int newC=c+Dc[prevDirection];
                if(inRange(newR,newC)&&map[newR][newC]!=0&&map[newR][newC]!=3){
                    Point cur=new Point(newR,newC);
                    cur.prevDirection=prevDirection;
                    cur.dist=dist+1;
                    list.add(cur);
                    return list;
                }

            }

            for(short k=0;k<4;k++){
                int newR=r+Dr[k];
                int newC=c+Dc[k];
                if(!inRange(newR,newC))
                    continue;
                Point cur=new Point(newR,newC);
                cur.prevDirection=k;
                cur.smells=smells;
                cur.dist=dist+1;
                short val=cur.getVal();
                if(val==0)
                    continue;
                else if(val==2){
                    cur.smells=true;
                }
                else if(val==3){
                    if(!smells)
                        continue;
                }
                else if(val==4){
                    cur.smells=false;
                }
                list.add(cur);

            }

            return list;
        }
    }
    public static boolean inRange(int r,int c){
        return r>=0&&c>=0&&r<rows&&c<cols;
    }
    static int rows;
    static int cols;
    public static int BFS(Point start){
        ArrayDeque<Point> q=new ArrayDeque<>();
        q.add(start);
        while (!q.isEmpty()){
            Point cur=q.pop();
            if(cur.r==rows-1&&cur.c==cols-1)
                return cur.dist;
            if(cur.hasBeenVisited())
                continue;
            else{
                cur.visit();
                q.addAll(cur.adjacent());
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("dream.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        rows=Integer.parseInt(tokenizer.nextToken());
        cols=Integer.parseInt(tokenizer.nextToken());
        visited=new boolean[rows][cols][4];
        map=new short[rows][cols];
        for(int i=0;i<map.length;i++){
            tokenizer=new StringTokenizer(br.readLine());
            for(int j=0;j<map[0].length;j++)
                map[i][j]=Short.parseShort(tokenizer.nextToken());
        }
        Point start=new Point(0,0);
        start.smells=false;
        start.dist=0;
        pw.println(BFS(start));
        pw.close();
//        System.out.println(BFS(start));
    }
}
