import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by user on 9/13/2018.
 */
public class funhouse{
    static class Index{
        int r,c;
        public Index(int r, int c){this.r=r;this.c=c;}
        public void add(Index in){r+=in.r;c+=in.c;}
        public char val(){return map[r][c];}
        public boolean isEdge(){return r==0||c==0||(r==map.length-1)||(c==map[0].length-1);}
        public String toString(){return "("+r+","+c+")";}
    }
    static Index[] directions={new Index(1,0),new Index(0,1),new Index(-1,0),new Index(0,-1)};
    static char[][] map;
    static int direction;
    static Index start;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int count=0;
        while(true){
            count++;
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int c=Integer.parseInt(tokenizer.nextToken());
            int r=Integer.parseInt(tokenizer.nextToken());
            if(r+c==0)
                break;
            map=new char[r][c];
            for(int i=0;i<r;i++)
                map[i]=br.readLine().toCharArray();
            //top row
            for(int i=0;i<c;i++)
                if(map[0][i]=='*'){
                    start=new Index(0,i);
                    direction=0;
                    break;
                }
            //bottom row
            for(int i=0;i<c;i++)
                if(map[r-1][i]=='*'){
                    start=new Index(r-1,i);
                    direction=2;
                    break;
                }
            //left column
            for(int k=0;k<r;k++)
                if(map[k][0]=='*'){
                    start=new Index(k,0);
                    direction=1;
                    break;
                }
            //bottom column
            for(int k=0;k<r;k++)
                if(map[k][c-1]=='*'){
                    start=new Index(k,c-1);
                    direction=3;
                    break;
                }

            start.add(directions[direction]);

            while(!start.isEdge()){
                if(start.val()=='/')
                    direction=3-direction;
                if(start.val()=='\\')
                    direction=(direction+1+(direction%2==1?2:0))%4;
                start.add(directions[direction]);

            }
            map[start.r][start.c]='&';
            System.out.println("HOUSE "+count);
            for(int i=0;i<map.length;i++) {
                for (int j = 0; j < map[0].length; j++)
                    System.out.print(map[i][j]);
                System.out.println();
            }
        }
    }
}
