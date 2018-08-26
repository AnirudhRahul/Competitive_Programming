import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by user on 8/25/2018.
 */
public class cowNavigation{

    static class visitedArray{
        boolean[][][] visited;
        int n;
        public visitedArray(int n){
            this.n=n;
            visited=new boolean[4][n*n][n*n];
        }

        public void visit(State in){
            visited[in.dir][n*in.r1+in.c1][n*in.r2+in.c2]=true;
        }

        public boolean isVisited(State in){
            return visited[in.dir][n*in.r1+in.c1][n*in.r2+in.c2];
        }
    }

    static class State{
        int dir,r1,c1,r2,c2,dist;
        public State(int dir,int r1,int c1,int r2,int c2,int dist){
            this.dir=dir;this.r1=r1;this.c1=c1;this.r2=r2;this.c2=c2;this.dist=dist;
        }
        public String toString(){
            return r1+" "+c1+"   "+r2+" "+c2+" ::"+dir;
        }
    }

    static visitedArray visitedArray;
    static int[] dR={-1,0,1,0};
    static int[] dC={0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cownav.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int size=Integer.parseInt(br.readLine());
        visitedArray=new visitedArray(size);
        char[][] map=new char[size][size];
        for(int i=0;i<size;i++)
            map[i]=br.readLine().toCharArray();
        State start=new State(0,size-1,0,size-1,0,0);
        ArrayDeque<State> arrayDeque=new ArrayDeque<>();
        int distFin=0;
        arrayDeque.add(start);
        while(!arrayDeque.isEmpty()){
//            System.out.println(arrayDeque);
            State cur=arrayDeque.pollFirst();
            if(cur.r1==0&&cur.c1==size-1&&cur.r2==0&&cur.c2==size-1) {
                distFin = cur.dist;
                break;
            }
            if(visitedArray.isVisited(cur))
                continue;
            ArrayList<State> list=new ArrayList<>();
            list.add(new State((cur.dir+1)%4,cur.r1,cur.c1,cur.r2,cur.c2,cur.dist+1));
            list.add(new State((cur.dir+3)%4,cur.r1,cur.c1,cur.r2,cur.c2,cur.dist+1));
            int newr1=cur.r1+dR[cur.dir];
            int newc1=cur.c1+dC[cur.dir];
            int newr2=cur.r2+dR[(cur.dir+1)%4];
            int newc2=cur.c2+dC[(cur.dir+1)%4];
            try{
                if(map[newr1][newc1]=='H'){
                    newr1=cur.r1;
                    newc1=cur.c1;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                newr1=cur.r1;
                newc1=cur.c1;
            }

            try{
                if(map[newr2][newc2]=='H'){
                    newr2=cur.r2;
                    newc2=cur.c2;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                newr2=cur.r2;
                newc2=cur.c2;
            }
            if(cur.r1==0&&cur.c1==size-1){
                newr1=cur.r1;
                newc1=cur.c1;
            }
            if(cur.r2==0&&cur.c2==size-1){
                newr2=cur.r2;
                newc2=cur.c2;
            }

            list.add(new State(cur.dir,newr1,newc1,newr2,newc2,cur.dist+1));
            visitedArray.visit(cur);
            arrayDeque.addAll(list);
        }
        pw.println(distFin);
        pw.close();
//        System.out.println(distFin);
    }
}
