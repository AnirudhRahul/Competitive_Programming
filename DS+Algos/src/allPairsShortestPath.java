/**
 * Created by user on 11/16/2019.
 */

import java.io.*;

public class allPairsShortestPath {
    //Takes adjacency matrix as input
    //Set empty edges to inf
    static int[][] floyd(int[][] adj){
        int vertices = adj.length;
        for(int k=0;k<vertices;k++)
            for(int i=0;i<vertices;i++)
                for(int j=0;j<vertices;j++)
                    adj[i][j] = Math.min(adj[i][j], adj[i][k]+adj[k][j]);

        return adj;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    }
}
