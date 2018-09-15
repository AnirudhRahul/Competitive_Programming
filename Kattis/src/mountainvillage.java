import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by user on 9/14/2018.
 */
public class mountainvillage{
    static class Index implements Comparable<Index>{
        int r,c;
        public Index(int r, int c) {
            this.r = r;
            this.c = c;
        }
        @Override
        public int compareTo(Index o) {
            return map[r][c]-map[o.r][o.c];
        }
        private boolean inRange(){return r>=0&&r<map.length&&c>=0&&c<map[0].length;}
        private short val(){return map[r][c];}
        public void visit(){visited[r][c]=curVisitedID;}
        public boolean isVisited(){return visited[r][c]==curVisitedID;}
        public ArrayList<Index> adjacent(){
            Index[] possible={new Index(r+1,c),new Index(r-1,c),new Index(r,c+1),new Index(r,c-1)};
            ArrayList<Index> fin=new ArrayList<>(4);
            for(Index e:possible){
                if(e.inRange()&&!e.isVisited())
                    fin.add(e);
            }
            return fin;
        }
    }
    //Store the input
    static short[][] map;
    //Visited array(uses numbers so we dont need to reallocate a new array each time we want to wipe our array)
    static short[][] visited;
    static short curVisitedID=1;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        int r=Integer.parseInt(stringTokenizer.nextToken());
        int c=Integer.parseInt(stringTokenizer.nextToken());
        map=new short[r][c];
        visited=new short[r][c];
        short[][][] dp=new short[r][c][r*c+1];

        for(int i=0;i<r;i++)
            for(int j=0;j<c;j++)
                Arrays.fill(dp[i][j], (short) -1);

        for(int i=0;i<r;i++) {
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j]=Short.parseShort(tokenizer.nextToken());
            }
        }

        for(int i=0;i<r;i++)
            for(int j=0;j<c;j++){
            //wipes our visited array
                curVisitedID++;
                //Calculate the min difference you need to get an island of size 'size'
                //and store it in dp[i][j][size]
                //Use current value(map[i][j]) as the smallest value on the island
                PriorityQueue<Index> queue=new PriorityQueue<>();
                queue.add(new Index(i,j));
                short max=map[i][j];
                int size=1;
                while(!queue.isEmpty()){
                    Index cur=queue.poll();
                    if(cur.isVisited()||cur.val()<map[i][j])
                        continue;
                    else
                        cur.visit();
                    max=max<cur.val()?cur.val():max;
                    dp[i][j][size]= (short) (max-map[i][j]);
                    queue.addAll(cur.adjacent());
                    size++;
                }
            }

        //Answer the queries by finding the min height
        //difference needed by iterating through the array
        int queries=Integer.parseInt(br.readLine());
        while (queries-->0){
            int cur=Integer.parseInt(br.readLine());
            int min=Integer.MAX_VALUE;
            for(int i=0;i<r;i++)
                for(int j=0;j<c;j++)
                    if(dp[i][j][cur]==-1)
                        continue;
                    else
                        min=Math.min(min,dp[i][j][cur]);
            System.out.println(min);
        }


    }
}
