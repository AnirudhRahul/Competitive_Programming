import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 9/13/2018.
 */
public class allpairspath{
    public static long[][] shortestpath(long[][] adj) {
        int n = adj.length;
        long[][] m = copy(adj);
        for (int k=0; k<n;k++) {

            // Do so between each possible pair of points.
            for (int i=0; i<n; i++) {
                for (int j=0; j<n;j++) {
                    if(m[k][j]==Integer.MAX_VALUE||m[i][k]==Integer.MAX_VALUE)
                        continue;
                    if (m[i][k]+m[k][j] < m[i][j]) {
                        m[i][j] = m[i][k]+m[k][j];
                    }
                }
            }
        }
        return m;
    }
    public static long[][] copy(long[][] a) {
        long[][] res = new long[a.length][a[0].length];
        for (int i=0;i<a.length;i++)
            for (int j=0;j<a[0].length;j++)
                res[i][j] = a[i][j];
        return res;
    }
    public static void print(int[][] a){
        for (int i=0;i<a.length;i++)
            System.out.println(Arrays.toString(a[i]));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        boolean firstRun=true;
        while(true){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int vertices=Integer.parseInt(tokenizer.nextToken());
            int edges=Integer.parseInt(tokenizer.nextToken());
            int queries=Integer.parseInt(tokenizer.nextToken());
            if(vertices+edges+queries==0)
                break;

            if (firstRun) {
                firstRun = false;
            } else {
                System.out.println();
            }
            long[][] adj=new long[vertices][vertices];
            for(int i=0;i<adj.length;i++)
                for(int j=0;j<adj[0].length;j++){
                    adj[i][j]=Integer.MAX_VALUE;
                }
            for(int i=0;i<edges;i++){
                StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
                int a=Integer.parseInt(stringTokenizer.nextToken());
                int b=Integer.parseInt(stringTokenizer.nextToken());
                adj[a][b]=Integer.parseInt(stringTokenizer.nextToken());
                if(a==b)
                    adj[a][b]=Math.min(adj[a][b],0);
            }
            long[][] fin=shortestpath(adj);
            long[][] finfin=shortestpath(fin);
            for(int j=0;j<queries;j++){
                StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
                int a=Integer.parseInt(stringTokenizer.nextToken());
                int b=Integer.parseInt(stringTokenizer.nextToken());
                if(fin[a][b]>=Integer.MAX_VALUE)
                    System.out.println("Impossible");
                else if(fin[a][b]==finfin[a][b])
                    System.out.println(fin[a][b]);
                else if(fin[a][b]!=finfin[a][b])
                    System.out.println("-Infinity");
            }

        }
    }
}
