/**
 * Created by user on 11/16/2019.
 */

import java.io.*;
import java.util.Scanner;

public class AlwaysLate10342 {
    static long[][][] floyd(long[][][] adj){
        int vertices = adj.length;
        for(int k=0;k<vertices;k++)
            for(int i=0;i<vertices;i++)
                for(int j=0;j<vertices;j++) {
                    if (adj[i][k][0] + adj[k][j][0] < adj[i][j][0])
                        adj[i][j][0] = adj[i][k][0] + adj[k][j][0];
                    else if (adj[i][k][0] + adj[k][j][0] < adj[i][j][1])
                        adj[i][j][1] = adj[i][k][0] + adj[k][j][0];
//                    else {

                        long option1 = adj[i][k][1] + adj[k][j][0];
                        long option2 = adj[i][k][0] + adj[k][j][1];
                        if (Math.min(option1, option2) < adj[i][j][1])
                            adj[i][j][1] = Math.min(option1, option2);
                        else if (Math.max(option1, option2) < adj[i][j][1])
                            adj[i][j][1] = Math.max(option1, option2);
                        else if (adj[i][k][1] + adj[k][j][1] < adj[i][j][1])
                            adj[i][j][1] = adj[i][k][1] + adj[k][j][1];
//                    }
//                        adj[i][j][1] = adj[i][k][1] + adj[k][j][0];
//                    else if (adj[i][k][0] + adj[k][j][1] < adj[i][j][1])
//                        adj[i][j][1] = adj[i][k][0] + adj[k][j][1];
                }


        return adj;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner scan = new Scanner(System.in);
        int c=0;
        while(scan.hasNextInt()) {
            c++;
            int vertices = scan.nextInt();
            int edges = scan.nextInt();
            long[][][] adj = new long[vertices][vertices][2];
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    adj[i][j][0] = Integer.MAX_VALUE;
                    adj[i][j][1] = Integer.MAX_VALUE;
                }
            }

            for (int i = 0; i < edges; i++) {
                int a = scan.nextInt();
                int b = scan.nextInt();
                int w = scan.nextInt();
                adj[a][b][0] = w;
                adj[b][a][0] = w;
//                adj[a][b][1] = w;
//                adj[b][a][1] = w;
            }
//            for(int i=0;i<vertices;i++) {
//                for (int j = 0; j < vertices; j++) {
//                    System.out.print(adj[i][j][0] + "\t");
//                }
//                System.out.println();
//            }

            adj=floyd(adj);
            wr.write("Set #"+c+"\n");
            int q = scan.nextInt();
            while (q-->0){
                int a = scan.nextInt();
                int b = scan.nextInt();
                if(adj[a][b][1]!=Integer.MAX_VALUE)
                    wr.write(adj[a][b][1]+"\n");
                else
                    wr.write("?\n");

            }
            wr.flush();
        }
    }
}
