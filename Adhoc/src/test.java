import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class test {
    public static class Index{
        int r,c;
        public Index(int r,int c){
            this.r=r;
            this.c=c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader( new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        while (cases-->0){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int r=Integer.parseInt(tokenizer.nextToken());
            int c=Integer.parseInt(tokenizer.nextToken());
            int[][] troops=new int[r][c];
            for(int i=0;i<troops.length;i++) {
                String in=br.readLine();
                for (int j = 0; j < troops[0].length; j++) {
                    troops[i][j]=in.charAt(j)-'0';
                }
            }
            int[][] marks=new int[r][c];
            int curID=2;
            int[] Dc={0,0,1,-1,1,-1,-1,1};
            int[] Dr={1,-1,0,0,1,1,-1,-1};
            for(int i=0;i<troops.length;i++)
                for(int j=0;j<troops[0].length;j++){
                if(marks[i][j]==0){
                    Index start=new Index(i,j);
                    ArrayDeque<Index> q=new ArrayDeque<>();
                    while(!q.isEmpty()){
                        Index cur=q.pollFirst();
                        if(marks[cur.r][cur.c]!=0)
                            continue;
                        marks[cur.r][cur.c]=curID;
                        Index[] adj=new Index[8];
                        for(int )

                    }
                }
                else{
                    continue;
                }


                }


        }
    }
}
