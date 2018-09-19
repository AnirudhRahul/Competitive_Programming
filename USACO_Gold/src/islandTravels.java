import java.io.*;
import java.util.*;

/**
 * Created by user on 9/18/2018.
 */
public class islandTravels {
    static int[] dR={0,0,1,-1};
    static int[] dC={1,-1,0,0};
    static class Index implements Comparable<Index>{
        int r,c,dist;
        public Index(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist=dist;
        }
        private boolean inRange(){return r>=0&&r<map.length&&c>=0&&c<map[0].length;}
        private int val(){return map[r][c];}
        public void setVal(int in){map[r][c]=in;}
        public void visit(){visited[r][c]=true;}
        public boolean isVisited(){return visited[r][c];}
        public ArrayList<Index> adjacent(){
            Index[] possible={new Index(r+1,c,dist+1),new Index(r-1,c,dist+1),new Index(r,c+1,dist+1),new Index(r,c-1,dist+1)};
            ArrayList<Index> fin=new ArrayList<>(4);
            for(Index e:possible){
                if(e.inRange()&&!e.isVisited()&&e.val()!=-3) {
                    if(e.val()>=0)
                        e.dist-=1;
                    fin.add(e);
                }
            }
            return fin;
        }
        public ArrayList<Index> rawadjacent(){
            Index[] possible={new Index(r+1,c,dist+1),new Index(r-1,c,dist+1),new Index(r,c+1,dist+1),new Index(r,c-1,dist+1)};
            ArrayList<Index> fin=new ArrayList<>(4);
            for(Index e:possible){
                if(e.inRange()&&in[e.r][e.c]=='X')
                    fin.add(e);
            }
            return fin;
        }
        @Override
        public int compareTo(Index o) {
            return dist-o.dist;
        }
    }
    static int fin;
    public static int recurse(int out, int visited){
        if(visited==fin)
            return dp[out][visited]=0;
        if(dp[out][visited]!=-1)
            return dp[out][visited];
        int min=Integer.MAX_VALUE;
        for(int next:edges.get(out).keySet()){
            if((visited|(1<<next))==visited)
                continue;
            else
                min=Math.min(min,edges.get(out).get(next)+recurse(next,visited+(1<<next)));

        }
        return dp[out][visited]=min;
    }

    static boolean[][] visited;
    static int[][] map;
    static int[][] dp;
    static char[][] in;
    static HashMap<Integer, HashMap<Integer,Integer>> edges;
    public static void main(String[] args) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("island.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("island.out")));
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        int row=Integer.parseInt(stringTokenizer.nextToken());
        int col=Integer.parseInt(stringTokenizer.nextToken());
        in=new char[row][col];
        for(int i=0;i<row;i++)
            in[i]=br.readLine().toCharArray();
        int startID=0;
        map=new int[row][col];
        for(int[] e:map)
            Arrays.fill(e,-1);
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++){
                if(map[i][j]!=-1)
                    continue;
                if(in[i][j]=='X'){
                    ArrayDeque<Index> adj=new ArrayDeque<>();
                    adj.add(new Index(i,j,0));
                    while(!adj.isEmpty()){
                        Index cur=adj.pollFirst();
                        if(cur.val()==startID)
                            continue;
                        cur.setVal(startID);
                        adj.addAll(cur.rawadjacent());
                    }
                    startID++;
                }
                else
                    map[i][j]=in[i][j]=='S'?-2:-3;
            }

        edges=new HashMap<>();
        for(int i=0;i<startID;i++)
            edges.put(i,new HashMap<>());

        int nextSearch=0;
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++){
                if(map[i][j]==nextSearch) {
                    visited = new boolean[row][col];
                    PriorityQueue<Index> deque=new PriorityQueue<>();
                    deque.add(new Index(i,j,0));
                    while(!deque.isEmpty()){
                        Index cur=deque.poll();
                        if(cur.isVisited())
                            continue;
                        else
                            cur.visit();
                        if(cur.val()>=0)
                            if(!edges.get(map[i][j]).containsKey(cur.val())||edges.get(map[i][j]).get(cur.val())>cur.dist)
                                edges.get(map[i][j]).put(cur.val(),cur.dist);
                        deque.addAll(cur.adjacent());
                    }
                    nextSearch++;
                }
            }
        dp=new int[edges.size()][1<<(edges.size())];
        for(int[] e:dp)
            Arrays.fill(e,-1);
        fin=(1<<edges.size())-1;

        int min=Integer.MAX_VALUE;
        for(int i=0;i<edges.size();i++){
            min=Math.min(min,recurse(i,1<<i));
        }
        pw.println(min);
        pw.close();
    }
}
