import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by user on 9/1/2018.
 */
public class cowCrosstheRoad1{
    static int size,cost;
    static int[] dR={0,0,1,-1};
    static int[] dC={1,-1,0,0};
    static class State implements Comparable<State>{
        int r,c,runningCost,steps;
        public State(int r,int c,int runningCost,int steps){
            this.r=r;this.c=c;this.runningCost=runningCost;this.steps=steps;
        }

        @Override
        public int compareTo(State o) {
            return runningCost-o.runningCost;
        }
        public String toString(){
            return "["+r+","+c+"] cost:"+runningCost+" steps:"+steps;
        }
    }
    public static void main(String[] args) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("visitfj.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        size=Integer.parseInt(tokenizer.nextToken());
        cost=Integer.parseInt(tokenizer.nextToken());
        int[][] map=new int[size][size];
        int[][][] visited=new int[size][size][3];
        for(int i=0;i<size;i++) {
            StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
            for(int j=0;j<size;j++) {
                map[i][j]=Integer.parseInt(stringTokenizer.nextToken());
                visited[i][j][0]=Integer.MAX_VALUE;
                visited[i][j][1]=Integer.MAX_VALUE;
                visited[i][j][2]=Integer.MAX_VALUE;
            }
        }
        PriorityQueue<State> priorityQueue=new PriorityQueue<>();
        priorityQueue.add(new State(0,0,0,0));
        int distFin=0;
        while(true){
            State cur=priorityQueue.poll();
            if(visited[cur.r][cur.c][cur.steps%3]<cur.runningCost)
                continue;
//            System.out.println(priorityQueue);

            if(cur.r==size-1&&cur.c==size-1) {
                distFin = cur.runningCost;
                break;
            }
            ArrayList<State> newStates=new ArrayList<>(4);
            for(int i=0;i<4;i++){
                int newR=cur.r+dR[i];
                int newC=cur.c+dC[i];
                if(newR>=0&&newR<size&&newC>=0&&newC<size){
                    int newCost=cur.runningCost+cost;
                    if((cur.steps+1)%3==0){
                        newCost+=map[newR][newC];
                    }
                    if(newCost<visited[newR][newC][(cur.steps+1)%3]) {
                        newStates.add(new State(newR, newC, newCost, cur.steps + 1));
                        visited[newR][newC][(cur.steps+1)%3]=newCost;
                    }
                }
            }
            for(State e:newStates)
                priorityQueue.add(e);
        }
//        System.out.println(distFin);
        pw.println(distFin);
        pw.close();
    }
}
